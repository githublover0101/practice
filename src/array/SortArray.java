package array;

public class SortArray {

	public void sort(int[] nums) {
		int tmp = 0;
		for(int i = 0; i < nums.length-1; i += 2) {
			//i & i+1
			if(nums[i+1] < nums[i]) {
				tmp = nums[i];
				nums[i] = nums[i+1];
				nums[i+1] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		SortArray test = new SortArray();
		int[] nums = {6,1,7,9,12,8,12,1};
		test.sort(nums);
		for(int e: nums) {
			System.out.print(e + ",");
		}
		System.out.println();
	}
}