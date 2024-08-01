package semaphore.implementation;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        final CountingSemaphore countingSemaphore=new CountingSemaphore(1);

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0;i<5;i++){
                        countingSemaphore.acquire();
                        System.out.println("Ping "+i);
                    }
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0;i<5;i++){
                        countingSemaphore.release();
                        System.out.println("Pong "+i);
                    }
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });

        t2.start();
        t1.start();
        t1.join();
        t2.join();
    }
}
