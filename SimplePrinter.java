package atm;

public class SimplePrinter implements Printer {
    @Override
    public void print(String text) {
        System.out.println("---- PRINTER OUTPUT ----");
        System.out.println(text);
        System.out.println("---- END PRINTER ----");
    }
}
