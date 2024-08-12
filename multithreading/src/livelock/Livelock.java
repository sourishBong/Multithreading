package livelock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {
    private Lock lock1=new ReentrantLock();
    private Lock lock2=new ReentrantLock();

    public void worker1(){

        while(true){
            try{
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker 1 acquires lock 1");
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Worker 1 tries to get lock2");

            if(lock2.tryLock()){
                System.out.println("Worker 1 acquires lock 2");
                lock2.unlock();
            }else{
                System.out.println("Worker 1 can not acquire lock2");
                continue;
            }
            break;
        }
            lock1.unlock();
            lock2.unlock();
    }

    public void worker2(){
        while(true){
            try{
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println("Worker 2 acquires lock 2");
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Worker 2 tries to get lock1");

            if(lock1.tryLock()){
                System.out.println("Worker 2 acquires lock 1");
                lock1.unlock();
            }else{
                System.out.println("Worker 2 can not acquire lock1");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        Livelock livelock=new Livelock();

        new Thread(livelock::worker1,"worker1").start();
        new Thread(livelock::worker2,"worker2").start();
    }
}
