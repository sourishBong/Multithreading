package createAThread;

class ThreadCall implements Runnable{

    @Override
    public void run() {
        System.out.println("Hi From thread Call");
    }
}

public class CreateThreadByImplementingRunnable {

    public static void main(String[] args) {

        ThreadCall tc=new ThreadCall();

        Thread t=new Thread(tc);
        t.start();
    }
}
