package amazon;

import java.util.HashMap;
import java.util.Map;

public class ExcelTitleToNumber {

	public static Map<Character, Integer> map = new HashMap<Character, Integer>();
    static {
        int index = 1;
        for(char c = 'A'; c <= 'Z'; c++) {
            map.put(c, index++);
        }
    }
    
    public int titleToNumber(String s) {
        if(s == null || "".equals(s)) return 0;
        if(s.length() == 1) return map.get(s.charAt(0));
        if(s.length() == 2) return map.get(s.charAt(0))*26 + map.get(s.charAt(1));
        return titleToNumber(s.substring(0, s.length()-1)) + map.get(s.substring(s.length()-1));
    }

    public static void main(String[] args) {
    	ExcelTitleToNumber test = new ExcelTitleToNumber();
    	String s = "AAA";
    	int result = test.titleToNumber(s);
    	System.out.println(result);
    }
}