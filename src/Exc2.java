import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Exc2 {
    private int n;
    private int current = 1;
    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

    public Exc2(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                queue.put("fizz");
                printFizz.run();
                current++;
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                queue.put("buzz");
                printBuzz.run();
                current++;
            }
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            queue.put("fizzbuzz");
            printFizzBuzz.run();
            current++;
        }
    }

    public void number(Runnable printNumber) throws InterruptedException {
        while (current <= n) {
            if (current % 3 != 0 && current % 5 != 0) {
                queue.put(Integer.toString(current));
                printNumber.run();
                current++;
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
       Exc2 exc2 = new Exc2(n);

        Thread threadA = new Thread(() -> {
            try {
                exc2.fizz(() -> System.out.print("fizz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                exc2.buzz(() -> System.out.print("buzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                exc2.fizzbuzz(() -> System.out.print("fizzbuzz, "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                exc2.number(() -> {
                    try {
                        System.out.print(exc2.queue.take() + ", ");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
