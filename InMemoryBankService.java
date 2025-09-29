package ATM;

import java.util.*;

public class InMemoryBankService implements BankService {
    private static class Account {
        String cardNumber;
        String pin;
        int balance;
        List<TransactionRecord> records = new ArrayList<>();
        Account(String cardNumber, String pin, int balance) {
            this.cardNumber = cardNumber;
            this.pin = pin;
            this.balance = balance;
        }
    }
    private final Map<String, Account> accounts = new HashMap<>();
    @Override
    public boolean verifyPin(String cardNumber, String pin) {
        Account a = accounts.get(cardNumber);
        return a != null && a.pin.equals(pin);
    }
    @Override
    public Optional<Integer> getBalance(String cardNumber) {
        Account a = accounts.get(cardNumber);
        return a == null ? Optional.empty() : Optional.of(a.balance);
    }
    @Override
    public boolean withdraw(String cardNumber, int amount) {
        Account a = accounts.get(cardNumber);
        if (a == null) return false;
        if (a.balance < amount) return false;
        a.balance -= amount;
        a.records.add(new TransactionRecord(TransactionType.WITHDRAW, amount, "ATM Withdraw"));
        return true;
    }
    @Override
    public boolean deposit(String cardNumber, int amount) {
        Account a = accounts.get(cardNumber);
        if (a == null) return false;
        a.balance += amount;
        a.records.add(new TransactionRecord(TransactionType.DEPOSIT, amount, "ATM Deposit"));
        return true;
    }
    @Override
    public List<TransactionRecord> miniStatement(String cardNumber) {
        Account a = accounts.get(cardNumber);
        if (a == null) return Collections.emptyList();
        int from = Math.max(0, a.records.size() - 5);
        return new ArrayList<>(a.records.subList(from, a.records.size()));
    }
    @Override
    public boolean changePin(String cardNumber, String oldPin, String newPin) {
        Account a = accounts.get(cardNumber);
        if (a == null) return false;
        if (!a.pin.equals(oldPin)) return false;
        a.pin = newPin;
        a.records.add(new TransactionRecord(TransactionType.CHANGE_PIN, 0, "PIN Changed"));
        return true;
    }
    @Override
    public void createAccount(String cardNumber, String pin, int initialBalance) {
        accounts.put(cardNumber, new Account(cardNumber, pin, initialBalance));
    }
}
