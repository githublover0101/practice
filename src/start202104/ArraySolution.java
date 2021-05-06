package start202104;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

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

	/**
	 * 寻找两个升序数组的中位数
	 * 
	 * 解法一：先合并数组，再找出中位数
	 * 时间复杂度为O(m+n)，空间复杂度为O(m+n)
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = mergeArrays(nums1, nums2);
        int len = nums.length;
        if(len%2 == 0) {
        	return (nums[len/2-1] + nums[len/2])/2.0;
        } else {
        	return nums[len/2];
        }
    }
	
	private int[] mergeArrays(int[] nums1, int[] nums2) {
		if(nums1.length == 0) return nums2;
		if(nums2.length == 0) return nums1;
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] nums = new int[len1+len2];
		int k = len1+len2-1;
		int i = len1-1;
		int j = len2-1;
		while(i >= 0 && j >= 0) {
			if(nums1[i] > nums2[j]) {
				nums[k--] = nums1[i--]; 
			} else {
				nums[k--] = nums2[j--]; 
			}
		}
		while(i >= 0) {
			nums[k--] = nums1[i--]; 
		}
		while(j >= 0) {
			nums[k--] = nums2[j--]; 
		}
		return nums;
	}
	
	/**
	 * https://leetcode.com/problems/first-missing-positive/
	 * 
	 * 缺失的第一个整数
	 * 使用set/map，遍历数组，存储这些数，且记录数组中的min和max
	 * （1）如果max < 0，数组中全是负数，则返回 1
	 * （2）遍历[1, max]，如果i不存在set，则返回 i
	 * （3）否则返回 max+1
	 * 
	 * 时间复杂度为O(n)，空间复杂度为O(n)
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive(int[] nums) {
		if(nums.length == 0) return 1;
		HashSet<Integer> set = new HashSet<Integer>();
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if(max < 0) return 1;
		for(int i = 1; i <= max; i++) {
			if(!set.contains(i)) {
				return i;
			}
		}
		return max+1;
    }
	
	//结果只能存在三种：1（全是负数的情况）、N+1（全是整数）、[1..N]之间的数
	//1.将数组中的负数全部变成N+1
	//2.遍历数组，将[1...N]的元素对应的下标位置的数变成负数（打标记，说明下标对应的i值存在）
	//3.遍历数组，找到第一个正数的下标+1
	//时间复杂度为O(n)，空间复杂度为O(1)
	public int firstMissingPositiveII(int[] nums) {
		if(nums.length == 0) return 1;
		int len = nums.length;
		for(int i = 0; i < len; i++) {
			if(nums[i] <= 0) {
				nums[i] = len+1;
			}
		}
        for(int i = 0; i < len; i++) {
        	//需要使用绝对值，因为赋值为负数的原因，前面的i可能给后面的index赋值为负数，为防止遍历过程中，数组越界，所以需要用绝对值
            int num = Math.abs(nums[i]);
            if(num <= len) {
				nums[num-1] = -Math.abs(nums[num-1]);
			}
        }
		for(int i = 0; i < len; i++) {
			if(nums[i] > 0) {
				return i+1;
			}
		}
		return len+1;
	}
	
	//交换，时间复杂度为O(n)，空间复杂度为O(1)
	public int firstMissingPositiveIII(int[] nums) {
		int len = nums.length;
		for(int i = 0; i < len; i++) {
			//通过交换达到nums[x-1] = x，当x属于[1...len]之间
			while(nums[i] >=1 && nums[i] <=len && nums[nums[i] - 1] != nums[i]) {
				int temp = nums[nums[i]-1];
				nums[nums[i]-1] = nums[i];
				nums[i] = temp;
			}
		}
		//nums[i] = i+1，如果不满足，则i+1即是第一个缺失的正数
		for(int i = 0; i < len; i++) {
			if(nums[i] != i+1) {
				return i+1;
			}
		}
		return len+1;
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
	 * 
	 * 字典序的第K小数字
	 * 
	 * 解法一：暴力解法
	 * （1）根据n生成数组，且转成字符串
	 * （2）对字符串数组进行排序，O(nlgn)
	 * （3）返回nums[k-1]
	 * 
	 * 时间复杂度为O(nlgn)，空间复杂度为O(n)
	 * @param n
	 * @param k
	 * @return
	 */
	public int findKthNumber(int n, int k) {
		String[] nums = new String[n];
		int j = 0;
		for(int i = 1; i <= n; i++) {
			nums[j++] = String.valueOf(i);
		}
		Arrays.sort(nums); //O(nlogn)
		return Integer.parseInt(nums[k-1]);
    }
	
	/**
	 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
	 * 
	 * 字典序的第K小数字
	 * 
	 * 字典树：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/yi-tu-sheng-qian-yan-by-pianpianboy/
	 * 
	 * 构造一颗十叉树
	 * 
	 * 时间复杂度：O(logk * log n)
	 * 空间复杂度：O(1)
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public int findKthNumberII(int n, int k) {
		int cur = 1;
		k = k-1;
		while(k > 0) {
			int num = getNodesCount(n, cur, cur+1);
			if(num <= k) { //从左往右找，不在当前分支上
				cur = cur+1; //往右找，+1
				k = k-num;
			} else { //从上往下找，在当前分支上
				k = k-1; //减掉当前根节点
				cur = cur*10; //往下找，*10
			}
		}
		return cur;
    }
	
	//获取[first, next)之间的节点数
	private int getNodesCount(int n, long first, long next) {
		int num = 0;
		while(first <= n) {
			num += Math.min(n+1, next) - first; //[first,next) 或者 [first,n+1)之间的节点数
			first *= 10;
			next *= 10;
		}
		return num;
	}
	
	
	/**
	 * 下一个更大元素
	 * 时间复杂度为O(n)，空间复杂度为O(n)
	 * 
	 * 建立「单调递减栈」，并对原数组遍历一次：
	 * （1）如果栈为空，则把当前元素放入栈内；
	 * （2）如果栈不为空，则需要判断当前元素和栈顶元素的大小：
	 * - 2.1）如果当前元素比栈顶元素大：说明当前元素是前面一些元素的「下一个更大元素」，则逐个弹出栈顶元素，直到当前元素比栈顶元素小为止。
	 * - 2.2）如果当前元素比栈顶元素小：说明当前元素的「下一个更大元素」与栈顶元素相同，则把当前元素入栈。
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements(int[] nums) {
		if(nums.length == 0) return nums;
		Stack<Integer> stack = new Stack<Integer>();
		int len = nums.length;
		int[] res = new int[len];
        //如果对应的元素不存在右边更大元素，则设置成-1，所以需要将结果数组初始化成-1
		Arrays.fill(res, -1);
		
		//循环数组，所以遍历两遍，2*len
		for(int i = 0; i < 2*len; i++) {
			//如果当前栈不为空，且当前元素 > 栈顶元素，则表示当前元素，是栈顶元素（对应下标）的下一个更大元素
			while(!stack.isEmpty() && nums[i%len] > nums[stack.peek()]) {
				//栈中存储的是index
				res[stack.pop()] = nums[i%len];
			}
			stack.push(i%len);
		}
		return res;
    }	
	
	public static void main(String[] args) {
		ArraySolution test = new ArraySolution();
		System.out.print(test.findKthNumber(13, 2));
	}
	
}
