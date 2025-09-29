package atm;

import java.util.*;

public interface BankService {
    boolean verifyPin(String cardNumber, String pin);
    Optional<Integer> getBalance(String cardNumber);
    boolean withdraw(String cardNumber, int amount);
    boolean deposit(String cardNumber, int amount);
    List<TransactionRecord> miniStatement(String cardNumber);
    boolean changePin(String cardNumber, String oldPin, String newPin);
    void createAccount(String cardNumber, String pin, int initialBalance);
}
