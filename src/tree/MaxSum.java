package tree;

public class MaxSum {

	private int maxSum;
    
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        findMaxToParent(root);
        return maxSum;
    }
    
    // from bottom to the top, find a node's to its parent the biggest sum
    // if the sum is <= 0, should return 0;
    public int findMaxToParent(TreeNode root) {
        if(root == null) return 0;
        int left = findMaxToParent(root.left);
        int right = findMaxToParent(root.right);
        
        maxSum = Math.max(maxSum, root.val + left + right);
        
        int maxToParent = Math.max(left, right) + root.val;
        return maxToParent > 0 ? maxToParent : 0;
    }


    public int maxPath(TreeNode root) {
    	maxSum = Integer.MIN_VALUE;
    	findPathToParent(root);
    	return maxSum;
    }

//    
    public int findPathToParent(TreeNode root) {
    	if(root == null) return 0;
    	int left = findPathToParent(root.left);
    	int right = findPathToParent(root.right);

    	maxSum = Math.max(maxSum, 2 + left + right);
    	int maxToParent = Math.max(left, right) + 1;
    	return maxToParent > 0 ? maxToParent : 0;
    }

    public static void main(String[] args) {
    	MaxSum test = new MaxSum();
    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	root.left.left = new TreeNode(3);
    	root.left.right = new TreeNode(4);
    	root.left.left.left = new TreeNode(5);
    	root.left.right.left = new TreeNode(6);
    	root.left.right.left.left = new TreeNode(7);
    	int result = test.maxPath(root);
    	System.out.println(result);
    } 
}