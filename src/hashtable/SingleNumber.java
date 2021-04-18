public class SingleNumber {
	
	public int singleNumber1(int[] A) {
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	int len = A.length;
    	for(int i = 0; i < len; i ++) {
    		if(map.containsKey(A[i])) {
    			map.put(A[i], map.get(A[i]) + 1);
    		} else {
    			map.put(A[i], 1);
    		}
    	}   

    	for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
    		if(entry.getValue() == 1) {
    			return entry.getKey();
    		}
    	}
    	return -1;
    }

	public int singleNumber(int[] A) {
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < A.length; i ++) {
			if(set.contains(A[i])) {
				set.remove(A[i]);
			} else {
				set.add(A[i]);
			}
		}
		return set.iterator().next();
	}   

	public int singleNumber(int[] A) {
		int num = 0;
		for(int i = 0; i < A.length; i ++) {
			num ^= A[i];
		}
		return num;
	}

}