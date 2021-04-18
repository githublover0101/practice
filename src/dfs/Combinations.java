public class Combinations {

	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(n == 0 || k == 0) return allList;
        if(n < k) return allList;

        List<Integer> subList = new ArrayList<Integer>();
        combineHelper(n, k, allList, subList, 1);
        return allList;
    }

    public void combineHelper(int n, int k, List<List<Integer>> allList, List<Integer> subList, int start) {
    	if(subList.size() == k) {
    		List<Integer> array = new ArrayList<Integer>(subList);
    		allList.add(array);
    	}

    	for(int i = start; i <= n; i ++) {
    		subList.add(i);
    		combineHelper(n, k, allList, subList, i+1);
    		subList.remove(subList.size()-1);
    	}
    }

}