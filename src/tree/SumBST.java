package tree;

import java.util.Stack;

public class SumBST {

	// whether there exist two nodes x & y, their values' sum is a given value
	// x.val + y.val == sum
	// use two pointers: start & end
	// start init to the leftest node in the tree
	// end init to the rightest node in the tree
	// if start.val + end.val == sum, return tree;
	// if start.val + end.val > sum, end should point to the previous one
	// if start.val + end.val < sum, start should point to the next one
	// end pointer's previous one:
	// 	1. if it has left child, find the left child's the rightest node
	//  2. if no left child, find its parent
	// start pointer's next one:
	// 	1. if it has right child, find the right child's the leftest node
	//  2. if no right child, find its parent 
    // use two stacks to save the next nodes & pre nodes
	// time complexity is O(n), and space complexity is O(lgn). n is the count of nodes, lgn is the height of the tree
	public boolean hasTwoNodesSum(TreeNode root, int sum) {
		Stack<TreeNode> nextNodes = new Stack<TreeNode>();
		Stack<TreeNode> preNodes = new Stack<TreeNode>();
		buildNextNodes(root, nextNodes);
		buildPreNodes(root, preNodes);

		TreeNode pNext = getNext(nextNodes);
		TreeNode pPre = getPre(preNodes);
		while(pNext != null && pPre != null && pNext != pPre) {
			if(pNext.val + pPre.val == sum) {
				return true;
			} else if(pNext.val + pPre.val > sum) {
				pPre = getPre(preNodes);
				System.out.println("pre: " + pPre.val);
			} else {
				pNext = getNext(nextNodes);
				System.out.println("next: " + pNext.val);
			}
		}
		return false;
	}

	public void buildPreNodes(TreeNode root, Stack<TreeNode> preNodes) {
		TreeNode p = root;
		while(p != null) {
			preNodes.push(p);
			p = p.right;
		}
	}

	public void buildNextNodes(TreeNode root, Stack<TreeNode> nextNodes) {
		TreeNode p = root;
		while(p != null) {
			nextNodes.push(p);
			p = p.left;
		}
	}

	public TreeNode getPre(Stack<TreeNode> preNodes) {
		TreeNode pre = null;
		if(!preNodes.isEmpty()) {
			pre = preNodes.pop();
			TreeNode left = pre.left;
			while(left != null) {
				preNodes.push(left);
				left = left.right;
			}
		}
		return pre;
	}

	public TreeNode getNext(Stack<TreeNode> nextNodes) {
		TreeNode next = null;
		if(!nextNodes.isEmpty()) {
			next = nextNodes.pop();
			TreeNode right = next.right;
			while(right != null) {
				nextNodes.push(right);
				right = right.left;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		SumBST test = new SumBST();
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(4);
		root.right = new TreeNode(10);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(11);

		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(3);

		root.left.right = new TreeNode(6);
		root.left.right.left = new TreeNode(5);
		root.left.right.right = new TreeNode(7);

		System.out.println(test.hasTwoNodesSum(root, 15));
		System.out.println(test.hasTwoNodesSum(root, 7));
		System.out.println(test.hasTwoNodesSum(root, 21));
	}
}