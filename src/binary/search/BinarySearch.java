package binary.search;

public class BinarySearch {

	public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while(left < right) {
            mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) 
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
    	
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m-1;
        int targetRow = 0;
        while(start < end) {
            targetRow = start + (end - start)/2;
            
        }
    }
}