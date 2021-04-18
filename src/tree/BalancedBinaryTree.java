package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BalancedBinaryTree {

	public boolean isBalanced(TreeNode root) {
		if(root == null) return true;
		int left = height(root.left);
		int right = height(root.right);
		if(Math.abs(left - right) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}

	public int height(TreeNode root) {
		if(root == null) return 0;
		int left = height(root.left);
		int right = height(root.right);
		return left > right ? left+1 : right+1; 
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
     	if(p == null && q == null) return true;
     	if(p == null) return false;
     	if(q == null) return false;
     	if(p.val != q.val) return false;
     	return isSameTree(p.left, q.left) && isSameTree(p.right, q.right); 
    }

    public boolean hasPathSum(TreeNode root, int sum) {
     	List<Integer> pathList = getPathList(root);
     	for(int path: pathList) {
    		if(path == sum) {
    			return true;
    		} 
    	}
    	return false;
    }

    public List<Integer> getPathList(TreeNode root) {
    	List<Integer> pathList = new ArrayList<Integer>();
    	if(root == null) return pathList;

    	LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
    	LinkedList<Integer> sumQueue = new LinkedList<Integer>();
    	nodeQueue.add(root);
    	sumQueue.add(root.val);

    	while(!nodeQueue.isEmpty()) {
    		TreeNode curr = nodeQueue.pop();
    		int sum = sumQueue.pop();
    		if(curr.left != null) {
    			nodeQueue.push(curr.left);
    			sumQueue.push(sum+curr.left.val);
    		}
    		if(curr.right != null) {
    			nodeQueue.push(curr.right);
    			sumQueue.push(sum+curr.right.val);
    		}
    		if(curr.left == null && curr.right == null) {
    			pathList.add(sum);
    		}
    	}
    	return pathList;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
     	List<List<Integer>> list = new ArrayList<List<Integer>>();
     	
     	return list;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
     	List<List<Integer>> allList = new ArrayList<list<Integer>>();
     	if(root == null) return allList;
     	List<Integer> subList = new ArrayList<Integer>();
     	levelOrderHelper(root, allList, subList);   
     	return allList;
    }

    public void levelOrderHelper(TreeNode root, List<List<Integer>> allList, List<Integer> subList) {
    	if(root == null) return allList;
    	subList.add();
    }

    public static void main(String[] args) {
    	BalancedBinaryTree test = new BalancedBinaryTree();
    	List<TreeNode> list = new ArrayList<TreeNode>();
    	list.add(null);
    	list.add(new TreeNode(1));

    	for(TreeNode node: list) {
    		System.out.println(node);
    	}

    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
    	boolean result = test.hasPathSum(root, 3);
    	System.out.println(result);
    }

}