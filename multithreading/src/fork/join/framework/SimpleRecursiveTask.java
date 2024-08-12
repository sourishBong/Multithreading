package fork.join.framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Double> {

    private Double num;

    public SimpleRecursiveTask(Double num) {
        this.num = num;
    }

    @Override
    protected Double compute() {

        if (num > 100) {

            System.out.println("Splitting:" + num);
            SimpleRecursiveTask task1 = new SimpleRecursiveTask(num / 2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(num / 2);

            //we add the tasks to the thread pool(parallel
            task1.fork();
            task2.fork();

            //wait for these tasks to be finished
            Double solution = 0.0;
            solution = solution + task1.join();
            solution = solution + task2.join();

            return solution;

        } else {
            //problem is samll so using sequential approach
            System.out.println("problem is samll so using sequential approach:"+num);
            return 2*num;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool=new ForkJoinPool();
        SimpleRecursiveTask task=new SimpleRecursiveTask(10000.0);
        System.out.println(pool.invoke(task));
    }
}

