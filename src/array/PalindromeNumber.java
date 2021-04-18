package array;

public class PalindromeNumber {

	public boolean isPalindrome(int x) {
		if(x < 0) return false;
        long count = 1;
        while(count < x) {
        	count = count*10;
        }
        int size = (int)(count/10);
        
        int left = 0;
        int right = 0;
        while(size != 0 && x >= 10) {
            left = x/size;
            right = x%10;
            if(left != right) return false; 
            x = x%size;
            x = x/10;
            size = size/100;
        }
        return true;
    }
	
	public static void main(String[] args) {
		PalindromeNumber test = new PalindromeNumber();
		int x = 1000021;
		System.out.println(test.isPalindrome(x));
	}
	
}
