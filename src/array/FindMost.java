package array;

public class FindMost {

	// there is a num occurs most, more than half of the length, find it
	// 
	public int findMostNum(int[] nums) {
		int current = -1;
		int count = 0;
		for(int i = 0; i < nums.length; i ++) {
			if(count == 0) {
				current = nums[i];
				count = 1;
			} else {
				if(nums[i] == current) {
					count ++;
				} else {
					count --;
				}
			}
		}
		return current;
	}

	public static void main(String[] args) {
		FindMost test = new FindMost();
		int[] nums = {0,1,1,1,8,8,8,8,8,8,8,1,18,5,1,5,8,1};
		int num = test.findMostNum(nums);
		System.out.println(num);
	}

}