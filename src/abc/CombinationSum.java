package abc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        List<Integer> subList = new ArrayList<Integer>();
        
        Arrays.sort(candidates);
        combinationSum2(candidates, target, allList, subList, 0);
        return allList;
    }
    
    public void combinationSum2(int[] candidates, int target, List<List<Integer>> allList, List<Integer> subList, int start) {
        if(target == 0) {
            allList.add(new ArrayList<Integer>(subList));
            return;
        }
        for(int i = start; i < candidates.length; i ++) {
        	if(i > 0 && candidates[i] == candidates[i-1]) // we need to ignore duplication
        		continue; 
            if(candidates[i] <= target) {
                subList.add(candidates[i]);
                combinationSum2(candidates, target - candidates[i], allList, subList, i+1);
                subList.remove(subList.size()-1);
            }
        }
    }

    public static void main(String[] args) {
    	CombinationSum test = new CombinationSum();
    	int[] candidates = {7,1,2,5,1,6,10};
    	int target = 8;
    	List<List<Integer>> allList = test.combinationSum2(candidates, target);
    	for(List<Integer> list: allList) {
    		for(Integer e: list) {
    			System.out.print(e + ", ");
    		}
    		System.out.println();
    	}
    	
    }
}