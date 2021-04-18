package dp;

public class LongestCommonString {

	// s[i] == t[j], c[i][j] = c[i-1][j-1]+1
	// s[i] != t[j], c[i][j] = max{c[i-1][j], c[i][j-1]}
	public String getLongestCommonSubsequence(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		int[][] dp = new int[len1+1][len2+1];
		for(int i = 0; i < len1; i ++) {
			for(int j = 0; j < len2; j ++) {
				if(c1[i] == c2[j]) {
					dp[i+1][j+1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
			}
		}

		int lcsMaxLength = dp[len1][len2];
		System.out.println("longest common subsequence: " + lcsMaxLength);

		StringBuffer sb = new StringBuffer();
		int index1 = len1;
		int index2 = len2;
		while(index1 > 0 && index2 > 0) {
			if(c1[index1-1] == c2[index2-1]) {
				sb.append(c1[index1-1]);
				index1 --;
				index2 --;
			} else if(dp[index1][index2-1] >= dp[index1-1][index2]) {
				index2 --;
			} else {
				index1 --;
			}
		} 
		return sb.reverse().toString();
	}	

	// s[i] == t[j], c[i][j] = c[i-1][j-1]+1
	// s[i] != t[j], c[i][j] = 0
	public String getLongestCommonSubstring(String s1, String s2) {
		int maxLen = 0;
		int endIndex = 0;
		int len1 = s1.length();
		int len2 = s2.length();
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		int[][] dp = new int[len1+1][len2+1];
		for(int i = 0; i < len1; i ++) {
			for(int j = 0; j < len2; j ++) {
				if(c1[i] == c2[j]) {
					dp[i+1][j+1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = 0;
				}
				if(maxLen < dp[i+1][j+1]) {
					maxLen = dp[i+1][j+1];
					endIndex = i;
				}
			}
		}

		System.out.println("longest common substring: " + maxLen);
		return s1.substring(endIndex-maxLen+1, endIndex+1);
	}

	public static void main(String[] args) {
		LongestCommonString test = new LongestCommonString();
		String s1 = "abcdeehshsh";
		String s2 = "deehyuwyahsn";
		String lcsequence = test.getLongestCommonSubsequence(s1, s2);
		System.out.println(lcsequence);

		String lcstring = test.getLongestCommonSubstring(s1, s2);
		System.out.println(lcstring);
	}
}