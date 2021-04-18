package array;

public class RemoveRepeat {

	public static void removeRepeat(int[] A) {
		int num = 1;
		
		for(int i = 0; i < A.length; i ++) {
			System.out.print(A[i] + ", ");
		}
		
		for(int i = 1; i < A.length; i ++) {
			if(A[i] != A[i-1]) {
				A[num++] = A[i];
			}
		}
		
		System.out.println();
		for(int i = 0; i < num; i ++) {
			System.out.print(A[i] + ", ");
		}
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{1,2,2,11,34,2,6,4,4,4,4,6,7};
		removeRepeat(A);
	}
	
}
