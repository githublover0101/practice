public class LargestKth {

	public int findLargestKth(int[] nums, int k) {
		return findK(nums, 0, nums.length-1, k);
	}

	public int findK(int[] nums, int left, int right, int k) {
		int pvoit = partition(nums, left, right);
		if(pvoit == k-1) {
			return nums[k-1];
		} else if(pvoit > k-1) {
			return findK(nums, left, pvoit-1, k);
		} else {
			return findK(nums, pvoit+1, right, k);
		}
	}


	public void quicksort(int[] nums) {
		quicksort(nums, 0, nums.length - 1);
	}

	public void quicksort(int[] nums, int left, int right) {
		if(left < right) {
			int pvoit = partition(nums, left, right);
			quicksort(nums, left, pvoit-1);
			quicksort(nums, pvoit+1, right);
		}
	}

	public int partition(int[] nums, int left, int right) {
		int i = left - 1;
		int j = left;
		int key = nums[right];
		for(; j < right; j ++) {
			if(nums[j] < key) {
				i++;
				exchange(nums, i, j);
			}
		}
		exchange(nums, i+1, right);
		return i+1;
	}

	public void exchange(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}