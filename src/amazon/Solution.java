package amazon;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public String number2Word(int n) {
		if(n < 0) return "error";
		if(n <= 20) {
			return get(String.valueOf(n));
		} else if(n < 100) {
			return get(String.valueOf(n - n%10)) + get(String.valueOf(n%10));
		} else {
			int count = getNumber(n);
			return get(String.valueOf(n/count)) + get(String.valueOf(getNumber(n))) + get("and") + number2Word(n%count);
		} 
	}

	public int getNumber(int n) {
		int count = 1;
		while(n/10 != 0) {
			n = n/10;
			count = count*10;
		}
		return count;
	}

	public static void main(String[] args) {
		Solution test = new Solution();
		int n = 999;
		String result = test.number2Word(n);
		System.out.println(result);
		
		n = 9999;
		result = test.number2Word(n);
		System.out.println(result);

		n = 8765;
		result = test.number2Word(n);
		System.out.println(result);

		n = 45;
		result = test.number2Word(n);
		System.out.println(result);
		
		String s = "AAC";
		System.out.println(s.substring(s.length()-1));
		System.out.println(s.substring(1));
	}

	public String get(String s) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "one");
		map.put("2", "two");
		map.put("3", "three");
		map.put("4", "four");
		map.put("5", "five");
		map.put("6", "six");
		map.put("7", "seven");
		map.put("8", "eight");
		map.put("9", "nine");
		map.put("10", "ten");
		
		map.put("11", "eleven");
		map.put("12", "twelve");
		map.put("13", "thirdteen");
		map.put("14", "fourteen");
		map.put("16", "sixteen");
		map.put("17", "seventeen");
		map.put("18", "eighteen");
		map.put("19", "nineteen");
		
		map.put("20", "twenty ");
		map.put("30", "thirdty ");
		map.put("40", "fourty ");
		map.put("50", "fifty ");
		map.put("60", "sixty ");
		map.put("70", "seventy ");
		map.put("80", "eighty ");
		map.put("90", "ninty ");
		map.put("100", " hundred ");
		map.put("1000", " thousand ");
		map.put("hundred", " hundred ");
		map.put("thousand", " thousand ");
		map.put("and", " and ");
		return map.get(s);
	}

}