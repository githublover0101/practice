package tree;

public class ValidTree{

    // level order & check each node
	public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        
    }

    public static void main(String[] args) {
    	ValidTree test = new ValidTree();
    	TreeNode root = new TreeNode(-2147483648);
    	root.left = new TreeNode(-2147483648);
    	boolean flag = test.isValidBST(root);
    	System.out.println(Integer.MIN_VALUE+1);
    }
}