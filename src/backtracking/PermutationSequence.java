package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationSequence {

	public String getPermutation(int n, int k) {
        List<String> list = permutations(n);
        if(list.size() < k) return "";
        Collections.sort(list);
        for(int i  = 0; i < 24; i ++) {
        	System.out.println(list.get(i));
        }
        return list.get(k-1);
    }
    
	public List<String> permutations(int n) {
        List<String> list = new ArrayList<String>();
        boolean[] visited = new boolean[n+1];
        dfs(n, "", list, visited);
        return list;
    }
    
    public void dfs(int n, String s, List<String> list, boolean[] visited) {
        if(s.length() == n) {
            list.add(s);
            return;
        }
        for(int i = 1; i <= n; i ++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(n, s + "" + i, list, visited);
                visited[i] = false;
            }
            
        }
    }

    public static void main(String[] args) {
    	PermutationSequence test = new PermutationSequence();
    	String s = test.getPermutation(4,9);
    	System.out.println(s);
    }
}