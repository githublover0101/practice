package string;

public class LongestPalindromicString {

	
    public String longestPalindrome(String s) {
    		if(s == null || "".equals(s)) return s;
    		String palin = s.substring(0,1);
    		int max = 1;
       	int len = s.length();
       	boolean[][] dp = new boolean[len][len];
       	for(int i = 0; i < len; i ++) {
       		dp[i][i] = true;
       		if(i < len-1 && s.charAt(i) == s.charAt(i+1)) {
       			dp[i][i+1] = true;
       			if(max < 2) {
       				max = 2;
       				palin = s.substring(i, i+2);
       			}
       		}
       	}
       	
        // end index first, start index second, because need to j-1 & i+1
       	for(int j = 2; j < len; j ++) {
       		for(int i = 0 ; i < j-1; i++) {
       			if(s.charAt(i) == s.charAt(j)) {
       				dp[i][j] = dp[i+1][j-1];
       			} else {
       				dp[i][j] = false;
       			}
       			if(dp[i][j] && j - i + 1 > max) {
       				max = j - i + 1;
       				palin = s.substring(i, j+1);
       			}
       		}
       	}
       
       	return palin;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> allList = new ArrayList<List<String>>();
        List<String> subList = new ArrayList<String>();
        partitionHelper(s, allList, subList);
        return allList;
    }

    public void partitionHelper(String s, List<List<String>> allList, List<String> subList) {
        if(s.length() == 0) {
          allList.add(subList);
          return;
        }
        for() {
          
        }
    }

    public static void main(String[] args) {
    	LongestPalindromicString test = new LongestPalindromicString();
    	String s = "abcba";
    	String result = test.longestPalindrome(s);
    	System.out.println(result);
    }

}