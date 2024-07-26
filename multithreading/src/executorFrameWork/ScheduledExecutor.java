package executorFrameWork;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarket implements Runnable{

    @Override
    public void run() {
        System.out.println("Updating and Downloading stock realted data from web");
    }


}

public class ScheduledExecutor {

    public static void main(String[] args) {

        ScheduledExecutorService executor= Executors.newScheduledThreadPool(1);

       executor.scheduleAtFixedRate(new StockMarket(),1000,2000, TimeUnit.MILLISECONDS);

      // executor.shutdown();
    }
}
