package string;

public class ReverseWord {

	// first reverse the whole string
	// then reverse the words
	// finally delete the empty string
	public String reverseWords(String s) {
		if(s == null || "".equals(s)) return s;
		char[] chars = s.toCharArray();
		int len = chars.length;
		len = emptyFront(chars, len);
		reverse(chars, 0, len-1);
		len = emptyFront(chars, len);
        
        if(len == 0) return "";
        
		int end = 0;
		for(int start = 0; start < len; ) {
			while(start < len && chars[start] == ' ') start ++;
			end = start + 1;
			while(end < len && chars[end] != ' ') end++;
			//start .... end-1
			end--;
			reverse(chars, start, end);
			start = end+1;
		}

		int newLen = 1;
		for(int i = 1; i < len; ) {
			if(chars[i] == chars[i-1] && chars[i-1] == ' ') {
				i++;
			} else {
				chars[newLen++] = chars[i++];
			}
		}
		return new String(chars, 0, newLen);
    }

    public int emptyFront(char[] chars, int len) {
    	int newLen = 0;
    	int start = 0;
    	while(start < len && chars[start] == ' ') start ++;
    	if(start < len) {
    		for(int i = start; i < len; i ++) {
    			chars[newLen++] = chars[i];
    		}
    	}
    	return newLen; 
    }

    public void reverseWords(char[] chars) {
        reverse(chars, 0, chars.length-1);
        for(int i = 0; i < chars.length; ) {
            int j = i;
            while(j < chars.length && chars[j] != ' ') j++;
            reverse(chars, i, j-1);
            i = j+1; 
        }
    }

    public void reverseWords(char[] chars) {
        reverse(chars, 0, chars.length-1);
        for(int i = 0, j = 0; j < chars.length; j++) {
            if(j == chars.length-1 || chars[j] == ' ') {
                reverse(chars, i, j);
                i = j+1;
            }
        }
    }

    public void reverse(char[] chars, int start, int end) {
    	int i = 0;
    	int j = end;
    	char tmp = ' ';
    	while(i < j) {
    		tmp = chars[i];
    		chars[i] = chars[j];
    		chars[j] = tmp;
    		i++;
    		j--;
    	}
    }

    public static void main(String[] args) {
    	ReverseWord test = new ReverseWord();
    	String s = "   a    b";
    	String result = test.reverseWords(s);
    	System.out.println(result);
    }
}