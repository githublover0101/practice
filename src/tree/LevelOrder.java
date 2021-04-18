package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder {

	public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		if(root == null) return list;
		List<TreeNode> nodes = new LinkedList<TreeNode>();
		nodes.add(root);
		List<TreeNode> children = new LinkedList<TreeNode>();

		ArrayList<Integer> levelList = null;
		while(!nodes.isEmpty()) {
			levelList = new ArrayList<Integer>();
			for(TreeNode node: nodes) {
				levelList.add(node.val);
				if(node.left != null) {
					children.add(node.left);
				}
				if(node.right != null) {
					children.add(node.right);
				}
			}
			list.add(levelList);
			nodes.clear();
			nodes.addAll(children);
			children.clear();
		}
		return list;
	}

//	use linked list to visit the tree
	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> levelList = null;
		if(root == null) return list;
		Node head = new Node(root);
		Node first = head;
		Node last = head;
		Node p = null;
		while(first != null) {
			levelList = new ArrayList<Integer>();
			p = first;
			Node q = last;
			while(p != last.next) {
				TreeNode cur = p.val;
				levelList.add(cur.val);
				if(cur.left != null) {
					q.next = new Node(cur.left);
					q = q.next;
				}
				if(cur.right != null) {
					q.next = new Node(cur.right);
					q = q.next;
				}
				p = p.next;
			}
			list.add(levelList);
			first = last.next;
			last = q;
		}

		return list;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		LevelOrder test = new LevelOrder();
		ArrayList<ArrayList<Integer>> list = test.levelOrder(root);
		for(ArrayList<Integer> subList: list) {
			for(Integer e: subList) {
				System.out.print(e + ",");
			}
			System.out.println();
		}
	}
}

class Node {
	TreeNode val;
	Node next;

	public Node(TreeNode node) {
		this.val = node;
	}
}