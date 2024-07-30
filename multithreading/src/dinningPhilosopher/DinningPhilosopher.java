package dinningPhilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DinningPhilosopher {

    public static void main(String[] args) {
        Lock[] chopsticks=new ReentrantLock[5];

        for(int i=0;i<5;i++){
           chopsticks[i]=new ReentrantLock();
        }

        Philosopher[] philosophers=new Philosopher[5];
        Thread[] threads=new Thread[5];

        for(int i=0;i< philosophers.length;i++){
            Lock leftChopstick=chopsticks[i];
            Lock rightChopstick=chopsticks[(i+1)% chopsticks.length];

            // Ensure a consistent order of acquiring locks to prevent deadlock
            if(i%2==0) {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }else{
                philosophers[i]=new Philosopher(rightChopstick,leftChopstick);
                }

            threads[i]=new Thread(philosophers[i],"Philospher"+(i+1));
            threads[i].start();

            }
        }
}
