package ch.zhaw.prog2.printer;

public class PrinterB {

    // test program
    public static void main(String[] arg) {
        Thread a = new Thread(new PrinterRunnable('.'),"PrinterA");
        Thread b = new Thread(new PrinterRunnable( '*'),"PrinterB");
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    private static class PrinterRunnable implements Runnable {
        char symbol;

        public PrinterRunnable(char symbol) {
            this.symbol = symbol;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run started...");
            for (int i = 1; i < 100; i++) {
                System.out.print(symbol);
                Thread.yield();
            }
            System.out.println('\n' + Thread.currentThread().getName() + " run ended.");
        }
    }
}
