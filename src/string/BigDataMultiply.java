package string;

import java.math.BigInteger;

public class BigDataMultiply {

	// xxxxxxxxxxxxxxx * yyyyyyyyyyyyy
	// only work for positive numbers
	public String multiply(String num1, String num2) {
		int len = num2.length();
		String[] subs = new String[len];

		int zeroCount = 0;
		for(int i = len-1; i >= 0; i --) {
			StringBuffer sb = multiply(num1, num2.charAt(i));
			int counter = 0;
			while(counter < zeroCount) {
				sb.append("0");
				counter++;
			}
			subs[i] = sb.toString();
			zeroCount++;
		}

		String result = "0";
		for(int i = 0; i < len; i ++) {
			result = addTwoNumbers(result, subs[i]);
		}
		return result;
	}

	public static void main(String[] args) {
		BigDataMultiply test = new BigDataMultiply();
		String s1 = "111111111111111111111111111111111111111111111111111";
		String s2 = "11111111111111111111111111111111111111111111111111111111";
		String result = test.multiply(s1, s2);
		System.out.println(result);
		BigInteger bigInt = new BigInteger(s1);
	}

	// plus s1+s2
	// reverse the result
	public String addTwoNumbers(String s1, String s2) {
		int point = 0;
		StringBuffer sb = new StringBuffer();
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int i = c1.length-1;
		int j = c2.length-1;
		
		int sum = 0;
		while(i >= 0 && j >= 0) {
			sum = point + Character.getNumericValue(c1[i]) + Character.getNumericValue(c2[j]);
			if(sum >= 10) {
				point = 1;
				sb.append(sum-10);
			} else {
				point = 0;
				sb.append(sum);
			}
			i--;
			j--;
		}
		while(i >= 0) {
			sum = point + Character.getNumericValue(c1[i]);
			if(sum >= 10) {
				point = 1;
				sb.append(sum-10);
			} else {
				point = 0;
				sb.append(sum);
			}
			i--;
		}

		while(j >= 0) {
			sum = point + Character.getNumericValue(c2[j]);
			if(sum >= 10) {
				point = 1;
				sb.append(sum-10);
			} else {
				point = 0;
				sb.append(sum);
			}
			j--;
		}
		if(point != 0) sb.append(point); //attention the last point

		String result = sb.toString();
		char[] cs = result.toCharArray();
		int start = 0;
		int end = cs.length-1;
		while(start < end) {
			char tmp = cs[start];
			cs[start] = cs[end];
			cs[end] = tmp;
			start++;
			end--;
		}
		return new String(cs);
	}
	

	public StringBuffer multiply(String num1, char num2) {
		int point = 0;
		StringBuffer sb = new StringBuffer();
		for(int i = num1.length()-1; i >= 0; i --) {
			int result = multiply(num1.charAt(i), num2) + point;
			if(result >= 10) {
				point = result/10;
				sb.append(result%10);
			} else {
				point = 0;
				sb.append(result);
			}
		}
		if(point != 0) sb.append(point);
		return sb.reverse();
	}

	public int multiply(char num1, char num2) {
		return Character.getNumericValue(num1) * Character.getNumericValue(num2);
	}
}