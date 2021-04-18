package dp;

public class EatRice {

	// dp
	// space complexity is O(n*n)
	public int eatMax(int[][] rices) {
		int m = rices.length;
		if(m == 0) return 0;
		int n = rices[0].length;
		int[][] dp = new int[m][n];

		dp[0][0] = rices[0][0];
		int max = dp[0][0];
		for(int j = 1; j < n; j ++) {
			dp[0][j] = dp[0][j-1] + rices[0][j];
		}

		for(int i = 1; i < m; i ++) {
			dp[i][0] = dp[i-1][0] + rices[i][0];
		}

		for(int i = 1; i < m; i ++) {
			for(int j = 1; j < n; j ++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + rices[i][j];
			}
		}
		
		for(int i = 0; i < m; i ++) {
			max = Math.max(dp[i][n-1], max);
		}
		for(int j = 0; j < n; j ++) {
			max = Math.max(dp[m-1][j], max);
		}
		return max;
	}

	// dp 
	// time complexity is O(n)
	public int eatMax2(int[][] rices) {
		int m = rices.length;
		if(m == 0) return 0;
		int n = rices[0].length;

		int[] dp = new int[n+1];
		dp[0] = 0; //!!!
		int max = dp[0];
		for(int i = 0; i < m; i ++) {
			for(int j = 0; j < n; j ++) {
				dp[j+1] = Math.max(dp[j+1], dp[j]) + rices[i][j];
				max = Math.max(dp[j+1], max);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		EatRice test = new EatRice();
		int[][] num = {{1,0,1,0},{0,2,1,3},{1,0,1,0},{1,0,1,0}};
		int result1 = test.eatMax(num);
		int result2 = test.eatMax2(num);

		System.out.println(result1);
		System.out.println(result2);
	}
}