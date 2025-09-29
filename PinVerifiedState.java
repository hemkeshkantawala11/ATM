package ATM;

public class PinVerifiedState implements ATMState {
    private final ATM atm;
    public PinVerifiedState(ATM atm) { this.atm = atm; }
    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted");
    }
    @Override
    public void enterPin(String pin) {
        System.out.println("PIN already verified");
    }
    @Override
    public void selectTransaction(TransactionType type) {
        atm.setSelectedTransaction(type);
        atm.setState(atm.authenticatedState());
        System.out.println("Transaction selected: " + type);
    }
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
