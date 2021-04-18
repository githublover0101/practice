package abc;

import java.util.Stack;

import tree.TreeNode;

public class LowestCommonAncestor {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if(root == null || A == null || B == null) return null;
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        findPath(root, A, stack1);
        findPath(root, B, stack2);
        
        TreeNode pre = root;
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode k1 = stack1.pop();
            TreeNode k2 = stack2.pop();
            if(k1 != k2) {
                return pre;
            } else {
                pre = k1;
            }
        }
        return pre; //the last one is the root
    }
    
    public boolean findPath(TreeNode root, TreeNode A, Stack<TreeNode> stack) {
        if(root.val == A.val) {
            stack.add(root);
            return true;
        }
        if(root.left != null && findPath(root.left, A, stack)) {
            stack.add(root);
            return true;
        }
        if(root.right != null && findPath(root.right, A, stack)) {
            stack.add(root);
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
    	LowestCommonAncestor test = new LowestCommonAncestor();
    	TreeNode root = new TreeNode(1);
    	TreeNode p = root;
    	for(int i = 2; i <= 6; i ++) {
    		p.right = new TreeNode(i);
    		p = p.right; 
    	}
    	TreeNode A = new TreeNode(6);
    	TreeNode B = new TreeNode(3);
    	TreeNode c = test.lowestCommonAncestor(root, A, B);
    	System.out.println(c);
    	StringBuffer sb = new StringBuffer();
    }
	
}
