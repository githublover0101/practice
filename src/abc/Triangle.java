public class Triangle {

	public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0; 
        int row = triangle.size();
        int column = triangle.get(row-1).size();
     	int[][] sum = new int[row][column]; 
     	
     	for(int j = 0 ; j < column; j ++) {
     		sum[row-1][j] = triangle.get(row-1).get(j);
     	}  
     	for(int i = row-2; i >= 0; i --) {
     		int subColumn = triangle.get(i).size();
     		for(int j = 0; j < subColumn; j ++) {
     			sum[i][j] = triangle.get(i).get(j) + Math.min(sum[i+1][j], sum[i+1][j+1]);
     		}
     	}
     	return sum[0][0];
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
    	if(triangle == null || triangle.size() == 0) return 0;
    	int row = triangle.size();
    	for(int i = row-2; i >= 0; i --) {
    		List<Integer> currentRow = triangle.get(i);
    		int subColumn = currentRow.size();
    		for(int j = 0; j < subColumn; j ++) {
    			int currentMin = triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
    			currentRow.set(j, currentMin);
    		}
    	} 
    	return triangle.get(0).get(0);
    }
}