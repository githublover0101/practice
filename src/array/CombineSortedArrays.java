package array;

public class CombineSortedArrays {

//	Combine two sorted arrays as one maintain sorted with smart code;
	public int[] combine(int[] A, int[] B) {
		int len1 = A.length;
		int len2 = B.length;
		
		int[] num = new int[len1 + len2];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < len1 && j < len2) {
			if(A[i] < B[j]) {
				num[k++] = A[i++];
			} else {
				num[k++] = B[j++];
			}
		}
		
		while(i < len1) {
			num[k++] = A[i++];
		}
		
		while(j < len2) {
			num[k++] = B[j++];
		}
		
		return num;
	}
	
	public void merge(int[] A, int start, int mid, int end) {
		
	}
	
	public static void main(String[] args) {
		CombineSortedArrays test = new CombineSortedArrays();
		int[] A = new int[]{1,3,5,7,9};
		int[] B = new int[]{2,4,6,8,10,11};
		int[] num = test.combine(A, B);
		for(int e: num) {
			System.out.print(e + ", ");
		}
		
	}
	
}
