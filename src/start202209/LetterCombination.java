package start202209;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombination {

	public List<String> letterCombinations(String digits) {
		List<String> resultList = new ArrayList<String>();
		if(digits == null || digits.length() == 0) return resultList;

		String result = "";
		Map<Character, String> map = new HashMap<Character, String>();
		initMap(map);
		letterCombine(digits, resultList, result, map, 0);
		return resultList;
    }

    private void letterCombine(String digits, List<String> resultList, String result, Map<Character, String> map, int startIndex) {
    	if(startIndex == digits.length()) {
    		resultList.add(result);
    		return;
    	}
    	
    	char cur = digits.charAt(startIndex);
    	String curLetter = map.get(cur);
    	int len = curLetter.length();
    	for(int i = 0; i < len; i++) {
    		letterCombine(digits, resultList, result + curLetter.charAt(i), map, startIndex+1);
    	}
    	
    }
    
    private void initMap(Map<Character, String> map) {
    	map.put('1', "");
    	map.put('2', "abc");
    	map.put('3', "def");
    	map.put('4', "ghi");
    	map.put('5', "jkl");
    	map.put('6', "mno");
    	map.put('7', "pqrs");
    	map.put('8', "tuv");
    	map.put('9', "wxyz");
    }
	
}
