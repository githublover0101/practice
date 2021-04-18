package string;

import java.util.HashSet;
import java.util.Set;

public class LongestString{

    //time complexity is O(n*n)
    //space complexity is O(n)
	public int lengthOfLongestSubstring(String s) {
        int max = 1;
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<Character>();

        for(int i = 0; i < chars.length; i ++) {
        	int j = i;
        	set.clear();
        	while(j < chars.length && !set.contains(chars[j])) {
        		set.add(chars[j]);
        		j++;
        	}
        	if(j - i > max) max = j-i;
        }
        return max;
    }


    // time complexity is O(n), and space needs 256, is O(1)
    public int lengthOfLongestSubstring2(String s) {
        int max = 0;
        boolean[] exist = new boolean[256];
        int i = 0;
        for(int j = 0; j < s.length(); j ++) {
            while(exist[s.charAt(j)]) { //i指向上一次出现字符s[j]的下一个位置， 否则一直存在重复s[j]
                exist[s.charAt(i)] = false;
                i++;
            }
            exist[s.charAt(j)] = true;
            max = Math.max(j-i+1, max);
        }
        return max;
    }

    // time complexity is O(n), and space needs 256, is O(1)
    public int lengthOfLongestSubstring3(String s) {
        int max = 0;
        int[] index = new int[256];
        Arrays.fill(index, -1);

        int i = 0;
        for(int j = 0; j < s.length(); j ++) {
            if(index[s.charAt(j)] >= i) { //s[j]已经在该子串中出现过
                i = index[s.charAt(j)] + 1; //将开始index赋给s[j] 的下一位，否则s[j]依然重复
            }
            index[s.charAt(j)] = j;
            max = Math.max(j - i + 1, max);
        }
        return max;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || "".equals(s)) return 0;
        int len = s.length();
        int i = 0;

        int max = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        Set<Character> set = new HashSet<Character>();
        for(int j = 0; j < len; j ++) {
            if(!set.contains(s.charAt(j))) {
                if(set.size() == 2) {
                    max = Math.max(j - i,  max);
                    char pre = s.charAt(j-1);
                    char another = ' ';
                    for(char e: set) {
                        if(e != pre) {
                            another = e;
                            break;
                        }
                    }
                    i = map.get(another) + 1;
                    set.remove(another);
                    set.add(s.charAt(j));
                } else {
                    set.add(s.charAt(j));
                    map.put(s.charAt(j), j);
                }
            } else {
                map.put(s.charAt(j), j);
            }
        }
        return max;
    }


    public static void main(String[] args) {
    	LongestString test = new LongestString();
    	String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!";
    	int max = test.lengthOfLongestSubstring(s);
    	System.out.println(max);
    }

}