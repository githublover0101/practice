package binary.search;

public class SearchInsertPosition {

	public int searchInsert(int[] A, int target) {
		int left = 0;
		int right = A.length-1;
		int mid = 0;
		while(left < right) {
			mid = (left + right)/2;
			if(target == A[mid]) return mid;
			if(target > A[mid])
				left = mid + 1;
			else
				right = mid - 1;
		}
		mid = (left + right)/2;
		if(A[mid] < target)
			return mid + 1;
		return mid;
	} 
	
}
