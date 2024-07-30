package barriers;

public class BarrierDemo {

    public static void main(String[] args) {

        Barrier barrier=new Barrier(3);

        Runnable task=()->{
            try {
                System.out.println(Thread.currentThread().getName()+" is waiting at barrier");
                barrier.await();
                System.out.println(Thread.currentThread().getName()+" has crossed the barrier");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1=new Thread(task);
        Thread t2=new Thread(task);
        Thread t3=new Thread(task);

        t1.start();
        t2.start();
        t3.start();
    }
}
