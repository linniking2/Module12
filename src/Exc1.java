import java.util.Date;

public class Exc1 {
    public static void main(String[] args) {

        Thread timeThread = new Thread(() -> {
            while (true) {
                Date now = new Date();
                System.out.println("Поточний час: " + now);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("Минуло 5 секунд.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        timeThread.start();
        messageThread.start();
    }
}
