package fork.join.framework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class PrintingIntegers extends RecursiveAction {

    private List<Integer> nums;

    public PrintingIntegers(List<Integer> nums) {
        this.nums = nums;
    }

    @Override
    protected void compute() {
// the problem is small enough (containing 2 items) so we use
        // standard sequential print operation
        if(nums.size() < 2) {
            for(Integer num : nums)
                System.out.println(num);
        } else {
            System.out.println("Splitting:"+nums.size());
            // otherwise we split the problem into 2 sub-problems
            // [a,b,c] --> [a] and [b,c]
            // [a,b,c,d] --> [a,b] and [c,d]
            List<Integer> left = nums.subList(0, nums.size() / 2);
            List<Integer> right = nums.subList(nums.size() / 2, nums.size());

            PrintingIntegers action1 = new PrintingIntegers(left);
            PrintingIntegers action2 = new PrintingIntegers(right);

            // it means these actions are thrown into the pool
            // fork-join assigns different threads to different tasks
            // so these will be executed with different treads
            invokeAll(action1, action2);
        }
    }

    public static void main(String[] args) {

        List<Integer> nums= Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        PrintingIntegers action=new PrintingIntegers(nums);
        action.invoke();
    }
}
