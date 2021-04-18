package binary.search;

public class SearchRange {

	public int[] searchRange(int[] A, int target) {
		int index = binarySearch(A, target);
		if(index == -1)
			return new int[]{-1, -1};
		int start = index;
		int end = index;
		while(start >=0 && A[start] == target) start--;
		while(end < A.length && A[end] == target) end++;
		return new int[]{start+1, end-1};
	}
	
	public int binarySearch(int[] A, int target) {
		int start = 0;
		int end = A.length - 1;
		int mid = 0;
		while(start <= end) {
			mid = (start+end)/2;
			if(A[mid] == target) return mid;
			if(A[mid] > target)
				end = mid - 1;
			else
				start = mid + 1;
		}
		return -1;
	}
	
	public int searchLeftContainer(int[] A, int target) {
		int start = 0;
		int end = A.length-1;
		int mid = 0;
		while(start < end) {
			mid = (start + end)/2;
			if(A[mid] < target)
				start = mid + 1;
			else
				end = mid;
		}
		return start;
	}
	
	public int[] searchRange2(int[] A, int target) {
		int left = searchLeftContainer(A, target);
		if(left != target) {
			return new int[]{-1, -1};
		}
		int right = searchLeftContainer(A, target + 1);
		return new int[]{left, right-1};
	}
	
	public static void main(String[] args) {
		SearchRange test = new SearchRange();
		int[] A = new int[]{1,2,3,4,5,5,5,5,6,7,7,8,8,9};
		System.out.println(test.searchLeftContainer(A, 7));
		
	}
	
}
