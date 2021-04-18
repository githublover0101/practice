package abc;

public class MinAbsSliceSum {

	/**
	 * A non-empty zero-indexed array A of N integers is given. 
	 * A pair of integers (P, Q), such that 0 ² P ² Q < N, is called a slice of array A. 
	 * The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q]. 
	 * given a non-empty zero-indexed array A consisting of N integers, returns the absolute sum of min abs slice.
	 */
	// time complexity is O(n^3)
	public int minAbsSliceSum(int[] nums) {
		// to each nums[i], find all the abss
		// find the smallest one
		int minAbs = 0;
		int curAbs = 0;
		for(int i = 0; i < nums.length; i++) {
			for(int j = i; j < nums.length; j++) {
				curAbs = absSum(nums, i, j);
				minAbs = curAbs < minAbs ? curAbs : minAbs;
			}
		}
		return minAbs;
	}
	
	public int absSum(int[] nums, int start, int end) {
		int sum = 0;
		for(int i = start; i <= end; i++) {
			sum += nums[i];
		}
		return Math.abs(sum);
	}
	
	public int minAbsSliceSum2(int[] nums) {
		if(nums.length == 0) return 0;
		
		int[] abs = new int[nums.length];
		abs[0] = Math.abs(nums[0]);
		int minAbs = abs[0];
		for(int i = 1; i < nums.length; i++) {
			int abs1 = Math.abs(abs[i-1] + nums[i]);
			int abs2 = Math.abs(-abs[i-1] + nums[i]);
			abs[i] = Math.min(abs1, abs2);
			minAbs = Math.min(minAbs, abs[i]);
		}
		return minAbs;
	}
	
	public static void main(String[] args) {
		MinAbsSliceSum test = new MinAbsSliceSum();
		int[] nums = {2,-4,6,-3,9};
		int result = test.minAbsSliceSum2(nums);
		System.out.println(result);
	}
	
}
