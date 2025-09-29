package ATM;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Denomination[] denominations = new Denomination[] {
                new Denomination(2000),
                new Denomination(500),
                new Denomination(200),
                new Denomination(100)
        };
        CashInventory inventory = new CashInventory();
        inventory.addNotes(2000, 10);
        inventory.addNotes(500, 20);
        inventory.addNotes(200, 30);
        inventory.addNotes(100, 100);

        CardReader cardReader = new SimpleCardReader();
        PinEntry pinEntry = new SimplePinEntry();
        CashDispenser dispenser = new CashDispenser(inventory, denominations);
        Printer printer = new SimplePrinter();
        DepositSlot depositSlot = new SimpleDepositSlot();

        BankService bank = new InMemoryBankService();
        bank.createAccount("1111222233334444", "1234", 25000);
        bank.createAccount("9999888877776666", "0000", 10000);

        ATM atm = new ATM(cardReader, pinEntry, dispenser, printer, depositSlot, bank);
        atm.start();

        SimulatedCard card = new SimulatedCard("1111222233334444");
        atm.insertCard(card);
        atm.enterPin("1234");
        atm.selectTransaction(TransactionType.WITHDRAW);
        atm.requestWithdraw(3700);
        atm.printMiniStatement();
        atm.changePin("1234", "4321");
        atm.ejectCard();

        atm.insertCard(new SimulatedCard("9999888877776666"));
        atm.enterPin("0000");
        atm.selectTransaction(TransactionType.DEPOSIT);
        Map<Integer, Integer> depositNotes = new HashMap<>();
        depositNotes.put(2000, 2);
        depositNotes.put(500, 1);
        atm.depositCash(depositNotes);
        atm.requestWithdraw(2800);
        atm.ejectCard();
    }
}
