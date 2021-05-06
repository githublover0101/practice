package start202104;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class TestSolution {

	//给定数字n，找到字典序的第k小数字
	//yes
	public int findKthNumber(int n, int k) {
		int cur = 1;
		k = k-1;
		while(k > 0) {
			int count = getNodesCount(n, cur, cur+1);
			if(count <= k) { //说明不在这个子树上，需要从左往右找
				cur = cur+1;
				k = k-count;
			} else { //在当前子树上，需要从上往下找
				cur = cur*10;
				k = k-1;
			}
		}
		return cur;
	}
	
	//[first, next), [first, n+1)
	//注意long类型
	private int getNodesCount(int n, long first, long next) {
		int num = 0;
		while(first <= n) {
			num += Math.min(next, n+1) - first;
			first *= 10;
			next *= 10;
		}
		return num;
	}
	
	
	//接雨水
	//yes
	public int trap(int[] height) {
		int len = height.length;
		if(len == 0) return 0;
		
		int[] leftMax = new int[len];
		leftMax[0] = height[0];
		for(int i = 1; i < len; i++) {
			leftMax[i] = Math.max(leftMax[i-1], height[i]);
		}
		
		int[] rightMax = new int[len];
		rightMax[len-1] = height[len-1];
		for(int i = len-2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i+1], height[i]);
		}
		
		int sum = 0;
		for(int i = 0; i < len; i++) {
			sum += Math.min(leftMax[i], rightMax[i]) - height[i];
		}
		return sum;
	}
	
	
	//缺失的第一个正数
	//yes
	public int firstMissingPositive(int[] nums) {
		//1.全是负数，返回1，2.返回[1..N]之间，3.返回N+1
		//nums[x-1] = x
		int len = nums.length;
		if(len == 0) return 1;
		for(int i = 0; i < len; i++) {
			while(nums[i] >= 1 && nums[i] <= len && nums[nums[i]-1] != nums[i]) {
				int temp = nums[nums[i]-1];
				nums[nums[i]-1] = nums[i];
				nums[i] = temp;
			}
		}
		for(int i = 0; i < len; i++) {
			if(nums[i] != i+1) {
				return i+1;
			}
		}
		return len+1;
	}
	
	//二叉树中最大的路径和
	//yes
	int maxPath = Integer.MIN_VALUE; // 需要设置成int最小值，因为节点有可能是负数
	public int maxPathSum(TreeNode root) {
		if(root == null) return 0;
		maxPathToParent(root);
		return maxPath;
	}
	
	private int maxPathToParent(TreeNode root) {
		if(root == null) return 0;
		int left = maxPathToParent(root.left);
		int right = maxPathToParent(root.right);
		
		left = Math.max(left, 0);
		right = Math.max(right, 0);
		
		maxPath = Math.max(maxPath, root.val + left + right);
		return Math.max(left, right) + root.val;
	}
	
	//最长公共子序列
	//yes
	public int longestCommonSubsequence(String text1, String text2) {
		if(text1 == null || text1.length() == 0) return 0;
		if(text2 == null || text2.length() == 0) return 0;
		int len1 = text1.length();
		int len2 = text2.length();
		int[][] dp = new int[len1+1][len2+1];
		for(int i = 1; i <= len1; i++) {
			for(int j = 1; j <= len2; j++) {
				if(text1.charAt(i-1) == text2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[len1][len2];
	}
	
	
	//最长回文子串
	//dp[i][j] = true，表示s[i:j]是回文子串，有如下状态转移规则：
	//(1) dp[i][i] = true, s[i:i]
	//(2) dp[i][i+1] = true, if s[i] == s[i+1]
	//(3) dp[i][j] = dp[i+1][j-1], 当s[i] = s[j]时
	public String longestPalindrome(String s) {
		if(s == null || s.length() == 0) return s;
		int len = s.length();
		boolean[][] dp = new boolean[len][len];
		int max = 1;
		String res = s.substring(0, 1);
		for(int i = len-1; i >= 0; i--) {
			for(int j = i; j < len; j++) {
				if(s.charAt(i) == s.charAt(j)) {
					if(j - i < 2) {
						//s[i,i], s[i, i+1]
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i+1][j-1];
					}
				}
				//只有当存在回文子串时，且j-i+1的长度大于当前max时，更新max和res
				if(dp[i][j] && j-i+1 > max) {
					max = j-i+1;
					res = s.substring(i, j+1);
				}
			}
		}
		return res;
	}
	
	
	//买卖股票3
	//yes
	public int buyStockIII(int[] prices) {
		if(prices.length == 0) return 0;
		int len = prices.length;
		
		int minProfit = prices[0];
		int[] leftMax = new int[len];
		for(int i = 1; i < len; i++) {
			leftMax[i] = Math.max(leftMax[i-1], prices[i] - minProfit);
			minProfit = Math.min(minProfit, prices[i]);
		}
		
		int maxProfit = prices[len-1];
		int[] rightMax = new int[len];
		for(int i = len-2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i+1], maxProfit - prices[i]);
			maxProfit = Math.max(maxProfit, prices[i]);
		}
		
		int max = 0;
		for(int i = 0; i < len; i++) {
			max = Math.max(max, leftMax[i]+rightMax[i]);
		}
		
		return max;
	}
	
	
	//不同路径条数，with路障grid[i][j] = 1
	public int uniquePathsWithObstacles(int[][] grid) {
		int m = grid.length;
		if(m == 0) return 0;
		int n = grid[0].length;
		if(n == 0) return 0;
		
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 1) {
					dp[i][j] = 0; //此路不通
				} else {
					if(i == 0 && j == 0) {
						dp[i][j] = 1;
					}else if(i == 0) {
						dp[i][j] = dp[i][j-1];
					} else if(j == 0) {
						dp[i][j] = dp[i-1][j];
					} else {
						//来自上面和左边结果之和
						dp[i][j] = dp[i-1][j] + dp[i][j-1];
					}
				}
			}
		}
		return dp[m-1][n-1];
	}
	
	//无重复字符的最长子串
	//时间复杂度为O(n)，空间复杂度为O(n)
	public int lengthOfLongestSubstring(String s) {
		if(s == null || s.length() == 0) return 0;
		
		//map中保留对应的字符，以及出现的最近的下标
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int len = s.length();
		int max = 0;
		for(int start = 0, end=0; end < len; end++) {
			char cur = s.charAt(end);
			if(map.containsKey(cur)) {
				//当遇到重复的值时，开始下标为当前重复值+1，即下一个开始点
				start = Math.max(map.get(cur)+1, start);
			}
			//每次更新max长度，当前end下标，减去开始start下标，+1，即：end-start+1
			max = Math.max(max, end-start+1);
			map.put(cur, end);
		}
		return max;
	}
	
	
	//下一个更大元素
	public int[] nextGreaterElements(int[] nums) {
		if(nums.length == 0) return nums;
		int len = nums.length;
		int[] res = new int[len];
		Arrays.fill(res, -1);
		
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < 2*len; i++) {
			while(!stack.isEmpty() && nums[i%len] > nums[stack.peek()]) {
				res[stack.pop()] = nums[i%len];
			}
			stack.push(i%len);
		}
		return res;
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
	 * 矩阵中最长递增路径
	 * 
	 * 记忆化+DFS
	 * @param matrix
	 * @return
	 */
	public int longestIncreasingPath(int[][] matrix) {
		int m = matrix.length;
		if(m == 0) return 0;
		int n = matrix[0].length;
		if(n == 0) return 0;
		int[][] res = new int[m][n];
		int max = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				longestIncreasingPathDFS(matrix, i, j, res);
				max = Math.max(max, res[i][j]);
			}
		}
		return max;
	}	
	
	private int longestIncreasingPathDFS(int[][] matrix, int i, int j, int[][] res) {
		if(i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) return 0;
		if(res[i][j] > 0) return res[i][j];
		int max = 0;
		int up = 0, down = 0, left = 0, right = 0;
		if(i > 0 && matrix[i-1][j] > matrix[i][j]) {
			up = longestIncreasingPathDFS(matrix, i-1, j, res);
			res[i-1][j] = up;
		}
		if(i < matrix.length-1 && matrix[i+1][j] > matrix[i][j]) {
			down = longestIncreasingPathDFS(matrix, i+1, j, res);
			res[i+1][j] = down;
		}
		if(j > 0 && matrix[i][j-1] > matrix[i][j]) {
			left = longestIncreasingPathDFS(matrix, i, j-1, res);
			res[i][j-1] = left;
		}
		if(j < matrix[0].length-1 && matrix[i][j+1] > matrix[i][j]) {
			right = longestIncreasingPathDFS(matrix, i, j+1, res);
			res[i][j+1] = right;
		}
		max = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
		res[i][j] = max;
		return max;
	}
}
