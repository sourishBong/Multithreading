public class EvenoddPrinter {

    private final int limit;
    private int count=1;


    public EvenoddPrinter(int limit) {
        this.limit = limit;
    }
    
    public void printEven(){
        synchronized (this){
            while(count<limit){
                while(count%2==1){
                    try{
                        wait();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Even: "+count++);
                notify();
            }
        }
    }

    public void printOdd(){
        synchronized (this){
            while(count<limit){
                while(count%2==0){
                    try{
                        wait();
                    }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Odd: "+count++);
                notify();
            }
        }
    }

    public static void main(String[] args) {

        EvenoddPrinter printer=new EvenoddPrinter(10);

        Thread t1=new Thread(printer::printEven);
        Thread t2=new Thread(printer::printOdd);

        t1.start();
        t2.start();
    }
}
