package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatenationSubstring{

	// if curr not in the list, startIndex = j + wordLen
	// if curr in the list, and not show in the map, j = j + wordLen, check next
	// show in the map, and show count <= total show count, j = j + wordLen
	// show in the map, but show count > total show count, i = lastIndex + wordLen
	// show in the map & is the last word, add i, and i = i + wordLen
	public List<Integer> findSubstring(String S, String[] L) {
     	List<Integer> list = new ArrayList<Integer>();
     	if(S == null || S.length() == 0 || L.length == 0) return list;
     	
     	int wordLen = L[0].length();
     	int len = S.length();

     	Map<String, Integer> originalMap = new HashMap<String, Integer>();
     	Map<String, Integer> foundMap =  new HashMap<String, Integer>();
     	for(String s: L) {
     		int count = originalMap.containsKey(s) ? originalMap.get(s) + 1 : 1;
     		originalMap.put(s, count);
     	}

     	int i = 0;
     	for(int j = 0; j + wordLen <= len; j += wordLen) {
     		String curr = S.substring(j, j + wordLen);
     		if(originalMap.containsKey(curr)) {
     			if(foundMap.containsKey(curr)) {
     				int count = foundMap.get(curr);
     				if(count > originalMap.get(curr)) {

     				} else {
     					
     				}
     			} else {
     				foundMap.put(curr, 1);
     			}
     		} else {
     			i = j + wordLen;
     		}
     	}
     	return list;
    }

    public static void main(String[] args) {
    	ConcatenationSubstring test = new ConcatenationSubstring();
    	String S = "abababab";
    	String[] L = {"a","b","a"};
    	List<Integer> list = test.findSubstring(S, L);
    	for(int e: list) {
    		System.out.println(e);
    	}
    }
}