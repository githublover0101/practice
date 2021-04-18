import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;


public class DFS {

//	leetcode-- permutation
//	Given a collection of numbers, return all possible permutations.
//	For example,
//	[1,2,3] have the following permutations:
//	[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(nums.length == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        dfs(nums, allList, subList, visited);
        return allList;
    }
    
    public void dfs(int[] nums, List<List<Integer>> allList, List<Integer> subList, boolean[] visited) {
        if(subList.size() == nums.length) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) { //for loop is in the dfs
            if(!visited[i]) { //visited or not
                subList.add(nums[i]);
                visited[i] = true;
                dfs(nums, allList, subList, visited);
                subList.remove(subList.size()-1); //rollback
                visited[i] = false;
            }
        }
    }
	
    
//  LeetCode -- Permutations II: 
//  Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//  For example,
//  [1,1,2] have the following unique permutations:
//  [1,1,2], [1,2,1], and [2,1,1].
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(nums.length == 0) return allList;
        
        Arrays.sort(nums); //remember to sort the array first
        List<Integer> subList = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        permuteUniqueDfs(nums, allList, subList, visited);
        return allList;
    }
    
    public void permuteUniqueDfs(int[] nums, List<List<Integer>> allList, List<Integer> subList, boolean[] visited) {
        if(subList.size() == nums.length) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            //cur == pre num, and pre num hasn't been visited, so cur one needs to be ignored
            if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            
            if(!visited[i]) {
                visited[i] = true;
                subList.add(nums[i]);
                dfs(nums, allList, subList, visited);
                subList.remove(subList.size()-1);
                visited[i] = false;
            }
        }
    }
    
    
    /**leetcode: combination
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

    		For example,
    		If n = 4 and k = 2, a solution is:

    		[
    		  [2,4],
    		  [3,4],
    		  [2,3],
    		  [1,2],
    		  [1,3],
    		  [1,4],
    		]
    */
    public List<List<Integer>> combine(int n, int k) {
    	List<List<Integer>> allList = new ArrayList<List<Integer>>();
    	if(n == 0 || k == 0) return allList;
    	List<Integer> subList = new ArrayList<Integer>();
    	combineDfs(1, n, k, allList, subList);
    	return allList;
    }
    
    public void combineDfs(int start, int n, int k, List<List<Integer>> allList, List<Integer> subList) {
    	if(subList.size() == k) {
    		allList.add(new ArrayList<Integer>(subList));
    		return;
    	}
    	for(int i = start; i <= n; i++) {
    		subList.add(i);
    		combineDfs(i+1, n, k, allList, subList);
    		subList.remove(subList.size()-1);
    	}
    }
    
    
//    Combination Sum
    /**
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(candidates.length == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        
        Arrays.sort(candidates); //sort the array
        combinationSumDfs(0, candidates, allList, subList, target);
        return allList;
    }
    
    public void combinationSumDfs(int start, int[] candidates, List<List<Integer>> allList, List<Integer> subList, int target) {
    	if(target == 0) {
    		allList.add(new ArrayList<Integer>(subList));
    		return;
    	}
    	
    	//each time for the start index
    	for(int i = start; i < candidates.length; i++) {
    		if(candidates[i] <= target) {
    			subList.add(candidates[i]);
    			combinationSumDfs(i, candidates, allList, subList, target - candidates[i]); //current num or later, in dfs loop
    			subList.remove(subList.size()-1);
    		}
    	}
    }
    
//    leetcode: Combination Sum II
    /**
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(candidates.length == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        
        Arrays.sort(candidates); //sort the array
        
        boolean[] visited = new boolean[candidates.length];
        combinationSum2Dfs(0, candidates, allList, subList, target, visited);
        return allList;
    }
    
    public void combinationSum2Dfs(int start, int[] candidates, List<List<Integer>> allList, List<Integer> subList, int target, boolean[] visited) {
    	if(target == 0) {
    		allList.add(new ArrayList<Integer>(subList));
    		return;
    	}
    	
    	//each time for the start index
    	for(int i = start; i < candidates.length; i++) {
    		if(i > 0 && candidates[i] == candidates[i-1] && !visited[i-1]) 
    			continue; 
    
    		if(candidates[i] <= target) {
    			visited[i] = true;
    			subList.add(candidates[i]);
    			combinationSum2Dfs(i+1, candidates, allList, subList, target - candidates[i], visited); //current num or later, in dfs loop
    			subList.remove(subList.size()-1);
    			visited[i] = false;
    		}
    	}
    }
    
//    leetcode: Combination SumIII
    /**
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
*/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(k == 0 || n == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        combinationSum3Dfs(1, k, n, allList, subList);
        return allList;
    }
    
    public void combinationSum3Dfs(int start, int k, int n, List<List<Integer>> allList, List<Integer> subList) {
        if(subList.size() == k && n == 0) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        for(int i = start; i <= 9; i++) {
            if(i <= n) {
                subList.add(i);
                combinationSum3Dfs(i+1, k, n-i, allList, subList); //no duplicate, start i+1
                subList.remove(subList.size()-1);
            }
        }
    }
    
//    leetcode: Subsets
    /**
     * Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
     */
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> allList = new ArrayList<List<Integer>>();
    	if(nums.length == 0) return allList;
    	List<Integer> subList = new ArrayList<Integer>();
    	Arrays.sort(nums);
    	for(int k = 0; k <= nums.length; k++) { //control the total count of each array
    		subsetsDfs(0, nums, k, allList, subList);
    	}
    	return allList;
    }
    
    public void subsetsDfs(int start, int[] nums, int k, List<List<Integer>> allList, List<Integer> subList) {
    	if(subList.size() == k) {
    		allList.add(new ArrayList<Integer>(subList));
    		return;
    	}
    	for(int i = start; i < nums.length; i++) {
    		subList.add(nums[i]);
    		subsetsDfs(i+1, nums, k, allList, subList);
    		subList.remove(subList.size()-1);
    	}
    }
    
    
