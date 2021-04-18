public class CombinationSum {
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
   		List<List<Integer>> allList = new ArrayList<List<Integer>>();
   		List<Integer> subList = new ArrayList<Integer>();

   		combinationSumHelper(candidates, target, 0, subList, allList);
   		return allList;
    }

    public void combinationSumHelper(int[] candidates, int target, int start, List<Integer> subList, List<List<Integer>> allList) {
    	if(target < 0) return;
    	if(target == 0) {
    		allList.add(new ArrayList<Integer>(subList));
    	}

    	for(int i = start; i < candidates.length; i ++) {
    		subList.add(candidates[i]);
    		combinationSumHelper(candidates, target - candidates[i], i, subList, allList);
    		subList.remove(subList.size()-1);
    	}
    }
}