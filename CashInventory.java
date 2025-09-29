package atm;

import java.util.*;

public class CashInventory {
    private final Map<Integer, Integer> notes = new TreeMap<>(Collections.reverseOrder());
    public void addNotes(int denomination, int count) {
        notes.put(denomination, notes.getOrDefault(denomination, 0) + count);
    }
    public Map<Integer, Integer> snapshot() {
        return new LinkedHashMap<>(notes);
    }
    public boolean canDispense(Map<Integer, Integer> required) {
        for (Map.Entry<Integer, Integer> e : required.entrySet()) {
            int den = e.getKey();
            int cnt = e.getValue();
            if (notes.getOrDefault(den, 0) < cnt) return false;
        }
        return true;
    }
    public void dispense(Map<Integer, Integer> dispensed) {
        for (Map.Entry<Integer, Integer> e : dispensed.entrySet()) {
            int den = e.getKey();
            int cnt = e.getValue();
            int avail = notes.getOrDefault(den, 0);
            notes.put(den, avail - cnt);
        }
    }
}
