package ATM;

public class IdleState implements ATMState {
    private final ATM atm;
    public IdleState(ATM atm) { this.atm = atm; }
    @Override
    public void insertCard(Card card) {
        atm.cardReader().insert(card);
        atm.setCurrentCard(card);
        atm.setState(atm.cardInsertedState());
        System.out.println("Card inserted: " + card.getCardNumber());
    }
    @Override public void enterPin(String pin) {}
    @Override public void selectTransaction(TransactionType type) {}
    @Override public void requestWithdraw(int amount) {}
    @Override public void depositCash(java.util.Map<Integer,Integer> notes) {}
    @Override public void printMiniStatement() {}
    @Override public void changePin(String oldPin, String newPin) {}
    @Override
    public void ejectCard() {
        System.out.println("No card to eject");
    }
}
