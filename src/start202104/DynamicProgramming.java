package start202104;

import java.util.List;

public class DynamicProgramming {

	/**
	 * https://leetcode.com/problems/longest-increasing-subsequence/
	 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
	 * 
	 * 定义递推表达式：
	 * dp[i]表示以 nums[i]为结尾的递增子序列的长度
	 * 
	 * (1) dp[i] = dp[k]+1, nums[i] > nums[k], 其中k属于[0,i)之间的dp[k]属于其中的最大值
	 * (2) dp[i] = 1
	 * 找dp中最大值
	 * 
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {
		//dp[i] = dp[k]+1, nums[i] > nums[k], 其中k属于[0,i)之间的dp[k]属于其中的最大值
		//dp[i] = 1
		if(nums.length == 0) return 0;
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int max = 1;
		for(int i = 1; i < nums.length; i++) {
			for(int k = 0; k < i; k++) {
				if(nums[i] > nums[k]) {
					dp[i] = Math.max(dp[i], dp[k]);
				}
			}
			dp[i] += 1;
            max = Math.max(max, dp[i]);
		}
		return max;
    }
	
	/**
	 * https://leetcode.com/problems/longest-common-subsequence/
	 * 最长公共子序列
	 * 
	 * 假设字符串text1和text2的长度分别为 m 和 n，创建m+1行 n+1列的二维数组 dp，其中dp[i][j]表示text1[0:i]和text2[0:j]的最长公共子序列的长度
	 * 
	 * 状态转移方程：
	 * dp[i][j] = dp[i-1][j-1]+1, 当text1[i-1] = text2[j-1]时
	 * dp[i][j] = max(dp[i][j-1], dp[i-1][j]), 当text1[i-1] != text2[j-1]时
	 * 
	 * 时间复杂度为O(mn)，空间复杂度为O(mn)
	 * @param text1
	 * @param text2
	 * @return
	 */
	public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text1.length() == 0) return 0;
        if(text2 == null || text2.length() == 0) return 0;
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1]; 
        for(int i = 1; i <= len1; i ++) {
            char c1 = text1.charAt(i-1);
        	for(int j = 1; j <= len2; j ++) {
        		char c2 = text2.charAt(j-1);
        		if(c1 == c2) {
        			dp[i][j] = dp[i-1][j-1] + 1;
        		} else {
        			dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
        		}
        	}
        }
        return dp[len1][len2];
    }
	
	/**
	 * https://leetcode.com/problems/unique-paths/
	 * 不同路径
	 * 
	 * 只能往下走或者往右走，从左上角走到右下角有多少种走法
	 * 
	 * dp[i][j] = dp[i-1][j] + dp[i][j-1];
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		if(m == 0 || n == 0) return 0;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			dp[i][0] = 1;
		}
		for(int j = 0; j < n; j++) {
			dp[0][j] = 1;
		}
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
    }
	
	/**
	 * https://leetcode.com/problems/unique-paths-ii/
	 * 
	 * 不同路径II
	 * 
	 * 从左上角往右下角走，有多少种路径。其中包含路障，当grid[i][j] = 1时，表示有路障
	 * 
	 * @param grid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] grid) {
		int m = grid.length;
		if(m == 0) return 0;
		int n = grid[0].length;
		if(n == 0) return 0;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i ++) {
			for(int j = 0; j < n; j ++) {
				
				//如果grid[i][j] == 1，表示有路障，则dp[i][j] = 0
				if(grid[i][j] == 1) {
					dp[i][j] = 0;
				} else{
					//在grid[i][j]可达的前提下
					if(i == 0 && j == 0) {
						dp[i][j] = 1;
					} else if(i == 0) {
						dp[i][j] = dp[i][j-1];
					} else if(j == 0) {
                        dp[i][j] = dp[i-1][j];
                    } else {
						dp[i][j] = dp[i-1][j] + dp[i][j-1];
					}
				}
			}
		}
		return dp[m-1][n-1];
    }
	
	
	/**
	 * https://leetcode.com/problems/triangle/
	 * 三角形最小路径和
	 * 时间复杂度为O(n^2)，空间复杂度为O(n^2)
	 * @param triangle
	 * @return
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		if(triangle == null) return 0;
		int rows = triangle.size();
		if(rows == 0) return 0;
		int columns = triangle.get(rows-1).size();
        
		int[][] sum = new int[rows+1][columns+1];
		
		//行数遍历，从下往上遍历
		for(int i = rows-1; i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				sum[i][j] = triangle.get(i).get(j) + Math.min(sum[i+1][j], sum[i+1][j+1]);
			}
		}
		return sum[0][0];
    }
	
	//时间复杂度为O(n^2)，空间复杂度为O(n)
	public int minimumTotalII(List<List<Integer>> triangle) {
		if(triangle == null) return 0;
		int rows = triangle.size();
		if(rows == 0) return 0;
		
		int[] sum = new int[rows+1];
		
		//行数遍历，从下往上遍历
		for(int i = rows-1; i >= 0; i--) {
			for(int j = 0; j <= i; j++) {
				sum[j] = triangle.get(i).get(j) + Math.min(sum[j], sum[j+1]);
			}
		}
		return sum[0];
    }
	
	/**
	 * https://leetcode.com/problems/edit-distance/
	 * 编辑距离
	 * 
	 * 动态规划：定义状态转移方程dp[i][j]
	 * 
	 * dp[i][j]表示word1中前i个字符，变化成word2中前j个字符，最短需要操作的次数
	 * 
	 * 1) dp[i][0] = i，word2一个字母也没有，需要进行删除 i 次操作
	 * 2) dp[0][j] = j，word1一个字母也没有，需要进行插入 j 次操作
	 * 3) dp[i][j] = dp[i-1][j-1]，word1[i-1] = word2[j-1]，则不需要进行操作
	 * 4) dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1 次操作
	 * 
	 * 插入操作：dp[i][j] = dp[i][j-1]+1
	 * 删除操作：dp[i][j] = dp[i-1][j]+1
	 * 修改操作：dp[i][j] = dp[i-1][j-1]+1
	 * 
	 * 
	 * 时间复杂度为O(mn)，空间复杂度为O(mn)
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	public int minDistance(String word1, String word2) {
		if(word1 == null || word2 == null) return 0;
		int len1 = word1.length();
		int len2 = word2.length();
		int[][] dp = new int[len1+1][len2+1];
		//当word2是空串时
		for(int i = 0; i <= len1; i ++) {
			dp[i][0] = i;
		}
		
		//当word1是空串时
		for(int j = 0; j <= len2; j ++) {
			dp[0][j] = j;
		}
		for(int i = 1; i <= len1; i ++) {
			for(int j = 1; j <= len2; j ++) {
				
				//当 i-1 ==j-1 字母时，则无需操作：dp[i][j] = dp[i-1][j-1]
				if(word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1;
				}
			}
		}
		return dp[len1][len2];
    }
	
	private int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}
	
	
	/**
	 * https://leetcode-cn.com/problems/minimum-path-sum/
	 * 最小路径和
	 * 
	 * 时间复杂度为O(mn)，空间复杂度为O(mn)
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		if(grid.length == 0) return 0;
		int m = grid.length;
		if(grid[0].length == 0) return 0;
		int n = grid[0].length;
		
		int[][] dp = new int[m][n];
		dp[0][0] = grid[0][0];
		for(int i = 1; i < m; i++) {
			dp[i][0] = dp[i-1][0] + grid[i][0];
		}
		for(int j = 1; j < n; j++) {
			dp[0][j] = dp[0][j-1] + grid[0][j];
		}
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n; j++) {
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
			}
		}
		return dp[m-1][n-1];
    }
	
	//优化空间，使用一维数组，每次只存储上一行的dp值
	//时间复杂度为O(mn)，空间复杂度为O(n)
	public int minPathSumII(int[][] grid) {
		if(grid.length == 0) return 0;
		int m = grid.length;
		if(grid[0].length == 0) return 0;
		int n = grid[0].length;
		
		int[] dp = new int[n];
		dp[0] = grid[0][0];
        
        for(int i = 1; i < n; i ++) {
            dp[i] = dp[i-1] + grid[0][i];
        }
        
        for(int i = 1; i < m; i++) {
			dp[0] = dp[0] + grid[i][0];
			for(int j = 1; j < n; j++) {
				dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
			}
		}
		return dp[n-1];
    }
	
	//允许修改原数组，利用原数组来做dp值的存储
	//时间复杂度为O(mn)，空间复杂度为O(1)
	public int minPathSumIII(int[][] grid) {
		if(grid.length == 0) return 0;
		int m = grid.length;
		if(grid[0].length == 0) return 0;
		int n = grid[0].length;
		for(int i = 1; i < m; i++) {
			grid[i][0] = grid[i-1][0] + grid[i][0];
		}
		for(int j = 1; j < n; j++) {
			grid[0][j] = grid[0][j-1] + grid[0][j];
		}
		for(int i = 1; i < m; i++) {
			for(int j = 1; j < n; j++) {
				grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
			}
		}
		return grid[m-1][n-1];
    }
	
	/**
	 * https://leetcode.com/problems/trapping-rain-water/
	 * 接雨水
	 * 
	 * 解法一：暴力解法
	 * 
	 * （1）初始化 sum = 0
	 * （2）从左到右遍历height
	 * 		- 初始化 leftMax, rightMax = 0
	 * 		- 从左边0位到当前位置 [0...i] 中寻找最大值 leftMax = max(height[i], leftMax)
	 * 		- 从右边当前位置到末尾 [i...len-1] 中寻找最大值 rightMax = max(height[i], rightMax)
	 * 		- sum = sum + min(leftMax, rightMax) - height[i] （左右两边最大值取小的那一个，减去当前的高度值）
	 * 
	 * 时间复杂度为O(n^2)，空间复杂度为O(1)
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
		if(height.length == 0) return 0;
        int sum = 0;
        
        int len = height.length;
        
        //[1..len-2]位
        for(int i = 1; i < len-1; i++) {
        	int leftMax = 0;
            int rightMax = 0;
            
            //[0..i]之间
        	for(int j = i; j >= 0; j--) { //从右往左找
        		leftMax = Math.max(leftMax, height[j]);
        	}
        	
        	//[i...len-1]之间
        	for(int j = i; j < len; j++) { //从左往右找
        		rightMax = Math.max(rightMax, height[j]);
        	}
        	
        	//左边最大值和右边最大值中取较小的那个 min，减去当前高度值
        	sum += Math.min(leftMax, rightMax) - height[i];
        }
        return sum;
    }
	
	/**
	 * 解法二：动态规划
	 * 
	 * （1）找到数组中从下标i到最左端最高的条形块高度 leftMax
	 * （2）找到数组中从下标i到最右端最高的条形块高度 rightMax
	 * （3）扫描数组height并更新答案：
	 * 累加 min(leftMax[i], rightMax[i]) - height[i] 到 sum 值中
	 * 
	 * 时间复杂度为O(n)，空间复杂度为O(n)
	 * 
	 * @param height
	 * @return
	 */
	public int trapII(int[] height) {
		if(height.length == 0) return 0;
        int sum = 0;
        
        int len = height.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        
        //leftMax[i] = max(leftMax[i-1], height[i])
        leftMax[0] = height[0];
        for(int i = 1; i < len; i++) {
        	leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        
        
        //rightMax[i] = max(rightMax[i+1], height[i])
        rightMax[len-1] = height[len-1];
        for(int i = len-2; i >= 0; i--) {
        	rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        
        //区间[1...len-2]
        for(int i = 1; i < len-1; i++) {
        	sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }
	
}
