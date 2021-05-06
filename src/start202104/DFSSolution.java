package start202104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFSSolution {

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
	
	/**
	 * https://leetcode-cn.com/problems/number-of-islands/
	 * 岛屿数量
	 * 
	 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
	 * 
	 * 解法一：
	 * （1）for each遍历，碰到1的，则开始DFS四周访问，碰到1则将1设置成0，直到碰到0为止
	 * 
	 * 时间复杂度：O(m*n)，其中m和n分别为行数和列数。
	 * 空间复杂度：O(m*n)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到m*n，每个元素需要遍历一遍
	 * 
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		int m = grid.length;
		if(m == 0) return 0;
		int n = grid[0].length;
		if(n == 0) return 0;
		
		int count = 0;
		//O(m*n)
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				
				//只有遇到1时，才开始dfs遍历
				if(grid[i][j] == '1') {
					numIslandsDFS(grid, i, j);
					count++;
				}
			}
		}
		return count;
    } 
	
	private void numIslandsDFS(char[][] grid, int i, int j) {
		if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
		
		if(grid[i][j] == '0') return; //遇到0则停止
		
		grid[i][j] = '0'; //碰到1则设置成0，表示该1已经访问过了
		numIslandsDFS(grid, i-1, j);
		numIslandsDFS(grid, i+1, j);
		numIslandsDFS(grid, i, j-1);
		numIslandsDFS(grid, i, j+1);
	}
	
	
	/**
	 * 岛屿数量
	 * 解法二：不修改原数组，而是使用visited数组，标识元素是否被访问过
	 * 时间复杂度为O(m*n)，空间复杂度为O(m*n)
	 * @param grid
	 * @param i
	 * @param j
	 */
	public int numIslandsII(char[][] grid) {
		int m = grid.length;
		if(m == 0) return 0;
		int n = grid[0].length;
		if(n == 0) return 0;
		
		boolean[][] visited = new boolean[m][n];
		
		int count = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == '1') {
					numIslandsDFS(grid, i, j, visited);
					count++;
				}
			}
		}
		return count;
    } 
	
	private void numIslandsDFS(char[][] grid, int i, int j, boolean[][] visited) {
		if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
		
		if(grid[i][j] == '0' || visited[i][j]) return;
		
		visited[i][j] = true; //该元素被访问过
		numIslandsDFS(grid, i-1, j);
		numIslandsDFS(grid, i+1, j);
		numIslandsDFS(grid, i, j-1);
		numIslandsDFS(grid, i, j+1);
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/max-area-of-island/
	 * 岛屿的最大面积
	 * 
	 */
	
	//维护一个全局最大值
	int maxArea = 0;
	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length;
		if(m == 0) return 0;
		int n = grid[0].length;
		if(n == 0) return 0;
		
		int[] max = new int[1];
		//O(m*n)
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				
				//只有遇到1时，才开始dfs遍历
				if(grid[i][j] == 1) {
					max[0] = 0; // 需要每次初始化成0
					
					//注意采用数组进行数据传输
					maxAreaOfIslandDFS(grid, i, j, max);
					
					//每次更新最大值
					maxArea = Math.max(maxArea, max[0]);
				}
			}
		}
		return maxArea;
    }
	
	private void maxAreaOfIslandDFS(int[][] grid, int i, int j, int[] max) {
		if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
		
		if(grid[i][j] == 0) return; //遇到0则停止
		
		max[0] ++; //遍历到一个1，面积+1
		grid[i][j] = 0; //碰到1则设置成0，表示该1已经访问过了
		maxAreaOfIslandDFS(grid, i-1, j, max);
		maxAreaOfIslandDFS(grid, i+1, j, max);
		maxAreaOfIslandDFS(grid, i, j-1, max);
		maxAreaOfIslandDFS(grid, i, j+1, max);
	}
	
	
	/**
	 * 岛屿的最大面积
	 * 解法二
	 */
	public int maxAreaOfIslandII(int[][] grid) {
		int m = grid.length;
		if(m == 0) return 0;
		int n = grid[0].length;
		if(n == 0) return 0;
		
		int max = 0;
		//O(m*n)
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				
				//只有遇到1时，才开始dfs遍历
				if(grid[i][j] == 1) {
					max = Math.max(max, maxAreaOfIslandDFS(grid, i, j));
				}
			}
		}
		return max;
    }
	
	private int maxAreaOfIslandDFS(int[][] grid, int i, int j) {
		if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return 0;
		
		if(grid[i][j] == 0) return 0; //遇到0则停止
		
		//每次访问第一个，面积为1
		int ans = 1;
		grid[i][j] = 0; //碰到1则设置成0，表示该1已经访问过了
		ans += maxAreaOfIslandDFS(grid, i-1, j);
		ans += maxAreaOfIslandDFS(grid, i+1, j);
		ans += maxAreaOfIslandDFS(grid, i, j-1);
		ans += maxAreaOfIslandDFS(grid, i, j+1);
		
		return ans;
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/surrounded-regions/
	 * 被围绕的区域
	 * 
	 * 矩阵中有三种元素：
	 * （1）字母X
	 * （2）被字母X包围的O
	 * （3）没有被字母X包围的O
	 * 
	 * 
	 * 任何边界上的 O 都不会被填充为 X
	 * 所以需要先把边界的O找出来，并且排除出去
	 * 
	 * （1）把边界的O以及相连的O找出来，标记成#
	 * （2）for each遍历，把 O -> X, # -> O
	 * 
	 * 时间复杂度为O(m*n)
	 * 空间复杂度为O(m*n)
	 * 
	 * @param board
	 */
	public void solve(char[][] board) {
		int m = board.length;
		if(m == 0) return;
		int n = board[0].length;
		if(n == 0) return;
		
		//将边界的O标记成#
		for(int i = 0; i < m; i++) {
			solveDFS(board, i, 0); //第一列
			solveDFS(board, i, n-1); //最后一列
		}
		
		for(int i = 0; i < n; i++) {
			solveDFS(board, 0, i); //第一行
			solveDFS(board, m-1, i); //最后一行
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] == 'O') {
					board[i][j] = 'X';
				}
				if(board[i][j] == '#') {
					board[i][j] = 'O';
				}
			}
		}
		
    }
	
	private void solveDFS(char[][] board, int i, int j) {
		if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
		if(board[i][j] != 'O') return;
		
		board[i][j] = '#'; //把O变成#
		solveDFS(board, i-1, j);
		solveDFS(board, i+1, j);
		solveDFS(board, i, j-1);
		solveDFS(board, i, j+1);
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
	 * 矩阵中最长递增路径
	 * 
	 * 记忆化DFS
	 * 
	 * @param matrix
	 * @return
	 */
	public int longestIncreasingPath(int[][] matrix) {
		int m = matrix.length;
		if(m == 0) return 0;
		int n = matrix[0].length;
		if(n == 0) return 0;
		int max = 0;
		int[][] res = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				max = Math.max(max, longestIncreasingPathDFS(matrix, i, j, res));			
			}
		}
		return max;
    }
	
	private int longestIncreasingPathDFS(int[][] matrix, int i, int j, int[][] res) {
		//如果res[i][j]不等于0，表示已经计算过，直接返回结果
		if(res[i][j] != 0) return res[i][j];
		
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		
		if(i+1 < matrix.length && matrix[i+1][j] > matrix[i][j]) {
			up = longestIncreasingPathDFS(matrix, i+1, j, res);
			res[i+1][j] = up;
		}
		if(i-1 >= 0 && matrix[i-1][j] > matrix[i][j]) {
			down = longestIncreasingPathDFS(matrix, i-1, j, res);
			res[i-1][j] = down;
		}
		if(j+1 < matrix[0].length && matrix[i][j+1] > matrix[i][j]) {
			right = longestIncreasingPathDFS(matrix, i, j+1, res);
			res[i][j+1] = right;
		}
		if(j-1 >= 0 && matrix[i][j-1] > matrix[i][j]) {
			left = longestIncreasingPathDFS(matrix, i, j-1, res);
			res[i][j-1] = left;
		}
		int ans = Math.max(Math.max(up, down), Math.max(left, right))+1;
		res[i][j] = ans;
		return ans;
		
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
	 * 二叉树中的最小深度
	 * 
	 * 需要考虑左子树为空，或者右子树为空的情况
	 * （1）左子树为空时，返回右子树高度
	 * （2）右子树为空时，返回左子树高度
	 * 
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if(root == null) return 0;
		if(root.left != null && root.right != null) {
			return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
		} else if(root.left == null) {
			return minDepth(root.right) + 1;
		} else {
			return minDepth(root.left) + 1;
		}
    }
	
	
	/**
	 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
	 * 
	 * 电话号码的字母组合
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		List<String> allList = new ArrayList<String>();
		if(digits == null || digits.length() == 0) return allList;
		
		//用map来保存数字和字母列表的对应关系
		Map<Character, String> numMap= new HashMap<Character, String>();
		initMap(numMap);
		
		letterDFS(digits, "", numMap, allList, 0);
		return allList;
    }
	
	
	private void letterDFS(String digits, String result, Map<Character, String> numMap, List<String> allList, int startIndex) {
		if(startIndex == digits.length()) {
			allList.add(result);
			return;
		}
		char curNum = digits.charAt(startIndex);
		if(numMap.containsKey(curNum)) {
			String numValue = numMap.get(curNum);
			char[] numValues = numValue.toCharArray();
			for(int i = 0; i < numValues.length; i++) {
				letterDFS(digits, result + numValues[i], numMap, allList, startIndex+1);
			}
		}
		
	}
	
	private void initMap(Map<Character, String> numMap) {
		numMap.put('2', "abc");
		numMap.put('3', "def");
		numMap.put('4', "ghi");
		numMap.put('5', "jkl");
		numMap.put('6', "mno");
		numMap.put('7', "pqrs");
		numMap.put('8', "tuv");
		numMap.put('9', "wxyz");
	}
	
	public static void main(String[] args) {
		DFSSolution test = new DFSSolution();
		List<String> res = test.letterCombinations("23");
		
	}
	
}
