package abc;

public class HuluInterview {

	public int getMax(int[] nums) {
		int len = nums.length;
		int[] b = new int[len];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < len; i++) {
			if(i > 1) {
				int k = i-2;
				int curMax = b[k];
				while(k >= 0) {
					curMax = Math.max(curMax, b[k]);
					k--;
				}
				b[i] = curMax;
				if(nums[i] > 0) {
					b[i] += nums[i];
				}
			} else {
				b[i] = nums[i];
			}
			System.out.print(b[i] + ",");
			max = Math.max(max, b[i]);
		}
		System.out.println();
		return max;
	}
	
	public static void main(String[] args) {
		HuluInterview test = new HuluInterview();
		int[] nums = {1,3,-4,2,7,8,2,-9,12,-10};
		int result = test.getMax(nums);
		System.out.println(result);
	}
	
}
