package atm;

public class CardInsertedState implements ATMState {
    private final ATM atm;
    public CardInsertedState(ATM atm) { this.atm = atm; }
    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted");
    }
    @Override
    public void enterPin(String pin) {
        Card c = atm.getCurrentCard();
        if (c == null) { atm.setState(atm.idleState()); return; }
        boolean ok = atm.pinEntry().enterPin(c.getCardNumber(), pin, atm.bankService());
        if (ok) {
            atm.setState(atm.pinVerifiedState());
            System.out.println("PIN accepted");
        } else {
            System.out.println("Invalid PIN");
        }
    }
    @Override public void selectTransaction(TransactionType type) {}
    @Override public void requestWithdraw(int amount) {}
    @Override public void depositCash(java.util.Map<Integer,Integer> notes) {}
    @Override public void printMiniStatement() {}
    @Override public void changePin(String oldPin, String newPin) {}
    @Override
    public void ejectCard() {
        atm.cardReader().eject();
        atm.setCurrentCard(null);
        atm.setState(atm.idleState());
        System.out.println("Card ejected");
    }
}
