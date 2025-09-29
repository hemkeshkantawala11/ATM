package ATM;

public interface PinEntry {
    boolean enterPin(String cardNumber, String pin, BankService bankService);
}
