package start202104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationSolution {

	/**
	 * https://leetcode.com/problems/permutations/
	 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
	 * 
	 * 输入: [1,2,3]
		输出:
		[
		  [1,2,3],
		  [1,3,2],
		  [2,1,3],
		  [2,3,1],
		  [3,1,2],
		  [3,2,1]
		]
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(nums.length == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        //设置一个是否访问过的数组
        boolean[] visited = new boolean[nums.length];
        permuteHelper(nums, visited, subList, allList);
        return allList;
    }
	
	private void permuteHelper(int[] nums, boolean[] visited, List<Integer> subList, List<List<Integer>> allList) {
		if(subList.size() == nums.length) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
		//因为排列结果里面需要所有的值，所以从[0, len-1]的数组所有的值需要遍历
		for(int i = 0; i < nums.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				subList.add(nums[i]);
				permuteHelper(nums, visited, subList, allList);
				subList.remove(subList.size()-1);
				visited[i] = false;
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/permutations-ii/
	 * 
	 * 如果数组包含重复元素，全排列，且结果集中不包含重复的排列结果
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        List<Integer> subList = new ArrayList<Integer>();
        //设置访问数组，标识数组元素是否访问过
        boolean[] visited = new boolean[nums.length];
        
        //需要先对原数组进行升序排序
        Arrays.sort(nums);
        permuteUnique(nums, visited, subList, allList);
        
        return allList;
    }
	
	private void permuteUnique(int[] nums, boolean[] visited, List<Integer> subList, List<List<Integer>> allList) {
		if(subList.size() == nums.length) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
        
		for(int i = 0; i < nums.length; i++) {
			//如果遇到 i-1 已访问过，且 nums[i] == nums[i-1]时，则跳过
            if(i > 0 && nums[i] == nums[i-1] && visited[i-1]) continue; 
            
			if(!visited[i]) {
				visited[i] = true;
				subList.add(nums[i]);
				
                permuteUnique(nums, visited, subList, allList);
				visited[i] = false;
				subList.remove(subList.size()-1);
			}
		}
	}
	
	/**
	 * 组合总和
	 * https://leetcode.com/problems/combination-sum/
	 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
	 * candidates 中的数字可以无限制重复被选取。
	 * 
	 * 输入：candidates = [2,3,6,7], target = 7,
		所求解集为：
		[
		  [7],
		  [2,2,3]
		]
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		combinationSum(candidates, target, subList, allList, 0);
		return allList;
    }
	
	private void combinationSum(int[] candidates, int target, List<Integer> subList, List<List<Integer>> allList, int start) {
		if(target == 0) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
		//需要设置开始位置，如果不设置开始位置，每次从0开始，会出现重复结果，如[2,2,3], [3,2,2]
		for(int i = start; i < candidates.length; i ++) {
			if(candidates[i] <= target) {
				subList.add(candidates[i]);
				combinationSum(candidates, target - candidates[i], subList, allList, i); //每个元素可以重复使用
				subList.remove(subList.size()-1);
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/combination-sum-ii/
	 * 
	 * 组合总和
	 * candidates 中的每个数字在每个组合中只能使用一次。
	 * 
	 * 排列问题，讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为不同列表时），需要记录哪些数字已经使用过，此时用 used 数组；
		组合问题，不讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为相同列表时），需要按照某种顺序搜索，此时使用 begin 变量。
	 * 
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		
		//需要先排序
		Arrays.sort(candidates);
		combinationSum2(candidates, target, subList, allList, 0);
		return allList;
    }
	
	private void combinationSum2(int[] candidates, int target, List<Integer> subList, List<List<Integer>> allList, int start) {
		if(target == 0) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
		//需要设置开始位置，如果不设置开始位置，每次从0开始，会出现重复结果，如[2,2,3], [3,2,2]
		for(int i = start; i < candidates.length; i ++) {
            
            //i 大于开始标记，且出现重复元素
			if(i > start && candidates[i] == candidates[i-1]) {
				continue;
			}
			if(candidates[i] <= target) {
				subList.add(candidates[i]);
				combinationSum2(candidates, target - candidates[i], subList, allList, i+1);
				subList.remove(subList.size()-1);
			}
		}
	}
	
	/**
	 * https://leetcode.com/problems/combination-sum-iii/
	 * 组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
	 * 找出所有相加之和为 n 的 k 个数的组合
	 * @param k
	 * @param n
	 * @return
	 */
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		combinationSum3(k, n, subList, allList, 1);
		return allList;
	}
	
	private void combinationSum3(int k, int n, List<Integer> subList, List<List<Integer>> allList, int start) {
		if(n == 0 && subList.size() == k) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
		// 1-9之间，由于元素不可重复使用，需要使用start下标
		for(int i = start; i <= 9; i++) {
			subList.add(i);
			combinationSum3(k, n-i, subList, allList, i+1);
			subList.remove(subList.size()-1);
		}
	}
	
	public int combinationSum4(int[] nums, int target) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		List<Integer> subList = new ArrayList<Integer>();
		combinationSums4(nums, target, subList, allList);
		return allList.size();
    }
	
	private void combinationSums4(int[] nums, int target, List<Integer> subList, List<List<Integer>> allList) {
		if(target == 0) {
			allList.add(new ArrayList<Integer>(subList));
			return;
		}
		//元素可以重复出现，不需要visited数组，且index从0开始
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] <= target) {
				subList.add(nums[i]);
				combinationSums4(nums, target - nums[i], subList, allList);
				subList.remove(subList.size()-1);
			}
		}
	}
	
}
