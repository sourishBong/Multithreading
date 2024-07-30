package reentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock reentrantLock=new ReentrantLock();

    public void performTask() throws InterruptedException {
    reentrantLock.lock();
    try{
        //critical section
        System.out.println(Thread.currentThread().getName()+" is performing task");
        Thread.sleep(1000);
    }finally{
        reentrantLock.unLock();
    }
    }

    public static void main(String[] args) {

        ReentrantLockDemo demo = new ReentrantLockDemo();

        Runnable task = () -> {
            try {
                for (int i = 0; i < 5; i++) {
                    demo.performTask();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
    }
}
