package abc;

public class NumberOfIslands {

	static int counter1 = 0;
	static int counter2 = 0;
	
//	DFS, mark 1 to 0
	public int numberOfIslands1(int[][] grid) {
		int n = grid.length;
		if(n == 0) return 0;
		int m = grid[0].length;
		if(m == 0) return 0;

		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(grid[i][j] == 1) {
					counter++;
					dfs(grid, i, j);
				}
			}
		}
		return counter;
	}

	public void dfs(int[][] grid, int i, int j) {
		if(i < 0 || j < 0 || i == grid.length || j == grid[0].length) return;
		if(grid[i][j] == 1) {
			grid[i][j] = 0;
			counter1++;
			dfs(grid, i+1, j);
			dfs(grid, i-1, j);
			dfs(grid, i, j+1);
			dfs(grid, i, j-1);
		}
	}
	
	// use a visited[][] array to mark the element is visited or not, but it doesn't work
	public int numIslands(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        boolean[][] visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    ans++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return ans;
    }
    
    public void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if(i < 0 || j < 0 || i == grid.length || j == grid[0].length) return;
        
        if(visited[i][j] || grid[i][j] != 1) return;
        
        counter2++;
        
        grid[i][j] = 0;
        visited[i][j] = true;
        dfs(grid, i+1, j, visited);
        dfs(grid, i-1, j, visited);
        dfs(grid, i, j+1, visited);
        dfs(grid, i, j-1, visited);
    }
    
    public static void main(String[] args) {
    	NumberOfIslands test = new NumberOfIslands();
    	int[][] grid = {
    			{1, 1, 0, 0, 0},
    			{0, 1, 0, 0, 1},
    			{0, 0, 0, 1, 1},
    			{0, 0, 0, 0, 0},
    			{0, 0, 0, 0, 1}
    	};
    	int ans1 = test.numberOfIslands1(grid);
    	System.out.println(counter1 + " -- " + ans1);
    	
    	int[][] grid2 = {
    			{1, 1, 0, 0, 0},
    			{0, 1, 0, 0, 1},
    			{0, 0, 0, 1, 1},
    			{0, 0, 0, 0, 0},
    			{0, 0, 0, 0, 1}
    	};
    	int ans2 = test.numIslands(grid2);
    	System.out.println(counter2 + " -- " + ans2);
    }
}