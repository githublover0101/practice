package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterMap {

	public List<String> mapString(String original, Map<Character, List<Character>> map) {
		List<String> allList = new ArrayList<String>();
		dfs(original, "", allList, map, 0);
		return allList;
	}

	public void dfs(String original, String result, List<String> allList, Map<Character, List<Character>> map, int start) {
		if(start == original.length()) {
			allList.add(result);
			return;
		}
		char c = original.charAt(start);
		if(map.containsKey(c)) {
			List<Character> cList = map.get(c);
			for(Character mapChar: cList) {
				dfs(original, result + mapChar, allList, map, start+1);
			}
		} else {
			dfs(original, result + c, allList, map, start+1);
		}
		
	}

	public static void main(String[] args) {
		CharacterMap test = new CharacterMap();
		String original = "face";
		Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
		List<Character> list = new ArrayList<Character>();
		list.add('@');
		list.add('4');
		list.add('A');
		map.put('a', list);
		list = new ArrayList<Character>();
		list.add('&');
		list.add('5');
		list.add('F');
		map.put('f', list);
		List<String> result = test.mapString(original, map);
		System.out.println(result);
	}
}