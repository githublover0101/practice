package start202104;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TwoSum {

	
	//题目一：数组是无序的，两个数之和等于target，每个数只能用一次
	//方法：hashmap存储数字和对应的index，比较两个的index，i < map.get(target - nums[i])
	public int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		if(nums.length == 0) return res;
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		
		//需要考虑目标index在当前index之后
		for(int i = 0 ; i < nums.length; i++) {
			if(map.containsKey(target - nums[i]) && i < map.get(target - nums[i])) {
				res[0] = i;
				res[1] = map.get(target - nums[i]);
				return res;
			}
		}
		return res;
    }
	
	//数组已排好序，找出两个数字等于target
	//双指针法，一个指针从左至右，一个指针从右至左	
	public int[] twoSumWithSortedNumbers(int[] numbers, int target) {
        int[] res = new int[2];
        if(numbers.length == 0) return res;
        int i = 0; 
        int j = numbers.length - 1;
        
        while(i < j) {
        	if(numbers[i] + numbers[j] == target) {
        		res[0] = i+1;
        		res[1] = j+1;
        		return res;
        	} else if(numbers[i] + numbers[j] < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
        return res;
    }
	
	//输入是一个二叉查找树	
	//方法：递归方式，用一个map或者set存储访问过的值
	public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        HashSet<Integer> set = new HashSet<Integer>();
        return helper(root, k, set);
    }
	
	private boolean helper(TreeNode root, int k, HashSet<Integer> set) {
		if(root == null) return false;
		if(set.contains(k - root.val)) return true;
		//说明没找到，先加入到set中		
		set.add(root.val);
		//继续在左子树和右子树查找
		return helper(root.left, k, set) || helper(root.right, k, set);
	}
}	

