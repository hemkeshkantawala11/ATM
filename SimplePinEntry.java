package ATM;

public class SimplePinEntry implements PinEntry {
    @Override
    public boolean enterPin(String cardNumber, String pin, BankService bankService) {
        return bankService.verifyPin(cardNumber, pin);
    }
}
