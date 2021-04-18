package string;

public class CompareStrings {

	// A contains B
	public boolean compareStrings1(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        if(lenA == lenB && lenA == 0) return true; 
        int i = 0;
        for(; i + lenB < lenA; i ++) {
            int j = 0;
            int k = i;
            while(k < lenA && j < lenB && A.charAt(k) == B.charAt(j)) {
                k++;
                j++;
            }
            if(j == lenB) return true;
        }
        return false;
    }

    // A contains all characters in B
    public boolean compareStrings(String A, String B) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int lenA = A.length();
        int lenB = B.length();
        for(int i = 0; i < lenA; i++) {
        	char c = A.charAt(i);
        	if(map.containsKey()) {
        		map.put(c, map.get(c) + 1);
        	} else {	
        		map.put(c, 1);
        	}
        }
        for(int i = 0; i < lenB; i++) {
        	char c = B.charAt(i);
        	if(map.containsKey(c) && map.get(c) > 0) {
        		map.put(c, map.get(c) - 1);
        	} else {
        		return false;
        	}
        }
        return true;
    }

    public static void main(String[] args) {
    	CompareStrings test = new CompareStrings();
    	String A = "ABCDEFG";
    	String B = "ACC";
    	String result = test.compareStrings(A, B);
    	System.out.println();
    }
}