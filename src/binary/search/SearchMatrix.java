package binary.search;

public class SearchMatrix {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if(target < matrix[0][0]) return false;
		if(target > matrix[matrix.length - 1][matrix[0].length - 1]) return false;
        int row = 0;
        int rowCount = matrix.length - 1;
        while(row < rowCount) {
        	if(matrix[row][0] <= target && target < matrix[row+1][0]) {
        		break;
        	} else {
        		row ++;
        	}
        }
        int left = 0;
        int right = matrix[0].length;
        int mid = 0;
        while(left <= right) {
        	mid = (left + right)/2;
        	if(matrix[row][mid] == target) return true;
        	if(matrix[row][mid] > target) 
        		right = mid - 1;
        	else
        		left = mid + 1;
        }
        return false;
    }

    // the matrix is like this, from left to right is ascending, and
    // from down to up is ascending, but the second row'start is not always bigger than the first row's end 
    // 1   5   7    9
    // 4   6   10   15
    // 8   11  12   19
    // 14  16  18   21
    // time complexity is O(x+y), x is the count of row, and y is the count of column
    public boolean searchMatrix2(int[][] matrix, int target) {
        int rowCount = matrix.length;
        if(rowCount == 0) return false;

        int colCount = matrix[0].length;
        if(colCount == 0) return false;

        //first find the target row, needs O(x)
        int targetRow = 0;
        while(targetRow < rowCount-1 && matrix[targetRow+1][0] <= target) {
            targetRow++;
        }
        //than find the target in the target row, needs O(y), so the total is O(x)+O(y)
        boolean result = false;
        for(int i = 0; i < colCount; i ++) {
            if(matrix[targetRow][i] == target) {
                result = true;
                break;
            }
        }
        return result;
    }

    //find the target row using binary search, then find the target use binary search again.
    //the time complexity is O(lgx + lgy)
    public boolean searchMatrix3(int[][] matrix, int target) {
        int rowCount = matrix.length;
        if(rowCount == 0) return false;

        int colCount = matrix[0].length;
        if(colCount == 0) return false;

        int targetRow = 0;
        int start = 0;
        int end = rowCount - 1;
        int mid = 0;
        while(start < end) {
            mid = start + (end - start)/2;
            if(matrix[mid][0] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        targetRow = start;
        start = 0;
        end = colCount - 1;
        while(start <= end) {
            mid = start + (end - start)/2;
            if(matrix[targetRow][mid] == target) return true;
            if(matrix[targetRow][mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }   
        return false;
    }
    
    public static void main(String[] args) {
        SearchMatrix test = new SearchMatrix();
        int[][] matrix = new int[][]{{1,5,7,9},
{4,6,10,15},
{8,11,12,19},
{14,16,18,21}};

        int target = 13;
        boolean result = test.searchMatrix3(matrix, target);
        System.out.println(result);
    }
}