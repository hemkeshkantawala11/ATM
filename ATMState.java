package ATM;

public interface ATMState {
    void insertCard(Card card);
    void enterPin(String pin);
    void selectTransaction(TransactionType type);
    void requestWithdraw(int amount);
    void depositCash(java.util.Map<Integer,Integer> notes);
    void printMiniStatement();
    void changePin(String oldPin, String newPin);
    void ejectCard();
}
