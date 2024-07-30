package reentrantLock;

public class ReentrantLock {

    private boolean isLocked=false;

    private Thread lockingThread=null;

    private int lockCount=0;

    public synchronized void lock() throws InterruptedException {
        while(isLocked && Thread.currentThread()!=lockingThread){
            wait();
        }
        isLocked=true;
        lockCount++;
        lockingThread=Thread.currentThread();
    }

    public synchronized void unLock(){
        if(Thread.currentThread()==lockingThread){
            lockCount--;
            if(lockCount==0){
                isLocked=false;
                lockingThread=null;
                notifyAll();
            }
        }
    }
}
