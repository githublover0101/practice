package tree;

// the longest path between two nodes A & B in binary tree is called diameter
// The diameter of a tree T is the largest of the following quantities:

// * the diameter of T’s left subtree
// * the diameter of T’s right subtree
// * the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)

public class LongestPath {

	public int height(TreeNode root) {
		if(root == null) return 0;
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	// there exists two times recursion
	// the time complexity is O(n^2)
	public int diameter(TreeNode root) {
		if(root == null) return 0;

		int lHeight = height(root.left);
		int rHeight = height(root.right);

		int lDiameter = diameter(root.left);
		int rDiameter = diameter(root.right);
		return Math.max(Math.max(lDiameter, rDiameter), lHeight + rHeight + 1);
	}

	// use one recursion
	// get height the same time as get diameter
	// time complexity is O(n)	
	public int computeDiameter(TreeNode root, int[] height) {
		int[] lHeight = new int[1];
		int[] rHeight = new int[1];

		if(root == null) {
			height[0] = 0;
			return 0;
		}

		int lDiameter = computeDiameter(root.left, lHeight);
		int rDiameter = computeDiameter(root.right, rHeight);
		height[0] = Math.max(lHeight[0], rHeight[0]) + 1;
		return Math.max(lHeight[0] + rHeight[0] + 1, Math.max(lDiameter, rDiameter));
	}

	public static void main(String[] args) {
		LongestPath test = new LongestPath();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.left.left.left = new TreeNode(6);
		root.left.left.right = new TreeNode(7);

		int result = test.diameter(root);
		System.out.println(result);
		
		int[] height = new int[1];
		int shortest = test.computeDiameter(root, height);
		System.out.println(shortest);
	}
}