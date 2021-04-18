package abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StringSeperator {

	public String[] separate(String input, String[] separators) {
		int len = input.length();
		return null;
	}
	
	// O(nm)
    public boolean strStr(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        for(int i = 0; i < len1-len2+1; i++) {
            int j = i;
            boolean flag = true;
            while(j-i < len2) {
                if(needle.charAt(j-i) != haystack.charAt(j)) {
                    flag = false;
                    break;
                }
                j++;
            }
            if(flag) return true;
        }
        return false;
    }
    
    public String[] split(String input, String[] separators) {
		int len = input.length();
		List<String> list = new LinkedList<String>();
		int i = 0;
		for(int j = 1; j < len; ) {
			if(isMeetSeparator(input, j, separators) != -1) {
				String token = input.substring(i, j);
				i = j+;
				j = i+1;
				list.add(token);
			} else {
				j++;
			}
		}
		String[] result = new String[list.size()];
		i = 0;
		for(String e: list) {
			result[i++] = e;
		}
		return result;
	}
	
	
	
	public int isMeetSeparator(String input, int start, String[] separators) {
		Map<Character, List<String>> map = new HashMap<Character, List<String>>();
		for(String sp: separators) {
			char c = sp.charAt(0);
			List<String> list = null;
			if(map.containsKey(c)) {
				list = map.get(c);
				list.add(sp);
				map.put(c, list);
			} else {
				list = new ArrayList<String>();
				map.put(c, list);
			}
		}
		
		for(int i = 0; i < separators.length; i++) {
			String sp = separators[i];
			String inputSub = input.substring(start);
			if(inputSub.startsWith(sp)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
}
