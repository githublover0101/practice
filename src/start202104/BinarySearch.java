package start202104;

public class BinarySearch {

	
	/**
	 * 二分查找 - 递归实现
	 * 时间复杂度为O(lgn)
	 * @param nums
	 * @param target
	 * @return
	 */
	public int binarySearch(int[] nums, int target) {
		if(nums.length == 0) return -1;
		return binarySearch(nums, target, 0, nums.length-1);
	}
	
	
	private int binarySearch(int[] nums, int target, int left, int right) {
		if(left <= right) {
			int mid = (left + right)/2;
			if(nums[mid] == target) {
				return mid;
			} else if(nums[mid] > target) {
				return binarySearch(nums, target, left, mid-1);
			} else {
				return binarySearch(nums, target, mid+1, right);
			}
		}
		return -1;
	}
	
	/**
	 * 二分查找 - 非递归实现方式
	 * @param nums
	 * @param target
	 * @return
	 */
	public int binarySearchII(int[] nums, int target) {
		if(nums.length == 0) return -1;
		int left = 0;
		int right = nums.length-1;
		int mid = 0;
		while(left <= right) {
			mid = (left+right)/2;
			if(nums[mid] == target) {
				return mid;
			} else if(nums[mid] > target) {
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		return -1;
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/search-a-2d-matrix/
	 * 搜索二维矩阵
	 * 
	 * 从左下角或者右上角开始，如果当前元素cur > target，则往上移动，否则往右移动
	 * 时间复杂度为O(m+n)
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		if(m == 0) return false;
		int n = matrix[0].length;
		if(n == 0) return false;
		
		int i = m-1;
		int j = 0;
		while(i >= 0 && j < n) {
			if(matrix[i][j] == target) {
				return true;
			} else if(matrix[i][j] > target) {
				i--;
			} else {
				j++;
			}
		}
		return false;
    }
	
	/**
	 * https://leetcode-cn.com/problems/search-a-2d-matrix/
	 * 搜索二维矩阵
	 * 
	 * 一次二分查找，将整个矩阵变成一个一维有序数组，长度为m*n
	 * 
	 * 时间复杂度为O(lgm*n)
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrixII(int[][] matrix, int target) {
		int m = matrix.length;
		if(m == 0) return false;
		int n = matrix[0].length;
		if(n == 0) return false;
		
		int left = 0;
		int right = m*n-1; //注意right值
		int mid = 0;
		while(left <= right) {
			mid = (right - left)/2 + left; //防止越界，大减去小除以2，再加上小
			
			//根据mid获取二维数组中的值，row = mid/n, column = mid%n
			int x = matrix[mid/n][mid%n];
			if(x == target) {
				return true;
			} else if(x > target) {
				right = mid-1;
			} else {
				left = mid+1;
			}
		}
		return false;
    }
	
	/**
	 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
	 * 搜索旋转排序数组
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		if(nums.length == 0) return -1;
		int left = 0;
		int right = nums.length-1;
		int mid = 0;
		while(left <= right) {
			mid = (right - left)/2 + left;
			if(nums[mid] == target) {
				return mid;
			}
			// 找到绝对递增的一半
			if(nums[mid] < nums[right]) { //右半边为递增数组
				//target处于[mid, right]中间，且[mid, target]是绝对递增数组
				if(nums[mid] < target && target <= nums[right]) {
					left = mid+1;
				} else {
					right = mid-1;
				}
			} else { //左半边为递增数组
				
				//target处于[left, mid]之间，且[left, mid]为绝对递增数组
				if(nums[left] <= target && target < nums[mid]) {
					right = mid-1;
				} else {
					left = mid+1;
				}
			}
		}
		return -1;
    }
	
	
	/**
	 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
	 * 搜索旋转排序数组II
	 * 
	 * 数组中的元素可能相同，非严格递增数组
	 */
	public boolean searchII(int[] nums, int target) {
		if(nums.length == 0) return false;
		int left = 0;
		int right = nums.length-1;
		int mid = 0;
		while(left <= right) {
			mid = (right - left)/2 + left;
			if(nums[mid] == target) {
				return true;
			}
			// 找到绝对递增的一半
			if(nums[mid] < nums[right]) { //右半边为递增数组
				//target处于[mid, right]中间，且[mid, target]是绝对递增数组
				if(nums[mid] < target && target <= nums[right]) {
					left = mid+1;
				} else {
					right = mid-1;
				}
			} else if(nums[mid] > nums[right]){ //左半边为递增数组
				
				//target处于[left, mid]之间，且[left, mid]为绝对递增数组
				if(nums[left] <= target && target < nums[mid]) {
					right = mid-1;
				} else {
					left = mid+1;
				}
			} else { // 当nums[mid]==nums[right]时，可能是这种情况 [2,3,1,1,1,1,1]，需要将right指针往前移，找到非1的地方
                right--;
            }
		}
		return false;
    }
	
	
	/**
	 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
	 * 寻找旋转数组中的最小值
	 * @param nums
	 * @return
	 */
	public int findMin(int[] nums) {
		if(nums.length == 0) return -1;
		int left = 0;
		int right = nums.length-1;
		int mid = 0;
		while(left < right) {
			mid = (right-left)/2+left;
			if(nums[right] < nums[mid]) { //中值 > 右值，说明是旋转那一半，最小值在右边，left=mid+1
				left = mid+1;
			} else {
				right = mid; //中值 <= 右值，中值可能也是最小值，所以right=mid
			}
		}
		return nums[right]; //循环结束left==right，输出left或者right都可以
    }
	
	
	/**
	 * https://leetcode-cn.com/problems/find-peak-element/
	 * 寻找峰值
	 * 时间复杂度为O(n)
	 * @param nums
	 * @return
	 */
	public int findPeakElement(int[] nums) {
		if(nums.length <= 1) return 0;
        for(int i = 0; i < nums.length-1; i++) {
        	
        	//如果当前元素 > 后一个元素，则当前元素是峰值
        	if(nums[i] > nums[i+1]) {
        		return i;
        	}
		}
        //否则返回最后一个元素
		return nums.length-1;
    }
	
	/**
	 * https://leetcode-cn.com/problems/find-peak-element/
	 * 寻找峰值
	 * 时间复杂度为O(lgn)
	 * @param nums
	 * @return
	 */
	public int findPeakElementII(int[] nums) {
		int left = 0;
		int right = nums.length-1;
		while(left < right) {
			int mid = (right-left)/2+left;
			if(nums[mid] > nums[mid+1]) { //如果mid > mid+1，说明mid很可能是峰值，则right往mid移动
				right = mid;
			} else {
				left = mid+1;
			}
		}
		return left; //跳出循环是left=right，则返回其中任意一个都可以
    }
	
}
