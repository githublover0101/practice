public class CommonAncestor {
	public int findLastestCommon(TreeNode root, TreeNode p, TreeNode q) {
		// use two stacks to save the path root -> node
		// output stack s1 & s2, the first common node
		// return the first common node
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();

		findPath(root, p.val, s1);
		findPath(root, q.val, s2);

		while(!s1.isEmpty() && !s2.isEmpty()) {
			int k1 = s1.pop();
			int k2 = s2.pop();
			if(k1 == k2) {
				return k1;
			}
		}
		return -1;
	}

	public boolean findPath(TreeNode root, int val, Stack<Integer> s) {
		if(root == null) return false;
		if(root.val == val) return true;
		s.push(root.val);

		if((root.left && findPath(root.left, val, s)) || (root.right && findPath(root.right, val, s)))
			return true;
		return false;
	}

	public TreeNode findLCA(TreeNode root, int k1, int k2) {
		if(root == null) return root;
		if(root.val == k1 || root.val == k2) return root;
		
		TreeNode left = findLCA(root.left, k1, k2);
		TreeNode right = findLCA(root.right, k1, k2);
		
		if(left != null && right != null) return root;
		return left == null ? right : left;
	}

}
