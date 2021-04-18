package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PrintTreeTopDown {

	public void printTreeTopDownColumnWise(TreeNode root) {
		if(root == null) return;
		// key is the column value
		// value is the nodes with the column
		// TreeMap is ordered by key
		Map<Integer, List<TreeNode>> columnMap = new TreeMap<Integer, List<TreeNode>>();
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		// key is the node
		// value is the column value
		Map<TreeNode, Integer> nodeMap = new HashMap<TreeNode, Integer>();
		nodeMap.put(root, 0);
		queue.add(root);
		List<TreeNode> levelList = new ArrayList<TreeNode>();
		levelList.add(root);
		columnMap.put(0, levelList);

		while(!queue.isEmpty()) {
			TreeNode cur = queue.pop();
			int columnVal = nodeMap.get(cur);
			if(cur.left != null) {
				nodeMap.put(cur.left, columnVal-1);
				queue.add(cur.left);

				if(columnMap.containsKey(columnVal-1)) {
					columnMap.get(columnVal-1).add(cur.left);
				} else {
					levelList = new ArrayList<TreeNode>();
					levelList.add(cur.left);
					columnMap.put(columnVal-1, levelList);
				}
			}
			if(cur.right != null) {
				nodeMap.put(cur.right, columnVal+1);
				queue.add(cur.right);

				if(columnMap.containsKey(columnVal+1)) {
					columnMap.get(columnVal+1).add(cur.right);
				} else {
					levelList = new ArrayList<TreeNode>();
					levelList.add(cur.right);
					columnMap.put(columnVal+1, levelList);
				}
			}
		}

		for(Map.Entry<Integer, List<TreeNode>> entry: columnMap.entrySet()) {
			for(TreeNode node: entry.getValue()) {
				System.out.print(node.val + ", ");
			}
			System.out.println();
		}
	}

	// recursively traverse the tree, and add it to the TreeMap
	public void recurseTraverse(TreeNode root, Map<Integer, List<TreeNode>> columnMap, int column) {
		if(root == null) return;
		if(columnMap.containsKey(column)) {
			columnMap.get(column).add(root);
		} else {
			List<TreeNode> subList = new ArrayList<TreeNode>();
			subList.add(root);
			columnMap.put(column, subList);
		}
		recurseTraverse(root.left, columnMap, column-1);
		recurseTraverse(root.right, columnMap, column+1);
	}

	public void recurseTraverse(TreeNode root) {
		Map<Integer, List<TreeNode>> columnMap = new TreeMap<Integer, List<TreeNode>>();
		recurseTraverse(root, columnMap, 0);
		for(Map.Entry<Integer, List<TreeNode>> entry: columnMap.entrySet()) {
			for(TreeNode node: entry.getValue()) {
				System.out.print(node.val + ", ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		PrintTreeTopDown test = new PrintTreeTopDown();
		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(6);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(7);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(5);
		root.right = new TreeNode(10);
		root.right.left = new TreeNode(9);
		root.right.right = new TreeNode(12);
		test.printTreeTopDownColumnWise(root);

		System.out.println("recursively.....");
		test.recurseTraverse(root);

	}
}