package abc;

public class MoveK {

	public void moveK2(int[] nums, int k) {
		print(nums);
		int len = nums.length;
		int tmp = 0;
		int count = 0;
		while(count < k) {
			tmp = nums[len-1];
			for(int i = len-1; i > 0; i --) {
				nums[i] = nums[i-1];
			}
			nums[0] = tmp;
			count ++;
		}
		print(nums);
	}

	public void print(int[] nums) {
		for(int e: nums) {
			System.out.print(e + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		MoveK test = new MoveK();
		int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12};
		int k = 4;
		test.moveK(nums, k);
	}

	// time complexity is O(n), and space complexity is O(1)
	public void reverse(int[] nums, int start, int end) {
		int tmp = 0;
		while(start < end) {
			tmp = nums[start];
			nums[start] = nums[end];
			nums[end] = tmp;
			start++;
			end--;
		}
	}

	public void moveK(int[] nums, int k) {
		print(nums);
		int n = nums.length;
		reverse(nums, 0, n-k-1);
		reverse(nums, n-k, n-1);
		reverse(nums, 0, n-1);
		print(nums);
	}
}