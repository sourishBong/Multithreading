package blockingQueue.producerConsumer;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try{
            for(int i=0;i<10;i++){
                queue.put(i);
                System.out.println("Produced:" +i);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