//  leetcode: Subsets II  
    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(nums.length == 0) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        Arrays.sort(nums); //sort the array
        boolean[] visited = new boolean[nums.length];
        for(int k = 0; k <= nums.length; k++) {
        	subsetsWithDupDfs(0, nums, k, allList, subList, visited);
        }
        return allList;
    }
    
    public void subsetsWithDupDfs(int start, int[] nums, int k, List<List<Integer>> allList, List<Integer> subList, boolean[] visited) {
    	if(subList.size() == k) {
    		allList.add(new ArrayList<Integer>(subList));
    		return;
    	}
    	for(int i = start; i < nums.length; i++) {
//    		need to check current num equals to pre num or not
    		if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
    		
    		if(!visited[i]) {
    			visited[i] = true;
    			subList.add(nums[i]);
        		subsetsWithDupDfs(i+1, nums, k, allList, subList, visited);
        		subList.remove(subList.size()-1);
        		visited[i] = false;
    		}
    	}
    } 
    
//    leetcode: Word Search
    /**
     * Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false. 
     */
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0) return false;
        if(word == null || "".equals(word)) return true;
    	boolean result = false;
    	boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[0].length; j++) {
        		if(searchWordDfs(board, i, j, word, 0, visited)) {
        			result = true;
        			break;
        		}
        	}
        }
        return result;
    }
    
    public boolean searchWordDfs(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
    	if(index == word.length()) return true;
    	
    	//i&j has been visited, should return
    	if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) return false;
    	
    	if(board[i][j] == word.charAt(index)) {
    	    visited[i][j] = true;
    		boolean result = searchWordDfs(board, i, j+1, word, index+1, visited) ||
    				searchWordDfs(board, i, j-1, word, index+1, visited) ||
    				searchWordDfs(board, i+1, j, word, index+1, visited) ||
    				searchWordDfs(board, i-1, j, word, index+1, visited);
    	    visited[i][j] = false;
    	    return result;
    	}
    	return false;
    }
    
//  找出一个grid中最长的路径，对于grid[i][j]这个点，上下左右只能往小于该点的数走，最后返回最长路径的长度  
    public int longestPathInGrid(int[][] grid) {
    	int n = grid.length;
    	int m = grid[0].length;
    	int[][] paths = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			longestPathInGridDfs(grid, i, j, paths);
    		}
    	}
    	int max = Integer.MIN_VALUE;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			max = Math.max(max, paths[i][j]);
    		}
    	}
    	return max;
    }
    
    public void longestPathInGridDfs(int[][] grid, int i, int j, int[][] paths) {
    	if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
    	if(paths[i][j] != 0) return;
    		
    	if(i > 0 && grid[i-1][j] > grid[i][j]) {
    		longestPathInGridDfs(grid, i-1, j, paths);
    		paths[i][j] = Math.max(paths[i][j], paths[i-1][j]+1);
    	}
    	if(i < grid.length-1 && grid[i+1][j] > grid[i][j]) {
    		longestPathInGridDfs(grid, i+1, j, paths);
    		paths[i][j] = Math.max(paths[i][j], paths[i+1][j]+1);
    	}
    	if(j > 0 && grid[i][j-1] > grid[i][j]) {
    		longestPathInGridDfs(grid, i, j-1, paths);
    		paths[i][j] = Math.max(paths[i][j], paths[i][j-1]+1);
    	}
    	if(j < grid[0].length-1 && grid[i][j+1] > grid[i][j]) {
    		longestPathInGridDfs(grid, i, j+1, paths);
    		paths[i][j] = Math.max(paths[i][j], paths[i][j+1]+1);
    	}
    }
    
    
    //leetcode: Letter Combinations of a Phone Number
    /**
     * Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.
1 -
2 - abc
3 - def
4 - ghi
5 - jkl
6 - mno
7 - pqrs
8 - tuv
9 - wxyz
Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 
     */
    public List<String> letterCombinations(String digits) {
    	List<String> list = new ArrayList<String>();
    	if(digits == null || "".equals(digits)) return list;
    	String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    	letterCombinationsDfs(digits, map, 0, "", list);
    	return list;
    }
    
    public void letterCombinationsDfs(String digits, String[] map, int index, String result, List<String> list) {
    	if(index == digits.length()) {
    		list.add(result);
    		return;
    	}
    	char numChar = digits.charAt(index);
    	int mapIndex = Integer.parseInt(numChar+"");
    	String target = map[mapIndex];
    	for(int i = 0; i < target.length(); i++) {
    		letterCombinationsDfs(digits, map, index+1, result+target.charAt(i), list);
    	}
    }
    
	public static void main(String[] args) {
		DFS test = new DFS();
		int[] nums = {0};
		test.subsets(nums);
		
		int n = 4;
//		int[][] grid = new int[5][5];
		int[][] grid = {
				{6,2,1,5},
					{9,6,7,10},
					{10,3,7,16},
					{2,10,2,6}
		};
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
//				grid[i][j] = (int)(Math.random()*10)+1;
				System.out.print(grid[i][j] + ",");
			}
			System.out.println();
		}
		
		int max = test.longestPathInGrid(grid);
		System.out.println(max);
		
		List<String> list = test.letterCombinations("23");
		for(String e: list) {
			System.out.print(e + ",");
		}
	}
	
}
