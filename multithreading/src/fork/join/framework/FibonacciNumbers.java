package fork.join.framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciNumbers extends RecursiveTask<Integer> {

    private int n;

    public FibonacciNumbers(int n) {
        this.n = n;
    }


    @Override
    protected Integer compute() {
        // F(0) = F(1) = 0
        if(n <= 1)
            return n;

        FibonacciNumbers fib1 = new FibonacciNumbers(n-1);
        FibonacciNumbers fib2 = new FibonacciNumbers(n-2);

        fib1.fork();
        fib2.fork();

        return fib1.join()+ fib2.join();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new FibonacciNumbers(6)));
    }
}
