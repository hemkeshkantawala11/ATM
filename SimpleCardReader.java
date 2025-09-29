package ATM;

public class SimpleCardReader implements CardReader {
    private Card current;
    @Override
    public void insert(Card card) {
        current = card;
    }
    @Override
    public Card current() {
        return current;
    }
    @Override
    public void eject() {
        current = null;
    }
}
