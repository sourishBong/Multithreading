package synchronization;

public class Synchronization3 {

    public void process1() throws InterruptedException {
        synchronized (this){
            System.out.println("Executing process 1");
            wait();
            System.out.println("Again executing process 1");
        }
    }

    public void process2() throws InterruptedException {
        synchronized (this){
            System.out.println("Executing process 2");
            notify();
            Thread.sleep(5000);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Synchronization3 demo=new Synchronization3();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.process1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.process2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
