package barriers;

public class Barrier {

    private final int totalThreads;

    private int waitingThreads=0;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public synchronized void await() throws InterruptedException {
        waitingThreads++;
        if(waitingThreads<totalThreads){
            wait();
        }else{
            waitingThreads=0;
            notifyAll();
        }
    }
}
