package array;

import java.util.HashSet;

public class CSS {

	public String getCss(String origin) {	
		char one = getMin(origin, 1, 2);
		char two = getMin(origin, 3, 4);
		char three = getMin(origin, 5, 6);
		StringBuffer sb = new StringBuffer();
		sb.append("#").append(one).append(two).append(three);
		return sb.toString();
	}

	public char getMin(String s, int start, int end) {
		char c1 = s.charAt(start);
		char c2 = s.charAt(end);
		HashSet<Character> set = new HashSet<Character>();
		set.add((char) (c1 + 1));
		set.add((char) (c1 - 1));
		set.add((char) (c2 + 1));
		set.add((char) (c2 - 1));
		set.add(c1);
		set.add(c2);
		
		int min = Integer.MAX_VALUE;
		char minChar = ' ';
		for(char c: set) {
			int tmp = Math.abs(compute(c1, c2, c, c)); //绝对值
			if(min > tmp) {
				min = tmp;
				minChar = c;
			}
		}
		return minChar;
	}

	public int compute(char c1, char c2, char c3, char c4) {
		char c = (char) (c1*16+c2);
		char d = (char) (c3*16+c4);
		return -(c - d)^2;
	}

	public static void main(String[] args) {
		CSS test = new CSS();
		String css = "#09f166";
		String result = test.getCss(css);
		System.out.println(result);
	}
}