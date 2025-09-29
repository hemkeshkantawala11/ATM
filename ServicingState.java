package ATM;

public class ServicingState implements ATMState {
    private final ATM atm;
    public ServicingState(ATM atm) { this.atm = atm; }
    @Override public void insertCard(Card card) {}
    @Override public void enterPin(String pin) {}
    @Override public void selectTransaction(TransactionType type) {}
    @Override public void requestWithdraw(int amount) {}
    @Override public void depositCash(java.util.Map<Integer,Integer> notes) {}
    @Override public void printMiniStatement() {}
    @Override public void changePin(String oldPin, String newPin) {}
    @Override public void ejectCard() {}
}
