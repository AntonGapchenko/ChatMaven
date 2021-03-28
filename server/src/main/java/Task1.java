
public class Task1 {
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final int COUNT = 5;
    private static final Object MONITOR = new Object();
    private static String nextLetter = A;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < COUNT; i++) {
                        while (!nextLetter.equals(A)) {
                            try {
                                MONITOR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(A);
                        nextLetter = B;
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < COUNT; i++) {
                        while (!nextLetter.equals(B)) {
                            try {
                                MONITOR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(B);
                        nextLetter = C;
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MONITOR) {
                    for (int i = 0; i < COUNT; i++) {
                        while (!nextLetter.equals(C)) {
                            try {
                                MONITOR.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(C);
                        nextLetter = A;
                        MONITOR.notifyAll();
                    }
                }
            }
        }).start();
    }

}
