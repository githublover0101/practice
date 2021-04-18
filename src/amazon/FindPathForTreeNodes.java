package amazon;

import java.util.Stack;

import tree.TreeNode;

public class FindPathForTreeNodes {

	//Given a tree find shortest path between any two given nodes.
	//A: Find nearest ancestor find heights from there, add them. 
	//He asked extra iterations for heights were required? 
	//Returned height while finding ancestor. Asked to code. 
	//Asked to explain the code, then dry run for any test case. A bug was found and corrected.

	public int findShortestPath(TreeNode root, int A, int B) {
		if(root == null) return 0;
		Stack<Integer> stackA = new Stack<Integer>();
		Stack<Integer> stackB = new Stack<Integer>();
		findPath(root, A, stackA);
		findPath(root, B, stackB);

		int lenA = stackA.size();
		int lenB = stackB.size();

		int commonLen = 0;
		while(!stackA.isEmpty() && !stackB.isEmpty()) {
			if(stackA.pop() != stackB.pop()) {
				break;
			} else {
				commonLen++;
			}
		}
		return lenA+lenB-2*commonLen;
	}

//	the stack path is from root ---> target node
	public boolean findPath(TreeNode root, int val, Stack<Integer> stack) {
		if(root == null) return false;
		if(root.val == val) {
			stack.push(root.val);
			return true;
		}
		if(root.left != null && findPath(root.left, val, stack)) {
			stack.push(root.val);
			return true;
		}
		if(root.right != null && findPath(root.right, val, stack)) {
			stack.push(root.val);
			return true;
		}
		return false;
	}
	
//	Binary Tree - Common Ancestor of two nodes
//	left & right
//	if left == right, return left
//	if left != right, return root
	public TreeNode commonAncestor(TreeNode root, int A, int B) {
		if(root == null) return null;
		if(root.val == A || root.val == B) return root;
		
		TreeNode left = commonAncestor(root.left, A, B);
		TreeNode right = commonAncestor(root.right, A, B);
		
		if(left == null) return right;
		if(right == null) return left;
		
		return left == right ? left : root;
	}
	
//	Binary Search Tree -- Common Ancestor of two nodes
	public TreeNode commonAncestorBST(TreeNode root, int A, int B) {
		if(root == null) return null;
		if(A < root.val && B < root.val) return commonAncestorBST(root.left, A, B);
		if(A > root.val && B > root.val) return commonAncestorBST(root.right, A, B);
		return root;
 	}
	
	public TreeNode buildBST() {
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.right = new TreeNode(10);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(6);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(11);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(3);
		root.left.right.left = new TreeNode(5);
		root.left.right.right = new TreeNode(7);
		return root;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		FindPathForTreeNodes test = new FindPathForTreeNodes();
		Stack<Integer> stack = new Stack<Integer>();
		test.findPath(root, 5, stack);
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + ",");
		}
		System.out.println();
		
		int A = 1;
		int B = 5;
		int result = test.findShortestPath(root, A, B);
		System.out.println(result);
		
		A = 3;
		B = 4;
		result = test.findShortestPath(root, A, B);
		System.out.println(result);
		
		A = 4;
		B = 5;
		result = test.findShortestPath(root, A, B);
		System.out.println(result);
		
		A = 4;
		B = 7;
		result = test.findShortestPath(root, A, B);
		System.out.println(result);
		
		TreeNode node = test.commonAncestor(root, 4, 3);
		System.out.println(node.val);
		
		TreeNode root2 = test.buildBST();
		A = 3;
		B = 6;
		TreeNode node2 = test.commonAncestorBST(root2, A, B);
		System.out.println(node2.val);
		
		A = 11;
		B = 9;
		node2 = test.commonAncestorBST(root2, A, B);
		System.out.println(node2.val);
	}

}
