package synchronization;

public class SynchronizationDemo2 {

    private static int counter1;
    private static int counter2;

    private static Object lock1=new Object();
    private static Object lock2=new Object();

    public static void increment1(){
        synchronized (lock1) {
            counter1++;
        }
    }

    public static void increment2(){
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void process() throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    increment1();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    increment2();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter1);
        System.out.println(counter2);
    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }
}
