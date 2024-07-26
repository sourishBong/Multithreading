package executorFrameWork;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable{

    private int id;

    Task(int id){
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
public class SingleThreadExecutor {

    public static void main(String[] args) {

        //creating single thread executor
        //it is a single thread that will execute tasks sequentially one after the other
        ExecutorService executor= Executors.newSingleThreadExecutor();

        for(int i=0;i<5;i++){
            executor.execute(new Task(i));
    }
        // shuting down executor
        executor.shutdown();
        try{
            //blocks until all tasks are completed execution after a shutdown request
            //or the timeout occurs, or the current thread is interrupted
            if(!executor.awaitTermination(1000,TimeUnit.MILLISECONDS)){
               executor.shutdownNow();
            }
        }catch(InterruptedException e){
               executor.shutdownNow();
        }
    }
}
