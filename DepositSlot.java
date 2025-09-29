package atm;

import java.util.Map;

public interface DepositSlot {
    boolean acceptDeposit(Map<Integer, Integer> notes);
}
