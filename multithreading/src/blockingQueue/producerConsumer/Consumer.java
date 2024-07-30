package blockingQueue.producerConsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private final BlockingQueue<Integer> queue;


    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                Integer item = queue.take();
                System.out.println("Consumed: "+item);
                if(item==9) break;
            }
        }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}
