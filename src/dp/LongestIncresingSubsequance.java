package dp;

public class LongestIncresingSubsequance {

//	dp[i] = 1 + max{dp[j]}, i > j > 0 && A[i] > A[j]
//	dp[i] = 1
	public int getLongest(int[] A) {
		int len = A.length;
		if(len <= 1) return len;
		int[] dp = new int[len];
		int max = 1;
		dp[0] = 1;
		for(int i = 1; i < len; i ++) {
			dp[i] = 1;
			int tmpMax = 0;
			for(int j = 1; j < i; j ++) {
				if(A[i] > A[j]) {
					if(tmpMax < dp[j])
						tmpMax = dp[j];
				}
			}
			dp[i] += tmpMax;
			if(max < dp[i]) {
				max = dp[i];
			}
		}
		return max;
	}
	
}
