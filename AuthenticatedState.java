package ATM;

import java.util.Map;
import java.util.Optional;

public class AuthenticatedState implements ATMState {
    private final ATM atm;
    public AuthenticatedState(ATM atm) { this.atm = atm; }
    @Override public void insertCard(Card card) {}
    @Override public void enterPin(String pin) {}
    @Override
    public void selectTransaction(TransactionType type) {
        atm.setSelectedTransaction(type);
    }
    @Override
    public void requestWithdraw(int amount) {
        if (atm.getSelectedTransaction() != TransactionType.WITHDRAW) {
            System.out.println("Select withdraw transaction first");
            return;
        }
        String cn = atm.getCurrentCard().getCardNumber();
        Optional<Integer> bal = atm.bankService().getBalance(cn);
        if (!bal.isPresent()) {
            System.out.println("Account not found");
            return;
        }
        if (bal.get() < amount) {
            System.out.println("Insufficient funds in account");
            return;
        }
        Optional<Map<Integer, Integer>> dispensed = atm.dispenser().dispenseAmount(amount);
        if (!dispensed.isPresent()) {
            System.out.println("Cannot dispense requested amount with available notes");
            return;
        }
        boolean ok = atm.bankService().withdraw(cn, amount);
        if (!ok) {
            System.out.println("Bank declined withdrawal");
            return;
        }
        atm.printer().print("Withdrawn: " + amount + " Dispensed: " + dispensed.get());
        System.out.println("Please collect your cash");
    }
    @Override
    public void depositCash(Map<Integer,Integer> notes) {
        if (atm.getSelectedTransaction() != TransactionType.DEPOSIT) {
            System.out.println("Select deposit transaction first");
            return;
        }
        int total = notes.entrySet().stream().mapToInt(e -> e.getKey()*e.getValue()).sum();
        boolean accepted = atm.depositSlot().acceptDeposit(notes);
        if (!accepted) {
            System.out.println("Deposit rejected");
            return;
        }
        boolean ok = atm.bankService().deposit(atm.getCurrentCard().getCardNumber(), total);
        if (!ok) {
            System.out.println("Bank failed to credit deposit");
            return;
        }
        atm.printer().print("Deposit successful: " + total);
    }
    @Override
    public void printMiniStatement() {
        if (atm.getSelectedTransaction() != TransactionType.MINI_STATEMENT && atm.getSelectedTransaction() != TransactionType.NONE) {
            // still allow
        }
        java.util.List<TransactionRecord> records = atm.bankService().miniStatement(atm.getCurrentCard().getCardNumber());
        StringBuilder sb = new StringBuilder();
        sb.append("Mini Statement\n");
        for (TransactionRecord r : records) sb.append(r.toString()).append("\n");
        atm.printer().print(sb.toString());
    }
    @Override
    public void changePin(String oldPin, String newPin) {
        boolean ok = atm.bankService().changePin(atm.getCurrentCard().getCardNumber(), oldPin, newPin);
        if (ok) atm.printer().print("PIN changed successfully");
        else System.out.println("PIN change failed");
    }
    @Override
    public void ejectCard() {
        atm.cardReader().eject();
        atm.setCurrentCard(null);
        atm.setState(atm.idleState());
        System.out.println("Card ejected");
    }
}
