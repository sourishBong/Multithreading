package synchronization;

public class SynchronizationDemo {

    private static int counter;

    public synchronized static void increment(){
        counter++;
    }

    public static void process() throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    increment();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }
}
