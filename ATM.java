package atm;

public class ATM {
    private final CardReader cardReader;
    private final PinEntry pinEntry;
    private final CashDispenser dispenser;
    private final Printer printer;
    private final DepositSlot depositSlot;
    private final BankService bankService;

    private ATMState idle;
    private ATMState cardInserted;
    private ATMState pinVerified;
    private ATMState authenticated;
    private ATMState servicing;
    private ATMState outOfService;

    private ATMState state;
    private Card currentCard;
    private TransactionType selectedTransaction = TransactionType.NONE;

    public ATM(CardReader cardReader, PinEntry pinEntry, CashDispenser dispenser, Printer printer, DepositSlot depositSlot, BankService bankService) {
        this.cardReader = cardReader;
        this.pinEntry = pinEntry;
        this.dispenser = dispenser;
        this.printer = printer;
        this.depositSlot = depositSlot;
        this.bankService = bankService;

        this.idle = new IdleState(this);
        this.cardInserted = new CardInsertedState(this);
        this.pinVerified = new PinVerifiedState(this);
        this.authenticated = new AuthenticatedState(this);
        this.servicing = new ServicingState(this);
        this.outOfService = new OutOfServiceState(this);

        this.state = idle;
    }

    void setState(ATMState state) { this.state = state; }
    CardReader cardReader() { return cardReader; }
    PinEntry pinEntry() { return pinEntry; }
    CashDispenser dispenser() { return dispenser; }
    Printer printer() { return printer; }
    DepositSlot depositSlot() { return depositSlot; }
    BankService bankService() { return bankService; }
    ATMState idleState() { return idle; }
    ATMState cardInsertedState() { return cardInserted; }
    ATMState pinVerifiedState() { return pinVerified; }
    ATMState authenticatedState() { return authenticated; }
    ATMState servicingState() { return servicing; }
    ATMState outOfServiceState() { return outOfService; }

    void start() {
        setState(idle);
    }

    public void insertCard(Card card) {
        state.insertCard(card);
    }
    public void enterPin(String pin) {
        state.enterPin(pin);
    }
    public void selectTransaction(TransactionType type) {
        state.selectTransaction(type);
    }
    public void requestWithdraw(int amount) {
        state.requestWithdraw(amount);
    }
    public void depositCash(java.util.Map<Integer,Integer> notes) {
        state.depositCash(notes);
    }
    public void printMiniStatement() {
        state.printMiniStatement();
    }
    public void changePin(String oldPin, String newPin) {
        state.changePin(oldPin, newPin);
    }
    public void ejectCard() {
        state.ejectCard();
    }

    void setCurrentCard(Card card) { this.currentCard = card; }
    Card getCurrentCard() { return currentCard; }
    void setSelectedTransaction(TransactionType t) { this.selectedTransaction = t; }
    TransactionType getSelectedTransaction() { return selectedTransaction; }
}
