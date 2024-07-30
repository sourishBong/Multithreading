package dinningPhilosopher;

import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{

    private final Lock leftChopstick;
    private final Lock rightChopstick;

    public Philosopher(Lock leftChopstick, Lock rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" "+action);
        Thread.sleep((int) (Math.random()*1000));
    }

    @Override
    public void run() {
        try{
            while(true){
                doAction("Thinking");
                if(leftChopstick.tryLock()){
                    try{
                        if(rightChopstick.tryLock()){
                            try{
                                doAction("Eating");
                            }finally {
                                rightChopstick.unlock();
                            }
                        }
                    }finally {
                        leftChopstick.unlock();
                    }
                }
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
