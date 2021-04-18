package binary.search;

public class TwoSortMedian {

	public double findMedianSortedArrays(int A[], int B[]) {
        return findMedian(A, B, 0, A.length-1, 0, B.length-1);
    }
    
    public double findMedian(int[] A, int[] B, int aStart, int aEnd, int bStart, int bEnd) {
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;
        if(aLen == 0) return findMedian(B, bStart, bEnd);
        if(bLen == 0) return findMedian(A, aStart, aEnd);
        if(aLen == 1 && bLen == 1) return (A[aStart]+B[bStart])/2.0;
        
        double median1 = findMedian(A, aStart, aEnd);
        double median2 = findMedian(B, bStart, bEnd);
        
        if(median1 == median2) return median1;
        if(median1 < median2) {
            return findMedian(A, B, aLen/2, aEnd, bStart, bLen/2);
        } else {
            return findMedian(A, B, aStart, aLen/2, bLen/2, bEnd);
        }
    }
    
    public double findMedian(int[] A, int start, int end) {
        if(start == end) return A[start];
        if((start+end)%2 != 0) 
            return (A[(start+end)/2] + A[(start+end)/2+1])/2;
        else
            return A[(start+end)/2];
    }
    
    public static void main(String[] args) {
    	TwoSortMedian test = new TwoSortMedian();
    	int[] A = new int[]{1};
    	int[] B = new int[]{1, 2};
    	double result = test.findMedianSortedArrays(A, B);
    	System.out.println(result);
    }
	
}
