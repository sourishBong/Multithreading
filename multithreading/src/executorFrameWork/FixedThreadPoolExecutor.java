package executorFrameWork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Work implements Runnable{

    private int id;

    Work(int id){
        this.id=id;
    }
    @Override
    public void run() {
        System.out.println("Task with id="+id+" is in work with the Thread="+Thread.currentThread().getId());
        Long duration=(long) Math.random()*5;
        try {
            //another way to make a  thread sleep
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class FixedThreadPoolExecutor {

    public static void main(String[] args) {

        //creating single thread executor
        //it is a single thread that will execute tasks sequentially one after the other
        ExecutorService executor= Executors.newFixedThreadPool(3);

        for(int i=0;i<5;i++){
            executor.execute(new Work(i+1));
        }
        executor.shutdown();
    }
}
