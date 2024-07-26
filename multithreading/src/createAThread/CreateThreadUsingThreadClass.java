package createAThread;

public class CreateThreadUsingThreadClass extends Thread{

    public void run(){
        System.out.println("thread is running...");
    }

    public static void main(String[] args) {

        CreateThreadUsingThreadClass c=new CreateThreadUsingThreadClass();
        c.start();

    }
}
