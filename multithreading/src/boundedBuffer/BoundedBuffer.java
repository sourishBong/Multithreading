package boundedBuffer;

public class BoundedBuffer<T> {

    private final T[] buffer;

    private int head=0;
    private int tail=0;
    private int count=0;


    public BoundedBuffer(int capacity) {
        this.buffer = (T[]) new Object[capacity];
    }

    public synchronized void put(T item) throws InterruptedException{
        while(count== buffer.length){
            wait();
        }

        buffer[tail]=item;
        tail=(tail+1)% buffer.length;
        count++;
        notifyAll();
    }

    public synchronized T take() throws InterruptedException{
        while(count==0){
            wait();
        }

        T item=buffer[head];
        head=(head+1)% buffer.length;
        count--;
        notifyAll();
        return item;
    }
}
