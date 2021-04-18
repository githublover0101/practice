package abc;

import java.util.ArrayList;

public class ContinuousSubarraySum {

	public ArrayList<Integer> continuousSubarraySum(int[] A) {
        int startIndex = 0;
        int endIndex = 0;
        int max = Integer.MIN_VALUE;
        int[] dp = new int[A.length];
        for(int i = 0; i < A.length; i ++) {
            dp[i] = A[i];
            if(i > 0 && dp[i-1] > 0) {
                dp[i] += dp[i-1];
            }
            if(dp[i] > max) {
                max = dp[i];
                endIndex = i;
            }
        }
        int i = endIndex;
        while(max > 0) {
            max = max - A[i];
            i--;
        }
        startIndex = i+1;
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(startIndex);
        list.add(endIndex);
        return list;
    }

    public static void main(String[] args) {
    	ContinuousSubarraySum test = new ContinuousSubarraySum();
    	int[] nums = {-3, 1, 3, -3, 4};
    	test.continuousSubarraySum(nums);
    }
}