package array;

import java.util.ArrayList;

public class MinSubArray {

	public int minSubArray(ArrayList<Integer> nums) {
        //sum[i] = min{sum[j], } + nums[i], if min is >0, sum[i] = nums[i]
        //
        int len = nums.size();
        if(len == 0) return 0;
        int[] dp = new int[len];
        dp[0] = nums.get(0);
        int min = dp[0];
        for(int i = 1; i < len; i ++) {
            int minSum = Integer.MAX_VALUE;
            for(int j = 0; j < i; j ++) {
                minSum = Math.min(minSum, dp[j]);
            }
            dp[i] = minSum > 0 ? nums.get(i) : minSum + nums.get(i);
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    public static void main(String[] args) {
    	MinSubArray test = new MinSubArray();
    	int[] nums = {-5,10,-4};
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	for(int e: nums) {
    		list.add(e);
    	}
    	int result = test.minSubArray(list);
    	System.out.println(result);
    }
}