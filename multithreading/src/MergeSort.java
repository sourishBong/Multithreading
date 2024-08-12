import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class MergeSort extends RecursiveAction {

    private int[] nums;
    
    public MergeSort(int[] nums){
        this.nums=nums;
    }

    @Override
    protected void compute() {
        if(nums.length<=10){
            mergeSort(nums);
            return;
        }

        int middleIndex = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        MergeSort task1 = new MergeSort(left);
        MergeSort task2 = new MergeSort(right);

        invokeAll(task1, task2);

        merge(left, right, nums);
    }

    private void mergeSort(int[] nums) {
        if(nums.length<=1){
            return;
        }

        int middleIndex=nums.length/2;

        int left[]= Arrays.copyOfRange(nums,0,middleIndex);
        int right[]=Arrays.copyOfRange(nums,middleIndex,nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(left,right,nums);
    }

    private void merge(int[] leftSubarray, int[] rightSubarray, int[] originalArray) {

        int i=0;
        int j=0;
        int k=0;

        while (i < leftSubarray.length && j < rightSubarray.length) {
            if (leftSubarray[i] < rightSubarray[j])
                originalArray[k++] = leftSubarray[i++];
            else
                originalArray[k++] = rightSubarray[j++];
        }

        while (i < leftSubarray.length)
            originalArray[k++] = leftSubarray[i++];

        while (j < rightSubarray.length)
            originalArray[k++] = rightSubarray[j++];
    }

    public static void main(String[] args) {
        ForkJoinPool pool=new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        int nums[]={5,3,1,50,40,32,70,63,91,2,51};
        MergeSort sort=new MergeSort(nums);
        pool.invoke(sort);
        System.out.println(Arrays.toString(nums));
    }
}
