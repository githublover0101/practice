package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindMostFrequenceInBST {

	//space complexity is O(n)
	public int findMostFrenquence(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		inorder(root, list);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int num: list) {
			if(map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			} else {
				map.put(num, 1);
			}
		}
		int num = 0;
		int maxCount = 0;
		for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
			if(maxCount < entry.getValue()) {
				maxCount = entry.getValue();
				num = entry.getKey();
			}
		}
		return num;
	}

	public void inorder(TreeNode root, List<Integer> list) {
		if(root == null) return;
		inorder(root.left, list);
		list.add(root.val);
		inorder(root.right, list); 
	}

	int mostFrequence = 0;
	int most = 0;

	public int getMost(TreeNode root) {
		if(root == null) return most;
		int current = root.val;
		int currentFrequence = 0;
		mostFrequently(root, current, currentFrequence);
		return most;
	}

	public void mostFrequently(TreeNode node, int current, int currentFrequence) {
		if(node == null) return;
		mostFrequently(node.left, current, currentFrequence);
		if(node.val == current) {
			currentFrequence++;
		} else {
			current = node.val;
			currentFrequence = 1;
		}
		if(currentFrequence > mostFrequence) {
			mostFrequence = currentFrequence;
			most = current;
		}
		mostFrequently(node.right, current, currentFrequence);
	}

	public static void main(String[] args) {
		FindMostFrequenceInBST test = new FindMostFrequenceInBST();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(5);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(5);
		root.right.right = new TreeNode(8);
		root.right.right.left = new TreeNode(6);
		root.right.right.right = new TreeNode(12);
		int most = test.getMost(root);
		System.out.println(most);
	}
}	