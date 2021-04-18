package tree;

import java.util.List;

public class VerticalOrder {

	private int min;
	private int max;

	public void print(TreeNode root) {
		findMaxMin(root, 0);

		for(int lineNum = min; lineNum <= max; lineNum ++) {
			printVeritically(root, lineNum, 0);
			System.out.println();
		}
		
	}

	public void findMaxMin(TreeNode root, int hDistion) {
		if(root == null) return;

		if(min > hDistion) min = hDistion;
		if(max < hDistion) max = hDistion;

		findMaxMin(root.left, hDistion-1);
		findMaxMin(root.right, hDistion+1);
	}

	public void printVeritically(TreeNode root, int lineNum, int hDistion) {
		if(root == null) return;
		
		if(lineNum == hDistion) {
			System.out.print(root.val + "  ");
		}

		printVeritically(root.left, lineNum, hDistion-1);	
		printVeritically(root.right, lineNum, hDistion+1);		
	}

	public static void main(String[] args) {
		VerticalOrder test = new VerticalOrder();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		test.print(root);
	}
}
