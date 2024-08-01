package rate.limiting.using.token.bucket;

import java.util.HashSet;
import java.util.Set;

public class RateLimitingUsingTokenBucket {

    private int MAX_TOKENS;

    private long lastRequested=System.currentTimeMillis();

    private long possibleToken=0;

    public RateLimitingUsingTokenBucket(int MAX_TOKENS) {
        this.MAX_TOKENS = MAX_TOKENS;
    }

    public synchronized void  getToken() throws InterruptedException{

        possibleToken=possibleToken+(System.currentTimeMillis()-lastRequested)/1000;

        if(possibleToken>MAX_TOKENS){
            possibleToken=MAX_TOKENS;
        }

        if(possibleToken==0){
            Thread.sleep(1000);
        }else{
            possibleToken--;
        }

        lastRequested=System.currentTimeMillis();

        System.out.println("Granting "+Thread.currentThread().getName()+" to token at "+System.currentTimeMillis()/1000);
    }


    public static void main(String[] args) throws InterruptedException{

        Set<Thread> allThreads=new HashSet<>();
        final RateLimitingUsingTokenBucket token=new RateLimitingUsingTokenBucket(1);

        for(int i=0;i<10;i++){

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        token.getToken();
                    }catch(InterruptedException e){
                        System.out.println("Something went wrong");
                    }
                }
            });

            thread.setName("Thread_"+(i+1));
            allThreads.add(thread);
        }
            for(Thread t:allThreads){
                t.start();
            }

            for(Thread t:allThreads){
                t.join();
            }



    }
}
