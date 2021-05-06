package start202104;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 下一个更大元素系列
 */
public class NextGreater {
	
	/**
	 * https://leetcode-cn.com/problems/next-greater-element-i/
	 * 
	 * 单调栈：
	 * （1）如果栈顶元素 < 当前元素，则当前元素为栈顶元素的下一个更大元素；pop栈顶元素，放入map中保存
	 * 
	 * 时间复杂度为O(m+n)，空间复杂度为O(n)
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] res = new int[len1];
		
		//map用来保存元素与其下一个更大元素之间的对应关系
		Map<Integer, Integer> nextMap = new HashMap<Integer, Integer>();
		
		//需要额外栈空间
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < len2; i++) {
			while(!stack.isEmpty() && stack.peek() < nums2[i]) {
				
				//将栈顶元素pop，且栈顶元素的下一个更大元素为nums2[i]
				nextMap.put(stack.pop(), nums2[i]);
			}
			
			//将当前元素放入栈顶
			stack.push(nums2[i]);
		}
		
		for(int i = 0; i < len1; i++) {
			res[i] = nextMap.getOrDefault(nums1[i], -1);
		}
		return res;
    }
	
	
	/**
	 * https://leetcode-cn.com/problems/next-greater-element-ii/
	 * 下一个更大元素II
	 * 跟I的区别在于，循环数组
	 * 循环数组（最后一个元素的下一个元素是数组的第一个元素）
	 * 
	 * （1）循环数组，遍历范围为 2*len，下标需要做hash处理：i%len
	 * （2）stack栈中存储的是下标，而不是元素本身
	 * @param nums
	 * @return
	 */
    public int[] nextGreaterElements(int[] nums) {
    	int len = nums.length;
    	int[] res = new int[len];
    	//初始化res元素为-1
    	Arrays.fill(res, -1);
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	for(int i = 0; i < 2*len; i++) {
    		//如果遇到栈顶元素 < 当前元素
    		while(!stack.isEmpty() && nums[stack.peek()] < nums[i%len]) {
    			
    			//栈顶index所对应的元素的下一个更大元素 为当前元素
    			res[stack.pop()] = nums[i%len];
    		}
    		//栈中放的是元素index
    		stack.push(i%len);
    	}
    	return res;
    }
    	
    
    /**
     * https://leetcode-cn.com/problems/next-greater-element-iii/
     * 下一个更大元素III
     * 
     * 存在两种结果：
     * （1）整个数组是降序数组，则不存在比它大的元素，返回-1
     * （2）从后往前找到第一个升序元素，即nums[i] < nums[i+1]
     * 
     * 解法：
     * （1）从后往前遍历，找到第一个满足 nums[i] < nums[i+1] 的下标 i
     * （2）在后半段数组 [i+1, len-1] 中，从后往前遍历，找到第一个元素满足：nums[i] < nums[j] 的下标j，并且交换 i 和 j 元素
     * （3）对后半段数组 [i+1, len-1] 进行升序排序
     * （4）返回整体nums数组对应的数字（需要考虑越界）
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
    	if(n <= 0) return -1; 
    	String value = String.valueOf(n);
    	char[] nums = value.toCharArray();
    	int len = nums.length;
    	int i = len-2;
    	for(; i>= 0; i--) {
    		if(nums[i] < nums[i+1]) {
    			break;
    		}
    	}
    	//说明没找到符合 nums[i] < nums[i+1]的元素，整个数组是降序排序的
    	if(i < 0) {
    		return -1;
    	}
    	
    	//[i+1, len-1]中从后往前找，找到第一个 j 满足：nums[i] < nums[j]
    	findFirstGreater(nums, i+1, len-1);
    	
    	//对[i+1, len-1]进行排序
    	Arrays.sort(nums, i+1, len);
    	
    	return convertToInt(nums);
    }
    
    private int convertToInt(char[] nums) {
    	//需要考虑越界
    	long res = 0;
    	for(int k = 0; k < nums.length; k++) {
    		res = res*10 + (int)(nums[k] - '0');
    	}
    	//如果大于int最大值，则返回-1
    	return res > Integer.MAX_VALUE ? -1 : (int)res;
    }
    
    
  //在[left, right]之间，从后往前遍历，找到第一个j满足：nums[i] < nums[j]，其中i=left-1
    private void findFirstGreater(char[] nums, int left, int right) {
    	int i = left-1;
    	for(int j = right; j >= left; j--) {
    		if(nums[i] < nums[j]) {
    			//swap i, j
    			char temp = nums[i];
    			nums[i] = nums[j];
    			nums[j] = temp;
    			break;
    		}
    	}
    }
    
    
    /**
     * https://leetcode-cn.com/problems/next-permutation/submissions/
     * 下一个排序
     * 
     * 时间复杂度为O(n)，空间复杂度为O(1)
     * @param nums
     */
    public void nextPermutation(int[] nums) {
    	int len = nums.length;
    	int i = len-2;
    	for(; i>= 0; i--) {
    		if(nums[i] < nums[i+1]) {
    			break;
    		}
    	}
    	//说明没找到符合 nums[i] < nums[i+1]的元素，整个数组是降序排序的
    	if(i < 0) {
    		//将数组进行升序排序，结果为升序排序数组
    		Arrays.sort(nums);
    	} else {
    		//[i+1, len-1]中从后往前找，找到第一个 j 满足：nums[i] < nums[j]，并交换 i 和 j
    		for(int j = len-1; j > i; j--) {
    			if(nums[i] < nums[j]) {
    				int temp = nums[i];
    				nums[i] = nums[j];
    				nums[j] = temp;
    				break;
    			}
    		}
    		
    		//对[i+1, len-1]进行排序
        	Arrays.sort(nums, i+1, len);
    	}
    }
    
    public static void main(String[] args) {
    	NextGreater test = new NextGreater();
    	System.out.println(test.nextGreaterElement(12));
    }
    
}
