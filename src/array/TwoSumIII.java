public class TwoSumIII {

	// use a map to save the nodes
	// space complexity is O(n)
	// add, O(1)
	// find, O(n), for each the keys, then find another elem in map, O(1) --> total is O(n)
	// should consider repeated elements
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public void add(int value) {
		int count = map.containsKey(value) ? map.get(value) : 0;
		map.put(value, count+1);
	}	

	public boolean find(int value) {
		for(Map.Entry<Integer, Integer> entry: map) {
			int elem = entry.getKey();
			int anotherElement = value - elem;
			if(anotherElement == elem) {
				if(map.get(anotherElement) >= 2) return true;
			} else {
				if(map.containsKey(anotherElement)) {
					return true;
				}
			}
			
		}
		return false;
	}

}