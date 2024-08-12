package fork.join.framework;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMaxTask extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelMaxTask(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }


    @Override
    protected Long compute() {

        // if the array is small we use standard sequential approach
        if(highIndex-lowIndex<1000){
            return SequentialMaxFinding();
        }else{
            //we have to use parallelisation
            int middleIndex=(highIndex+lowIndex)/2;

            ParallelMaxTask task1=new ParallelMaxTask(nums,lowIndex,middleIndex);
            ParallelMaxTask task2=new ParallelMaxTask(nums,middleIndex+1,highIndex);

            invokeAll(task1,task2);
            return Math.max(task1.join(),task2.join());
        }
    }

    private Long SequentialMaxFinding() {

        long max=nums[lowIndex];

        for(int i=lowIndex+1;i<highIndex;i++){
            if(nums[i]>max){
                max=nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        long[] num=createNumbers(1000);

        ForkJoinPool pool=new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ParallelMaxTask parallel=new ParallelMaxTask(num,0,num.length);
        System.out.println("Max="+pool.invoke(parallel));
    }

    private static long[] createNumbers(int n){
        Random random=new Random();
        long[] nums=new long[n];

        for(int i=0;i<nums.length;i++){
            nums[i]= random.nextInt(1000);
        }
        return nums;
    }
}
