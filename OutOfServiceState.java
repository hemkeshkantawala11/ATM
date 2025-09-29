package atm;

public class OutOfServiceState implements ATMState {
    private final ATM atm;
    public OutOfServiceState(ATM atm) { this.atm = atm; }
    @Override public void insertCard(Card card) { System.out.println("ATM out of service"); }
    @Override public void enterPin(String pin) { System.out.println("ATM out of service"); }
    @Override public void selectTransaction(TransactionType type) { System.out.println("ATM out of service"); }
    @Override public void requestWithdraw(int amount) { System.out.println("ATM out of service"); }
    @Override public void depositCash(java.util.Map<Integer,Integer> notes) { System.out.println("ATM out of service"); }
    @Override public void printMiniStatement() { System.out.println("ATM out of service"); }
    @Override public void changePin(String oldPin, String newPin) { System.out.println("ATM out of service"); }
    @Override public void ejectCard() { System.out.println("ATM out of service"); }
}
