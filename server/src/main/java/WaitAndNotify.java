public class WaitAndNotify {

    private final Object mon = new Object();
    private char letterQueue = 'A';

    public static void main(String[] args) {
        WaitAndNotify main = new WaitAndNotify();

        new Thread(main::printA).start();
        new Thread(main::printB).start();
        new Thread(main::printC).start();

    }

    private void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letterQueue != 'A') {
                        mon.wait();
                    }
                    System.out.println("A");
                    letterQueue = 'B';
                    mon.notifyAll();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letterQueue != 'B') {
                        mon.wait();
                    }
                    System.out.println("B");
                    letterQueue = 'C';
                    mon.notifyAll();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (letterQueue != 'C') {
                        mon.wait();
                    }
                    System.out.println("C");
                    letterQueue = 'A';
                    mon.notifyAll();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}