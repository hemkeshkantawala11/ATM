package atm;

public interface CardReader {
    void insert(Card card);
    Card current();
    void eject();
}
