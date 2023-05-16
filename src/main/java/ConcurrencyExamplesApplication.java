import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyExamplesApplication {
    public static void main(String[] args) throws InterruptedException {
        //all examples are take from OCP Java SE 11 Study Guide published by Sybex (Wiley Group) - ISBN 978-1-119-61913-0
        System.out.println("---- Example 1 ----");
        example1();

        Thread.sleep(2000);
        System.out.println("---- Example 2 ----");
        //Polling with sleep to aid thread scheduling
        example2();

        Thread.sleep(2000);
        System.out.println("---- Example 3 ----");
        //Polling with sleep to aid thread scheduling
        example3();

    }

    private static void example1() {
        //order is not guaranteed because of thread scheduling
        System.out.println("begin");
        //extends Thread class
        new ReadInventoryThread().start();

        //implements Runnable interface
        new Thread(new PrintData()).start();

        //extends Thread class
        new ReadInventoryThread().start();
        System.out.println("end");
    }

    private static void example2() throws InterruptedException {
        new Thread(() -> {
            for (int i =0; i < 500; i++) CheckResults.counter++;
        }).start();

        while (CheckResults.counter < 100) {
            System.out.println("Not reached yet");
            Thread.sleep(1000);
        }
    }

    private static void example3() {
        ExecutorService service = null;
        Runnable task1 = () -> System.out.println("Printing zoo inventory");
        Runnable task2 = () -> {
            for (int i = 0; i < 3; i++) System.out.println("Printing record: " + i);
        };

        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(task1);
            service.execute(task2);
            service.execute(task1);
            System.out.println("end");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
