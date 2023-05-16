public class ConcurrencyExamplesApplication {
    public static void main(String[] args) {
        //all examples are take from OCP Java SE 11 Study Guide published by Sybex (Wiley Group) - ISBN 978-1-119-61913-0
        example1();
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
}
