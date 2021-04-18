package appannie;

public class Task1 {

	static int solution(int[] A, int X) {
        int N = A.length;
        if (N == 0) {
            return -1;
        }
        int l = 0;
        int r = N - 1;
        while (l < r) {
//            int m = (l + r) / 2;
        	int m = (l + r) / 2 + 1;
            if (A[m] > X) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        if (A[l] == X) {
            return l;
        }
        return -1;
    }
	
	public static void main(String[] args) {
		Task1 test = new Task1();
		int[] A = {0,1,1,2,3,4,5,6,7,7};
		int X = 8;
		System.out.println(test.solution(A, X));
	}
	
}
