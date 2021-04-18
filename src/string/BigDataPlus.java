package string;

public class BigDataPlus {

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
			sum = point + Character.getNumericValue(c1[j]);
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
	
	public static void main(String[] args) {
		char c = '1';
		System.out.println(Character.getNumericValue(c));
		BigDataPlus test = new BigDataPlus();
		String s1 = "1234567891111111000000001111";
		String s2 = "1111111111111111111111111111";
		String result = test.addTwoNumbers(s1, s2);
		System.out.println(result);
	}
}