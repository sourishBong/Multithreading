package unisex.bathroom.problem;

import java.util.concurrent.Semaphore;

public class UnisexBathroom {

    static String MEN="men";
    static String WOMEN="women";
    static String NONE="none";

    String inUseBy=NONE;
    int empInBathroom=0;
    Semaphore maxEmp=new Semaphore(3);

    void useBathroom(String name)throws InterruptedException{
        System.out.println(name+" using the bathroom. Current employee count="+empInBathroom+" "+System.currentTimeMillis());
        Thread.sleep(3000);
        System.out.println("\n" + name + " done using bathroom " + System.currentTimeMillis());
    }

    public void maleUseBathroom(String name)throws InterruptedException{

        synchronized (this){
            while(inUseBy.equals(WOMEN)) {
                this.wait();
            }
            maxEmp.acquire();
            empInBathroom++;
            inUseBy=MEN;
        }

        useBathroom(name);
        maxEmp.release();

        synchronized (this){
            empInBathroom--;

            if(empInBathroom==0)
                inUseBy=NONE;
            this.notifyAll();
        }

    }

    public void femaleUseBathroom(String name) throws InterruptedException {

        synchronized (this){
            while(inUseBy.equals(MEN)){
                this.wait();
            }
            maxEmp.acquire();
            empInBathroom++;
            inUseBy=WOMEN;
        }

        useBathroom(name);
        maxEmp.release();

        synchronized (this){
            empInBathroom--;

            if(empInBathroom==0)
                inUseBy=NONE;
            this.notifyAll();
        }

    }

}
