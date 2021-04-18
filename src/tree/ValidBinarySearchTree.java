ySearchpackage tree;

public class ValidBinarySearchTree {

    
	public boolean isValidBST(TreeNode root) {
        return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean valid(TreeNode root, int min, int max) {
        if(root == null) return true;
        return root.val > min && root.val < max
            && valid(root.left, min, root.val) 
            && valid(root.right, root.val, max);
    }

    public static void main(String[] args) {
    	ValidTree test = new ValidTree();
    	TreeNode root = new TreeNode(-2147483648);
    	root.left = new TreeNode(-2147483648);
    	boolean flag = test.isValidBST(root);
    	System.out.println(Integer.MIN_VALUE+1);
    }

    public List<String> validPranthesis(int n) {
        List<String> list = new ArrayList<String>();
        createPranthesisString(n, n, list, "");
        return list;
    }

    public void createPranthesisString(int left, int right, List<String> list, String result) {
        if(left == 0 && right == 0) {
            list.add(result);
            return;
        }
        if(left > 0) {
            createPranthesisString(left-1, right, list, result+"(");
        }
        if(left < right) {
            createPranthesisString(left, right-1, list, result+")");
        }
    }

}