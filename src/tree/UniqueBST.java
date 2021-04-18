public class UniqueBST {

	
   	public int numTrees(int n) {
        if(n <= 1) return 1;
        int sum = 0;
        int left = 0;
        int right = 0;
        for(int i = 1; i <= n; i ++) {
            left = numTrees(i-1); // left tree's count
            right = numTrees(n-i); // right tree's count
            sum += left*right;
        }
        return sum;
    }

    public int numTrees2(int n) {
    	int[] count = new int[n+1];
    	count[0] = 1;
    	count[1] = 1;
    	
    	for(int i = 2; i <= n; i ++) {
    		for(int j = 0; j < i; j ++) {
    			count[i] += count[j]*count[i-j-1]
    		}
    	}
    	return count[n];
    }

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(0, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
    	List<TreeNode> tree = new ArrayList<TreeNode>();
    	if(start > end) {
    		return tree;
    	}
    	for(int i = start; i <= end; i ++) {
    		List<TreeNode> leftNodes = generateTrees(start, i - 1);
    		List<TreeNode> rightNodes = generateTrees(i + 1, end);
    		for(TreeNode left: leftNodes) {
    			for(TreeNode right: rightNodes) {
    				TreeNode node = new TreeNode(i);
    				node.left = left;
    				node.right = right;
    				tree.add(node);
    			}
    		}
    	}
    	return tree;
    }
}