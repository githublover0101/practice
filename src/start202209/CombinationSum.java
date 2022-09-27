package start202209;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	
	/**
	 * https://leetcode.cn/problems/combination-sum
	 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
	 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
	 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
	 * 示例 1：
	 * 输入：candidates = [2,3,6,7], target = 7
	 * 输出：[[2,2,3],[7]]
	 * 解释：
	 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
	 * 7 也是一个候选， 7 = 7 。
	 * 仅有这两种组合。
	 * 
	 * 重点：每个元素可以重复使用，[2,2,3]和[2,3,2]不能共存，所以结果具有一定的顺序性，需要用start index
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(candidates.length == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        dfs(candidates, target, allList, subList, 0);
        return allList;
    }

    private void dfs(int[] nums, int target, List<List<Integer>> allList, List<Integer> subList, int start) {
        if(target == 0) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(nums[i] <= target) {
                subList.add(nums[i]);
                dfs(nums, target - nums[i], allList, subList, i);
                subList.remove(subList.size()-1);
            }
        }
    }
    
    //在1的基础上，每个元素只能用一次时，需要：
    //1）排序
    //2）去重判断，nums[i] == nums[i-1]
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(candidates.length == 0) return allList;
        Arrays.sort(candidates);
        List<Integer> subList = new ArrayList<Integer>();
        combinationSum2(candidates, target, allList, subList, 0);
        return allList;
    }

    private void combinationSum2(int[] nums, int target, List<List<Integer>> allList, List<Integer> subList, int start) {
        if(target == 0) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) //排序且去重判断
                continue;

            if(nums[i] <= target) {
                subList.add(nums[i]);
                combinationSum2(nums, target - nums[i], allList, subList, i+1);
                subList.remove(subList.size()-1);
            }
        }
    }
    
    //结果集具有顺序性，需要有index 每个元素最多只能用一次，需要有个开始index
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(k == 0 || n == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        combinationSum3(allList, subList, k, n, 1); //结果集具有顺序性，需要有index
        return allList;
    }

    private void combinationSum3(List<List<Integer>> allList, List<Integer> subList, int k, int n, int start) {
        if(subList.size() == k && n == 0) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        for(int i = start; i <= 9; i++) {
            if(i <= n) {
                subList.add(i);
                combinationSum3(allList, subList, k, n-i, i+1); //每个元素最多只能用一次，需要index+1
                subList.remove(subList.size()-1);
            }
        }
    }
	
}
