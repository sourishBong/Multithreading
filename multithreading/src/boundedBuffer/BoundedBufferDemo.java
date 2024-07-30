package boundedBuffer;

public class BoundedBufferDemo {

    public static void main(String[] args) {

        BoundedBuffer<Integer> buffer=new BoundedBuffer<>(5);

        Runnable producer=()->{
            try {
                for (int i = 0; i < 10; i++) {
                        buffer.put(i);
                    System.out.println("Produced"+i);
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer=()->{
            for(int i=0;i<10;i++){
                try {
                    int item=buffer.take();
                    System.out.println("Consumed"+item);
                } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                }

            }
        };

        Thread t1=new Thread(producer);
        Thread t2=new Thread(consumer);

        t1.start();
        t2.start();
    }
}
