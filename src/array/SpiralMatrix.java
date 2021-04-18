package array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public int[][] generateMatrix(int n) {
        int[][] list = new int[n][n];
        int rowStart = 0;
        int rowEnd = n-1;
        int colStart = 0;
        int colEnd = n-1;
        
        int num = 1;
        while(rowStart <= rowEnd && colStart <= colEnd) {
            //to right
            for(int j = colStart; j <= colEnd; j ++) {
                list[rowStart][j] = num++;
            }

            //to down
            for(int i = rowStart+1; i <= rowEnd; i ++) {
                list[i][colEnd] = num++;
            }

            //to left
            for(int j = colEnd-1; j >= colStart; j --) {
                list[rowEnd][j] = num++;
            }

            //to up
            for(int i = rowEnd-1; i > rowStart; i --) {
                list[i][colStart] = num++;
            }

            rowStart ++;
            rowEnd --;
            colStart ++;
            colEnd --;
        }
        return list;
    }

	public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        int rows = matrix.length;
        if(rows == 0) return list;
        
        int columns = matrix[0].length;
        if(columns == 0) return list;
        
        int rowStart = 0;
        int colStart = 0;
        int rowEnd = rows - 1;
        int colEnd = columns - 1;
        
        while(rowStart <= rowEnd && colStart <= colEnd) {
            //to right
            for(int j = colStart; j <= colEnd; j ++) {
                list.add(matrix[rowStart][j]);
            }
            
            //to down
            for(int i = rowStart+1; i <= rowEnd; i ++) {
                list.add(matrix[i][colEnd]);
            }
            
            //to left
            if(rowEnd > rowStart) {
            	for(int j = colEnd-1; j >= colStart; j --) {
                    list.add(matrix[rowEnd][j]);
                }
            }
            
            //to up
            if(colEnd > colStart) {
            	for(int i = rowEnd-1; i > rowStart ; i --) {
                    list.add(matrix[i][colStart]);
                }
            }
            
            if(rowStart <= rowEnd) {
                rowStart ++;
                rowEnd --;
            }
            if(colStart <= colEnd) {
                colStart ++;
                colEnd --;
            }
        }
        
        return list;
    }
	
}
