package atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger count=new AtomicInteger(0);

    public void increment(){
        count.incrementAndGet();
    }

    public int getCount(){
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {

        Counter counter=new Counter();

        Runnable task=()->{
          for(int i=0;i<1000;i++){
              counter.increment();
          }
        };

        Thread t1=new Thread(task);
        Thread t2=new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("The total count is "+counter.getCount());
    }
}
