public class TraversalTree {

	//
	// use queue
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> tree = new ArrayList<List<Integer>>();
		if(root == null) return tree;

		List<Integer> values = new ArrayList<Integer>();
		List<TreeNode> nodes = new ArrayList<Integer>();
		nodes.add(root);
		values.add(root.val);
		tree.add(values);

		List<TreeNode> children = new ArrayList<TreeNode>();
		while(!nodes.isEmpty()) {
			values = new ArrayList<Integer>();
			children.clear();
			//for each travel the level of nodes, to get next level of nodes
			for(TreeNode node: nodes) { 
				if(node.left != null) {
					children.add(node.left);
					values.add(node.left.val);
				}
				if(node.right != null) {
					children.add(node.right);
					values.add(node.right.val);
				}
			}
			nodes.clear();
			if(!children.isEmpty()) {
				nodes.addAll(children);
				tree.add(values);
			}
		}
		return tree;
	}


	//pre order traversal binary tree
	//use stack 
	//for each node, push its right node, then left node
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> tree = new ArrayList<Integer>();
		if(!root.isEmpty()) return tree;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.empty()) {
			TreeNode node = stack.pop();
			tree.add(node.val);
			if(node.right != null) {
				stack.push(node.right);
			}
			if(node.left != null) {
				stack.push(node.left);
			}
		}
		return tree;
	}

	//in order
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> tree = new ArrayList<Integer>();
		if(root == null) return tree;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		TreeNode p = root;
		while(!stack.isEmpty() || p != null) {
			if(p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode currNode = stack.pop();
				tree.add(currNode.val);
				p = currNode.right;
			}
		}
		return tree;
	}

	//
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> tree = new ArrayList<Integer>();
		if(root == null) return tree;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.) {

		}
		return tree;
	}
}