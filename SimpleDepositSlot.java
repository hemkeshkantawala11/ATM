package ATM;

import java.util.Map;

public class SimpleDepositSlot implements DepositSlot {
    @Override
    public boolean acceptDeposit(Map<Integer, Integer> notes) {
        int total = notes.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
        System.out.println("Deposit accepted: " + total);
        return true;
    }
}
