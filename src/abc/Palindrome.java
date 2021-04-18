package abc;


public class Palindrome {

	public boolean isPalindrome(int x) {
        long count = 1;
        while(count*10 < x) {
            count = count*10;
        }
        int high = 0;
        int low = 0;
        while(count != 0) {
            high = x/(int)count;
            low = x%10;
            if(high != low) return false;
            x = x-(int)count*high;
            x = x/10;
            count = count/100;
        }
        return true;
    }
	
	public static void main(String[] args) {
		Palindrome test = new Palindrome();
		int x = 1874994781;
		System.out.println(test.isPalindrome(x));
	}
}
