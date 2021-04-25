package partthree.four;

public class Main {

    Character lastChar = 'C';
    final int PRINT_COUNT = 5;

    public static void main(String[] args) {
        new Main().startJob();
    }

    public void startJob() {
        new Thread(this::printB).start();
        new Thread(this::printC).start();
        new Thread(this::printA).start();
    }

    public synchronized void printA() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            while (lastChar != 'C') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lastChar = 'A';
            System.out.print(lastChar);
            notifyAll();
        }

    }

    public synchronized void printB() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            while (lastChar != 'A') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lastChar = 'B';
            System.out.print(lastChar);
            notifyAll();
        }

    }

    public synchronized void printC() {
        for (int i = 0; i < PRINT_COUNT; i++) {
            while (lastChar != 'B') {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            lastChar = 'C';
            System.out.print(lastChar);
            notifyAll();
        }

    }
}
