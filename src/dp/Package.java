public class Package {

	// dp(i, j) means for things(0 ... i), the rest weight is j, the biggest value
	// dp(i, j) = max{dp(i-1, j), dp(i-1, j-w[i]) + v[i]}
	public int getMaxValue(int[] v, int w, int maxWeight) {
		int len = v.length;
		int[][] dp = new int[len][maxWeight+1];
		
	}

}