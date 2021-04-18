
public class SurroundedRegions {

	public void solve(char[][] board) {
        int m = board.length;
        if(m == 0) return;
        int n = board[0].length;
        if(n == 0) return;
        
        for(int i = 0; i < m; i++) {
            if(board[i][0] == '0') {
                dfs(board, i, 0);
            }
            if(board[i][n-1] == '0') {
                dfs(board, i, n-1);   
            }
        }
        
        for(int j = 0; j < n; j++) {
            if(board[0][j] == '0') {
                dfs(board, 0, j);
            }
            if(board[m-1][j] == '0') {
                dfs(board, m-1, j);
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == '0') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = '0';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int row, int col) {
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length) return;
        if(board[row][col] != '0') return;
        board[row][col] = '#';
        dfs(board, row-1, col);
        dfs(board, row+1, col);
        dfs(board, row, col-1);
        dfs(board, row, col+1);
    }
    
    public static void main(String[] args) {
    	SurroundedRegions test = new SurroundedRegions();
    	char[][] board = {
    			{'X','X','X'},
    			{'X','0','X'},
    			{'X','X','X'}
    	};
    	test.solve(board);
    }
	
}
