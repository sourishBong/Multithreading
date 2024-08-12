package blocking.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed{

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis()+duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        // this is the method that compares objects
        if(duration<((DelayedWorker) other).getDuration())
            return -1;
        if(duration>((DelayedWorker) other).getDuration())
            return 1;

        return 0;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DelayWorker{" +
                "duration=" + duration +
                ", message='" + message + '\'' +
                '}';
    }
}

public class DelayQueueExample {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayedWorker> queue=new DelayQueue<>();

        queue.put(new DelayedWorker(2000,"This is the first message"));
        queue.put(new DelayedWorker(10000,"This is the second message"));
        queue.put(new DelayedWorker(4500,"This is the third message"));

        while(!queue.isEmpty()){
            System.out.println(queue.take());
        }
    }
}
