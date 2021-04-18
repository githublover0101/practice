public class Permutation {

	public List<List<Integer>> permute(int[] num) {
    	List<List<Integer>> allList = new ArrayList<List<Integer>>();
    	List<Integer> subList = new ArrayList<Integer>();
    	boolean[] visited = new boolean[num.length];
    	permuteHelper(num, allList, subList, visited);
    	return allList; 
    }

    public void permuteHelper(int[] num, List<List<Integer>> allList, List<Integer> subList, boolean[] visited) {
    	if(subList.size() == num.length) {
    		List<Integer> list = new ArrayList<Integer>(subList);
    		if(!allList.contains(list)) allList.add(list);
    		return;
    	}
    	for(int i = 0; i < num.length; i ++) {
    		if(i > 0 && !visited[i-1] && num[i] == num[i-1]) continue;
    	    if(!visited[i]) {
    	        visited[i] = true;
    	        subList.add(num[i]);
    		    permuteHelper(num, allList, subList, visited);
    		    subList.remove(subList.size()-1);
    		    visited[i] = false;
    	    }
    	}
    }

    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
    	List<Integer> subList = new ArrayList<Integer>();
    	boolean[] visited = new boolean[num.length];
    	Arrays.sort(num);
    	permuteHelper(num, allList, subList, visited);
    	return allList; 
    }

}