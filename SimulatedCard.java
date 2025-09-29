package atm;

public class SimulatedCard implements Card {
    private final String number;
    public SimulatedCard(String number) {
        this.number = number;
    }
    @Override
    public String getCardNumber() {
        return number;
    }
}
