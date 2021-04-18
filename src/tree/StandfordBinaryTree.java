package tree;

import java.util.ArrayList;
import java.util.List;

public class StandfordBinaryTree {

	// BST
	public boolean lookup(TreeNode root, int target) {
		if(root == null) return false;
		if(root.val == target) return true;
		return lookup(root.left, target) || lookup(root.right, target);
	}

	public boolean lookupinBST(TreeNode root, int target) {
		if(root == null) return false;
		if(root.val == target) return true;
		if(target < root.val) 
			return lookupinBST(root.left, target);
		else
			return lookupinBST(root.right, target);
	}

	// insert in BST
	public TreeNode insert(TreeNode root, int data) {
		// if the tree is empty, return a new, single node 
		if(root == null) return new TreeNode(data);
		if(data < root.val) 
			root.left = insert(root.left, data);
		else 
			root.right = insert(root.right, data);
		return root; //return the unchanged node
	}

	// min value in BST is the leftest node in the tree
	public int minValueinBST(TreeNode root) {
		if(root == null) return 0;
		TreeNode cur = root;
		while(cur.left != null) {
			cur = cur.left;
		}
		return cur.val;
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null) return sum == 0;
		sum = sum - root.val;
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}

	public void inorderPrint(TreeNode root) {
		if(root == null) {
			System.out.print("#, ");
			return;
		}
		System.out.print(root.val + ", ");
		inorderPrint(root.left);
		inorderPrint(root.right);
	}

	public void printPaths(TreeNode root, List<Integer> list, int pathLen) {
		if(root == null) return;
		list.add(pathLen, root.val); //
		pathLen++;
		if(root.left == null && root.right == null) {
			for(int i = 0; i < pathLen; i ++) {
				System.out.print(list.get(i) + ", ");
			}
			System.out.println();
		}
		
		printPaths(root.left, list, pathLen);
		printPaths(root.right, list, pathLen);
	}
	
	private void printPaths(TreeNode node, int[] path, int pathLen) { 
		if (node==null) return;

		// append this node to the path array 
		path[pathLen] = node.val; 
		pathLen++;

		// it's a leaf, so print the path that led to here 
		if (node.left==null && node.right==null) { 
			printArray(path, pathLen); 
		} else { 
			// otherwise try both subtrees 
			printPaths(node.left, path, pathLen); 
			printPaths(node.right, path, pathLen); 
		} 
	}

	private void printArray(int[] ints, int len) { 
		int i; 
		for (i=0; i<len; i++) { 
			System.out.print(ints[i] + " "); 
		} 
		System.out.println(); 
	} 

	public void mirror(TreeNode root) {
		if(root == null) return;
		mirror(root.left);
		mirror(root.right);

		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
	}

	public void doubleTree(TreeNode root) {
		if(root == null) return;
		doubleTree(root.left);
		doubleTree(root.right);
		
		// duplicate this node to its left 
		TreeNode oldLeft = root.left;
		root.left = new TreeNode(root.val);
		root.left.left = oldLeft;
	}
 
	public void sameTree(TreeNode root1, TreeNode root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null) return false;
		if(root2 == null) return false;
		return root1.val == root2.val && sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right);
	}

	public static void main(String[] args) {
		StandfordBinaryTree test = new StandfordBinaryTree();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
//		TreeNode node = test.insert(root, 4);
//		test.inorderPrint(node);
//
		List<Integer> list = new ArrayList<Integer>();
//		int[] path = new int[1000]; 
//		test.printPaths(root, path, 0);
		test.printPaths(root, list, 0);
		test.inorderPrint(root);
		System.out.println();
		test.mirror(root);
		test.inorderPrint(root);
		System.out.println();
		test.doubleTree(root);
		test.inorderPrint(root);
	}
}