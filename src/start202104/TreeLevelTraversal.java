package start202104;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeLevelTraversal {

	/**
	 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
	 * 二叉树的层次遍历 - 递归解法
	 * 利用树的高度
	 * 
	 * 时间复杂度为O(n)，空间复杂度为O(h)，h为树的高度
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		if(root == null) return allList;
		levelOrder(root, allList, 0);
		return allList;
    }
	
	private void levelOrder(TreeNode root, List<List<Integer>> allList, int height) {
		if(root == null) return;
		List<Integer> subList = null;
		
		//说明当前要遍历的高度已经大于已遍历的高度，要开始新一层次的遍历
		if(height >= allList.size()) { 
			subList = new ArrayList<Integer>(); // 将子数组创建好
			allList.add(subList);
		} else {
			subList = allList.get(height);
		}
		subList.add(root.val);
		levelOrder(root.left, allList, height+1);
		levelOrder(root.right, allList, height+1);
	}
	
	//使用队列，时间复杂度为O(n)，空间复杂度为O(n)
	public List<List<Integer>> levelOrderII(TreeNode root) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		if(root == null) return allList;
		List<Integer> subList = null;
		
		//定义队列
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		
		queue.add(root);
		while(!queue.isEmpty()) {
			subList = new ArrayList<Integer>();
			
			int nodeSize = queue.size();
			for(int i = 0; i < nodeSize; i++) {
				
				//取出队列中的值
				TreeNode cur = queue.remove();
				
				subList.add(cur.val);
				if(cur.left != null) {
					queue.add(cur.left);
				}
				if(cur.right != null) {
					queue.add(cur.right);
				}
			}
			allList.add(subList);
		}
		
		return allList;
    } 
	
	
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> allList = new ArrayList<List<Integer>>();
		if(root == null) return allList;
		LinkedList<Integer> subList = null;
		//定义队列
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int rowIndex = 0;
		while(!queue.isEmpty()) {
			subList = new LinkedList<Integer>();
			
			int nodeSize = queue.size();
			for(int i = 0; i < nodeSize; i++) {
				
				//取出队列中的值
				TreeNode cur = queue.remove();
				
				//如果是偶数，则追加到数组最后一位
				if(rowIndex%2 == 0) {
					subList.addLast(cur.val);
				} else {
					//添加到第一位
					subList.addFirst(cur.val);
				}
				
				if(cur.left != null) {
					queue.add(cur.left);
				}
				if(cur.right != null) {
					queue.add(cur.right);
				}
			}
			allList.add(subList);
			rowIndex++;
		}
		
		return allList;
    }
	
	class Node {
		int val;
		List<Node> children;
	}
	
	/**
	 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
	 * 后序遍历N叉树
	 * @param root
	 * @return
	 */
	public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        postorderHelper(root, list);
        return list;
    }

    private void postorderHelper(Node root, List<Integer> list) {
        if(root == null) return;
        List<Node> children = root.children;
        for(Node c: children) {
            postorderHelper(c, list);
        }
        list.add(root.val);
    }
    
    /**
     * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
     * N叉树后序遍历
     * 
     * 时间复杂度为O(M)，M为N叉树节点个数
     * 空间复杂度为O(M)，M为N叉树节点个数
     * 
     * @param root
     * @return
     */
    public List<Integer> postorderII(Node root) {
    	LinkedList<Integer> list = new LinkedList<Integer>();
        if(root == null) return list;
        Stack<Node> stack = new Stack<Node>();
        stack.add(root);
        while(!stack.isEmpty()) {
        	Node cur = stack.pop();
        	list.addFirst(cur.val);
        	for(Node item: cur.children) {
        		if(item != null) {
        			stack.add(item);
        		}
        	}
        }
        return list;
    }
    
    
    /**
     * 二叉树后序遍历
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * 
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
    	LinkedList<Integer> list = new LinkedList<Integer>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root);

        //放入栈的顺序：root -> left -> right
        while(!stack.isEmpty()) {
        	TreeNode cur = stack.pop();

            //头插法，放入第一位
        	list.addFirst(cur.val);
        	if(cur.left != null) {
                stack.add(cur.left);
            }
            if(cur.right != null) {
                stack.add(cur.right);
            }
        }
        return list;
    }
    
    
    /**
     * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
     * N叉树的层序遍历
     * 
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	if(root == null) return list;
    	List<Integer> subList = null;
    	LinkedList<Node> queue = new LinkedList<Node>();
    	queue.add(root);
    	while(!queue.isEmpty()) {
    		int levelSize = queue.size();
    		subList = new ArrayList<Integer>();
    		for(int i = 0; i < levelSize; i++) {
    			Node cur = queue.pop();
    			subList.add(cur.val);
    			
    			for(Node child: cur.children) {
    				queue.add(child);
    			}
    		}
    		list.add(subList);
    	}
    	return list;
    }
	
}
