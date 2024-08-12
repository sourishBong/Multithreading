package fork.join.framework;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest extends RecursiveAction {

    private int simulatedWork;

    public ForkJoinTest(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }


    @Override
    protected void compute() {

        //if the task is too large then we split and execute the tasks in parallel.
        if(simulatedWork>100){

            System.out.println("Parallel execution and split the tasks..."+simulatedWork);

            ForkJoinTest action1=new ForkJoinTest(simulatedWork/2);
            ForkJoinTest action2=new ForkJoinTest(simulatedWork/2);

//            action1.fork();
//            action2.fork();
//
//            action1.join();
//            action2.join();

            invokeAll(action1,action2);
        }else{
            System.out.println("Task is rather small so sequential execution is fine");
        }
    }

    public static void main(String[] args) {

        //ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTest action=new ForkJoinTest(800);
        action.invoke();

    }
}
