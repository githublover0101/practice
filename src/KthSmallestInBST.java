import java.util.HashMap;

import tree.TreeNode;


public class KthSmallestInBST {

	private static int count = 0;
    private static int finalVal = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return 0;
        finalVal = root.val;
        inorder(root, k);
        return finalVal;
    }
    
    private void inorder(TreeNode root, int k) {
        if(root == null) return;
        if(count == k) return;
        inorder(root.left, k);
        finalVal = root.val;
        count++;
        inorder(root.right, k);
    }
    
    public static void main(String[] args) {
    	KthSmallestInBST test = new KthSmallestInBST();
    	TreeNode root = new TreeNode(1);
    	root.right = new TreeNode(2);
    	System.out.println(test.kthSmallest(root, 2));
    	StringBuilder sb = new StringBuilder();
    	
    	StringBuffer sb1 = new StringBuffer();
    	HashMap<Integer, String> map = new HashMap<Integer, String>();
    	
    }
    
	
}
