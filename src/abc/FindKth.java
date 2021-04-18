public class FindKth {

	public int findKth(int[] nums, int k) {
		return findKth(nums, 0, nums.length-1, k);
	}

	public int findKth(int[] nums, int left, int right, int k) {
		int i = left-1;
		int key = nums[right];
		for(int j = left; j < right; j ++) {
			if(nums[j] > key) {
				i++;
				exchange(nums, i, j);
			}
		}
		exchange(nums, i+1, right);
		if(i+1 == k-1) {
			return nums[k-1];
		} else if(i+1 > k-1) {
			return findKth(nums, left, i, k);
		} else {
			return findKth(nums, i+2, right, k);
		}
	}

	public void exchange(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	// nums1 and nums2 are sorted lists, and need to find the kth num in nums1 and nums2
	// space complexity is O(len1+len2), and time complexity is O(n)
	public int findKth(int[] nums1, int[] nums2, int n) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] nums = new int[len1+len2];
		int k = 0;
		int i = 0;
		int j = 0;
		while(i < len1 && j < len2) {
			if(nums1[i] < nums2[j]) {
				nums[k++] = nums1[i];
				i++;
			} else {
				nums[k++] = nums2[j];
				j++;
			}
		}
		while(i < len1) {
			nums[k++] = nums1[i++];
		}
		while(j < len2) {
			nums[k++] = nums2[j++];
		}
		return nums[len1+len2-n];
	}
}