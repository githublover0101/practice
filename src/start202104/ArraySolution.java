package start202104;

public class ArraySolution {

	/**
	 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
	 * https://leetcode.com/problems/kth-largest-element-in-an-array
	 * 
	 * 215. 数组中的第K个最大元素
	 *
	 * 解题思路：快速排序思路
	 * 时间复杂度：O(n)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest(int[] nums, int k) {
		if(nums.length == 0 || k <= 0 || k > nums.length) return -1;
		return findKth(nums, k, 0, nums.length-1);
    }
	
	private int findKth(int[] nums, int k, int left, int right) {
		// 快速排序思路找到中间分割点
		int index = partition(nums, left, right);
		
		//如果刚好分割点 == k-1，则直接返回
		if(index == k-1) {
			return nums[index];
		} else if(index > k-1) {
			return findKth(nums, k, left, index-1);
		} else {
			return findKth(nums, k, index+1, right);
		}
	}
	
	private int partition(int[] nums, int left, int right) {
		int i = left-1;
		int key = nums[right];
		for(int j = left; j < right; j++) {
			if(nums[j] > key) {
				i++;
				swap(nums, i, j);
			}
		}
		swap(nums, i+1, right);
		return i+1;
	}
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	
	/**
	 * https://leetcode.com/problems/merge-sorted-array/
	 * 
	 * 88. 合并两个有序数组
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(m == 0 && n == 0) return;
        
        int k = m+n-1;
        int i = m-1;
        int j = n-1;
        while(i >= 0 && j >= 0) {
        	if(nums1[i] > nums2[j]) {
        		nums1[k--] = nums1[i--];
        	} else {
        		nums1[k--] = nums2[j--];
        	}
        }
        while(j >= 0) {
        	nums1[k--] = nums2[j--];
        }
    }

	
}
