public class FindFirst {

	// 输入一个字符串，求第一个只出现一次的字符
	public int findFirst(String s) {
		char[] cs = s.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < cs.length; i ++) {
			char c = cs[i];
			if(map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
	}

}