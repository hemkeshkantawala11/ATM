package atm;

import java.time.LocalDateTime;

public class TransactionRecord {
    private final LocalDateTime when;
    private final TransactionType type;
    private final int amount;
    private final String note;
    public TransactionRecord(TransactionType type, int amount, String note) {
        this.when = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.note = note;
    }
    public LocalDateTime getWhen() { return when; }
    public TransactionType getType() { return type; }
    public int getAmount() { return amount; }
    public String getNote() { return note; }
    @Override
    public String toString() {
        return when + " | " + type + " | " + amount + " | " + note;
    }
}
