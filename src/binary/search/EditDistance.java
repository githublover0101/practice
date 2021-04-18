package binary.search;

/**
 * Created by huanchen on 10/22/14.
 */
public class EditDistance {

//    d[i][j] means s[0...i] contains t[0...j] needs steps count
//    d[i][j] = d[i-1][j-1], s[i]=t[j]
//    d[i][j] = d[i-1][j-1] + 1, replace
//    d[i][j] = d[i][j-1] + 1, insert
//    d[i][j] = d[i-1][j] + 1, delete
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] d = new int[len1][len2];
        for(int i = 0; i < len1; i ++) {
            d[i][0] = i;
        }
        for(int i = 0; i < len2; i ++) {
            d[0][i] = i;
        }

        for(int i = 1; i < len1; i ++) {
            for(int j = 1; j < len2; j ++) {
                if(word1.charAt(i) == word2.charAt(j)) {
                    d[i][j] = d[i-1][j-1];
                } else {
                    d[i][j] = Math.min(Math.min(d[i-1][j] + 1, d[i-1][j-1] + 1), d[i][j-1] + 1);
                }
            }
        }
        return d[len1-1][len2-1];
    }

}
