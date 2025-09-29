package atm;

import java.util.*;

public class CashDispenser {
    private final CashInventory inventory;
    private final Denomination[] denominations;
    public CashDispenser(CashInventory inventory, Denomination[] denominations) {
        this.inventory = inventory;
        this.denominations = denominations;
    }
    public Optional<Map<Integer, Integer>> dispenseAmount(int amount) {
        Map<Integer, Integer> result = new LinkedHashMap<>();
        int remaining = amount;
        for (Denomination d : denominations) {
            int den = d.getValue();
            int avail = inventory.snapshot().getOrDefault(den, 0);
            int use = Math.min(remaining / den, avail);
            if (use > 0) {
                result.put(den, use);
                remaining -= use * den;
            }
        }
        if (remaining != 0 || !inventory.canDispense(result)) return Optional.empty();
        inventory.dispense(result);
        return Optional.of(result);
    }
}
