public class UniquePaths {

	// backtracking
	public int uniquePaths(int m, int n) {
    	return backtracking(m, n, 0, 0);
    }

    public int backtracking(int m, int n, int row, int col) {
    	if(row == m-1 || col == n-1) 
    		return 1;
    	if(row >= m || col >= n) 
    		return 0;

    	return backtracking(m, n, row, col+1) + backtracking(m, n, row+1, col);
    }

    // 
    public int uniquePaths(int m, int n) {
    	int[][] memo = new int[m+1][n+1];
    	for(int i = 0; i <= m; i ++) {
    		for(int j = 0; j <= n; j ++) {
    			memo = -1;
    		}
    	}
    	return backtrackingWithMemoization(m, n, 0, 0, memo);
    }

    public int backtrackingWithMemoization(int m, int n, int row, int col, int[][] memo) {
    	if(row == m-1 || col == n-1) 
    		return 1;
    		
    	if(row >= m || col >= n) 
    		return 0;
    	
    	if(memo[row][col+1] == -1) {
    		memo[row][col+1] = backtracking(m, n, row, col+1, memo);	
    	}

    	if(memo[row+1][col] == -1) {
    		memo[row+1][col] = backtracking(m, n, row+1, col, memo);
    	}
    	return memo[row][col+1] + memo[row+1][col];
    }

    // bottom-up dynamic programming
    // space complexity is O(m*n)
    // time complexity is O(m*n)
    public int uniquePaths(int m, int n) {
    	int[][] dp = new int[m+1][n+1];
    	dp[m][n-1] = 1;
    	for(int i = m-1; i >= 0; i --) {
    		for(int j = n-1; j >= 0; j --) {
    			dp[i][j] = dp[i][j+1] + dp[i+1][j];
    		}
    	}
    	return dp[0][0];
    }
}
