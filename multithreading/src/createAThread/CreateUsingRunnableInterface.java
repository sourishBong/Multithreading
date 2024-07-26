package createAThread;

public class CreateUsingRunnableInterface {

    public static void main(String[] args) {
        Thread t=new Thread(new Runnable() {

            //run method is invoked by thread t when it starts
            @Override
            public void run() {
                System.out.println("Run thread");
            }
        });
        //starting the thread
        t.start();
    }
}
