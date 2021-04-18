package dp;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

	public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m-1).size();
        if(m == 0 || n == 0) return 0;
        if(m == 1 && n == 1) return triangle.get(0).get(0);
        
        int[][] dp = new int[2][n];
        
        dp[0][0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;
       
        for(int i = 1; i < m; i ++) {
            for(int j = 0; j <= i; j ++) {
                if(j == 0) {
                    dp[1][0] = triangle.get(i).get(0) + dp[0][0];
                    System.out.println(triangle.get(i).get(0));;
                } else if(j == i) {
                    dp[1][j] = triangle.get(i).get(i) + dp[0][i-1]; //ith column
                } else {
                    dp[1][j] = triangle.get(i).get(j) + Math.min(dp[0][j-1], dp[0][j]);
                }
            }
            for(int j = 0; j <= i; j ++) {
                dp[0][j] = dp[1][j];
            }
        }
        
        for(int j = 0; j < n; j ++) {
            if(min > dp[1][j]) {
                min = dp[1][j];
            }
        }
        return min;
    }
	
	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		triangle.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		triangle.add(list);
		
		Triangle test = new Triangle();
		test.minimumTotal(triangle);
	}
}
