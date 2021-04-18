package amazon;

public class CompressString {

//	Given a string (0-9a-z), use run-length encoding to compress it. 
//	For non-consecutive letter, don't put 1 behind it in the compressed string.
//	aaabxxcccccchinaaaa --> a3bx2c6hina4
	
//	amazon interview question: Compress a given string "aabbbccc" to "a2b3c3" 
//	constraint: inplace compression, no extra space to be used 
//	assumption : output size will not exceed input size.. ex input:"abb" -> "a1b2" buffer overflow.. such inputs will not be given.

	public String compress(String s) {
		StringBuffer sb = new StringBuffer();
		int len = s.length();
		char pre = ' ';
		int count = 0;
		for(int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if(pre == c) {
				count++;
			} else {
				if(count > 1) {
					sb.append(count);
				}
				sb.append(c);
				pre = c;
				count = 1;
			}
		}
		if(count > 1) sb.append(count); //remember the last one
		return sb.toString();
	}
	
	public String compress2(String s) {
		char[] cs = s.toCharArray();
		char pre = ' ';
		int k = 0;
		int count = 0;
		for(int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if(pre == c) {
				count++;
			} else {
				if(count > 1) {
					cs[k++] = (char)count;
				}
				cs[k++] = c;
				pre = c;
				count = 1;
			}
		}
		if(count > 1) cs[k++] = (char)count;
		return new String(cs);
	}
	
	public static void main(String[] args) {
		CompressString test = new CompressString();
		String s = "aaabxxcccccchinaaaa";
		String result = test.compress2(s);
		System.out.println(result);
	}
	
}
