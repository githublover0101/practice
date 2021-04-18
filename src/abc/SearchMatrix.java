package abc;

public class SearchMatrix {

	public boolean searchMatrix(int[][] matrix, int target) {
        // binary search to find the target row, log(n)
        int n = matrix.length;
        if(n == 0) return false;
        int m = matrix[0].length;
        if(m == 0) return false;
        
        int mid = 0;
        int low = 0;
        int high = n-1;
        while(low <= high) {
            mid = low + (high - low)/2;
            if((mid == n-1) || (matrix[mid][0] <= target && target < matrix[mid+1][0])) 
                break;
            if(matrix[mid+1][0] < target)
                low = mid + 1;
            else 
                high = mid - 1;
        }
        int targetRow = mid;
        // in target row, use binary search to find the target num, log(m)
        int left = 0;
        int right = m-1;
        mid = 0;
        while(left <= right) {
            mid = left + (right - left)/2;
            if(matrix[targetRow][mid] == target) return true;
            if(matrix[targetRow][mid] > target)
                right = mid - 1;
            else 
                left = mid + 1;
        }
        return false;
    }
	
	public static void main(String[] args) {
		SearchMatrix test = new SearchMatrix();
		int target = 7;
		int[][] matrix = {
					{1,3,5,7},
					{10,11,16,20},
					{23,30,34,50}};
		boolean result = test.searchMatrix(matrix, target);
		System.out.println(result);
	}
}
