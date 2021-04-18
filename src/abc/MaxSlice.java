package abc;

public class MaxSlice {

//	time complexity is O(n)
//	space complexity is O(n)
	public int maxSlice(int[] nums) {
		int[] sums = new int[nums.length];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			if(i == 0) {
				sums[i] = nums[i];
			} else {
				sums[i] = sums[i-1] < 0 ? nums[i] : nums[i] + sums[i];
			}
			max = Math.max(sums[i], max);
		}
		return max;
	}
	
//	space complexity is O(1)
	public int maxSlice2(int[] nums) {
		if(nums.length == 0) return 0;
		int last = nums[0];
		int max = last;
		for(int i = 1; i < nums.length; i++) {
			last = last < 0 ? nums[i] : nums[i]+last;
			max = Math.max(last, max);
		}
		return max;
	}
	
	public int maxProfit(int[] nums) {
		int min = nums[0];
		int maxProfit = Integer.MIN_VALUE;
		for(int i = 1; i < nums.length; i++) {
			maxProfit = Math.max(maxProfit, nums[i]-min);
			min = Math.min(min, nums[i]);
		}
		return maxProfit;
	}
	
	public int maxDoubleSliceSum(int[] nums) {
		int len = nums.length;
		int[] K1 = new int[len];
		int[] K2 = new int[len];
		
//		K1[i] = K[i-1] + nums[i];
		for(int i = 0; i < len; i++) {
			if(i == 0) {
				K1[i] = Math.max(nums[i], 0);
			} else {
				K1[i] = Math.max(K1[i-1]+nums[i], 0);
			}
		}
		
//		K2[i] = K2[i+1] + nums[i];
		for(int i = len-1; i >= 0; i--) {
			if(i == len-1) {
				K2[i] = Math.max(nums[i], 0);
			} else {
				K2[i] = Math.max(K2[i+1]+nums[i], 0);
			}
		}
		
		int max = 0;
		for(int i = 1; i < len-1; i++) {
			max = Math.max(K1[i-1] + K2[i+1], max);
		}
		return max;
	}
	
}
