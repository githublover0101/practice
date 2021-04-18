package array;

public class MediaForTwoSortedArrays {

	public double getMedian(int[] A, int[] B) {
		return getMedian(A, B, 0, A.length-1, 0, B.length-1);
	}
	
	public double getMedian(int[] A, int[] B, int startA, int endA, int startB, int endB) {
		if(startA <= endA && startB <= endB) {
			double midA = getMedianForArray(A, startA, endA);
			double midB = getMedianForArray(B, startB, endB);
			if(midA == midB) {
				return midA;
			} else if(midA > midB) {
				return getMedian(A, B, startA, (startA + endA)/2, (startB + endB)/2, endB);
			} else {
				return getMedian(A, B, (startA + endA)/2, endA, startB, (startB + endB)/2);
			}
		}
		return 0.0;
	}
	
	public double getMedianForArray(int[] A, int start, int end) {
		if((start + end)%2 == 0) {
			return A[(start+end)/2]*1.0;
		} else {
			int mid1 = A[(start+end)/2];
			int mid2 = A[(start+end)/2 + 1];
			return (mid1 + mid2)/2.0;
		}
	}
	
	public static void main(String[] args) {
		MediaForTwoSortedArrays test = new MediaForTwoSortedArrays();
		int[] A = {1,3,5,7,9};
		int[] B = {2,4,6,8,10};
		double result = test.getMedian(A, B);
		System.out.println(result);
	}

}
