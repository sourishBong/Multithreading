package blockingQueue.producerConsumer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerImplementation {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(5);

        Thread producerThread=new Thread(new Producer(queue));
        Thread consumerThread=new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();

    }
}
