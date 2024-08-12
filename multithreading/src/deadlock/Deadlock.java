package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {

    private Lock lock1=new ReentrantLock();
    private Lock lock2=new ReentrantLock();

    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 has acquired Lock1");

        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        lock2.lock();
        System.out.println("Worker1 has acquired Lock2");

        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        lock2.lock();
        System.out.println("Worker2 has acquired Lock2");

        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        lock1.lock();
        System.out.println("Worker1 has acquired Lock1");

        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) throws InterruptedException {

        Deadlock deadlock=new Deadlock();

        Thread t1=new Thread(deadlock::worker1);
        Thread t2=new Thread(deadlock::worker2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
