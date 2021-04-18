public class FindCharacter {

	// time complexity is O(n), and space complexity is O(n)
	public char findFirst(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] cs = s.toCharArray();
		for(int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if(map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		char target = ' ';
		for(int i = 0; i < cs.length; i++) {
			if(map.get(cs[i]) == 1) {
				target = cs[i];
				break;
			}
		}
		return target;
	}

}