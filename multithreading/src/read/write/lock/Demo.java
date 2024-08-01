package read.write.lock;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        final ReadWriteLock rwl = new ReadWriteLock();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                   System.out.println("Attempting to acquire write lock by thread t1: "+System.currentTimeMillis());
                   rwl.acquireWriteLock();
                   System.out.println("Acquired write lock by thread t1: "+System.currentTimeMillis());

                   // Simulates write lock being held indefinitely
                   for (; ; ) {
                       Thread.sleep(500);
                   }

               }catch(InterruptedException e){
                   Thread.currentThread().interrupt();
               }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    System.out.println("Attempting to acquire write lock in t2: " + System.currentTimeMillis());
                    rwl.acquireWriteLock();
                    System.out.println("write lock acquired t2: " + System.currentTimeMillis());

                } catch (InterruptedException ie) {

                }
            }
        });

        Thread tReader1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    rwl.acquireReadLock();
                    System.out.println("Read lock acquired: " + System.currentTimeMillis());

                } catch (InterruptedException ie) {

                }
            }
        });

        Thread tReader2 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Read lock about to release: " + System.currentTimeMillis());
                rwl.releaseReadLock();
                System.out.println("Read lock released: " + System.currentTimeMillis());
            }
        });

        tReader1.start();
        t1.start();
        Thread.sleep(3000);
        tReader2.start();
        Thread.sleep(1000);
        t2.start();
        tReader1.join();
        tReader2.join();
        t2.join();

    }
}
