package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Amazon {

	public String getEnglishCharCount(int n) {
//		if(n < 1 || n > 1000) return "";
		if(n <= 20) {
			return get(String.valueOf(n));
		} else if(n <= 99) {
			int low = n%10;
			int high = n/10;
			return get(String.valueOf(high*10)) + " " + get(String.valueOf(low));
		} else if(n < 1000) {
			if(n%100 == 0) {
				return get(String.valueOf(n/100)) + " "  + get("hundred");
			} else {
				return get(String.valueOf(n/100)) + " "  + get("hundred") + " " + get("and") + " " + getEnglishCharCount(n%100);
			}
		} else if(n >= 1000) {
			if(n%1000 == 0) {
				return get(String.valueOf(n/1000)) + " "  + get("thousand");
			} else {
				return get(String.valueOf(n/1000)) + " " + get("thousand") + " " + get("and") + " " + getEnglishCharCount(n%1000);
			}
		}
		return get(String.valueOf(n));
	}
	
	public static void main(String[] args) {
		Amazon test = new Amazon();
		System.out.println(test.getEnglishCharCount(123));
		System.out.println(test.getEnglishCharCount(156));
		System.out.println(test.getEnglishCharCount(987));
		System.out.println(test.getEnglishCharCount(1234));
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
		
		map.put("20", "twenty");
		map.put("30", "thirdty");
		map.put("40", "fourty");
		map.put("50", "fifty");
		map.put("60", "sixty");
		map.put("70", "seventy");
		map.put("80", "eighty");
		map.put("90", "ninty");
		map.put("hundred", "hundred");
		map.put("thousand", "thousand");
		map.put("and", "and");
		return map.get(s);
	}
	
}
