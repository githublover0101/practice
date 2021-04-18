package start202104;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
}


public class TreeSolution {

	/**
	 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
	 * 236. 二叉树的最近公共祖先
	 * 
	 * //time: O(h), space: O(h)
	 * 
	 * 递归方式：
	 * 	1）如果 root = p或者q，则直接返回root
	 * 	2）递归找左子树、递归找右子树
	 * 	3）左子树结果为空，则返回右子树结果
	 * 	4）右子树结果为空，则返回左子树结果
	 * 	5）如果左右均不为空，且不相等时返回root，否则返回其一
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		//如果root == p 或者root == q，则直接返回root
		if(root == null || root == p || root == q) return root;
		
		//分别左右子树查找
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		
		//如果左子树不存在，则返回右子树
		if(left == null) return right;
		
		//如果右子树不存在，则返回左子树
		if(right == null) return left;
		
		//如果左右子树均存在，且不相等，则返回root
		return left == right ? left : root;
    }
	
	
	/**
	 * 二叉树的最近公共祖先
	 * 非递归方式
	 * 
	 * 需要借助两个栈，分别存储 root -> p, root -> q 之间的路径
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || root == p || root == q) return root;
		
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		
		findPath(root, p, stack1);
		findPath(root, q, stack2);
		
		TreeNode pre = root;
		
		TreeNode s1 = null;
		TreeNode s2 = null;
		while(!stack1.isEmpty() && !stack2.isEmpty()) {
			s1 = stack1.pop();
			s2 = stack2.pop();
			if(s1.val != s2.val) {
				return pre;
			}
			pre = s1;
		}
		return pre;
    }
	
	private boolean findPath(TreeNode root, TreeNode target, Stack<TreeNode> stack) {
		if(root == null || target == null) return false;
		
		//当root == target时，说明已经找到目标节点
		if(root.val == target.val) {
			//需要将root节点放入到结果集中
			stack.add(root);
			return true;
		}
		
		//递归查找左子树，只有找到的情况下，才将root放入结果集
		if(root.left != null && findPath(root.left, target, stack)) {
			stack.add(root);
			return true;
		}
		
		//递归查找右子树，只有找到的情况下，才将root放入结果集
		if(root.right != null && findPath(root.right, target, stack)) {
			stack.add(root);
			return true;
		}
		return false;
	}
	
	
	/**
	 * https://leetcode.com/problems/balanced-binary-tree/
	 * 判断二叉树是否为平衡二叉树
	 * 
	 * 判断依据：左右子树的高度差不大于1
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if(root == null) return true;
		int left = height(root.left);
		int right = height(root.right);
		if(Math.abs(right - left) > 1) { //如果左右子树高度差大于1，则返回false
			return false;
		}
		//分别递归判断左子树和右子树
		return isBalanced(root.left) && isBalanced(root.right);
    }
	
	//获取树的高度
	public int height(TreeNode root) {
		if(root == null) return 0;
		int left = height(root.left);
		int right = height(root.right);
		return Math.max(left, right) + 1;
	}
	
	
	/**
	 * 判断二叉树是否是对称二叉树
	 * 1）实现通用检测函数，输入参数为两个树节点，p & q
	 * p.left 和 q.right 节点递归判断
	 * p.right 和 q.left 节点递归判断
	
	1
   / \
  2   2
 / \ / \
3  4 4  3

	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }
	
	private boolean isSymmetric(TreeNode p, TreeNode q) {
		if(p == null && q == null) return true;
		if(p == null && q != null) return false;
		if(p != null && q == null) return false;
		if(p.val != q.val) return false;
		return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
	}
	
	
	/**
	 * 获取二叉树的最低高度
	 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
	 * 
	 * 注意：
	 * 1）当二叉树左子树为空，最低高度为右子树的高度
	 * 2）当二叉树右子树为空，最低高度为左子树的高度
	 * 3）当左右子树均不为空时，选较低的高度
	 * 
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return minDepth(root.right) + 1;
        if(root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
	
	/**
	 * 二叉树遍历 - 前序遍历
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
        preorder(root, list);
        return list;
    }
	
	private void preorder(TreeNode root, List<Integer> list) {
		if(root == null) return;
		list.add(root.val);
		preorder(root.left, list);
		preorder(root.right, list);
	}
	
	/**
	 * 二叉树遍历 - 前序遍历
	 * 
	 * 用栈实现
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal2(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        
        while(p != null || !stack.isEmpty()) {
        	if(p != null) {
        		list.add(p.val);
        		if(p.right != null) {
        			stack.push(p.right);
        		}
        		p = p.left;
        	} else {
        		p = stack.pop();
        	}
        }
        return list;
    }
	
	public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        
        while(p != null || !stack.isEmpty()) {
        	if(p != null) {
        		stack.push(p);
        		p = p.left;
        	} else {
        		p = stack.pop();
        		list.add(p.val);
        		p = p.right;
        	}
        }
        return list;
    }
	
	//采用头插法：
    //每次采用将元素放入到0位的方式，放入顺序：root -> right -> left
    public List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        
        while(p != null || !stack.isEmpty()) {
        	if(p != null) {
                
                //采用头插法，将元素每次放入到0位
        		list.add(0, p.val);
                
                //将左子树放到stack中
        		if(p.left != null) {
        			stack.push(p.left);
        		}
                
                //优先访问右子树
        		p = p.right;
        	} else {
        		p = stack.pop();
        	}
        }
        return list;
    }

	
	/**
	 * 二叉树 - 中序遍历
	 * 递归实现
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inorder(root, list);
        return list;
    }
	
	private void inorder(TreeNode root, List<Integer> list) {
		if(root == null) return;
		inorder(root.left, list);
		list.add(root.val);
		inorder(root.right, list);
	}
	
	/**
	 * 二叉树 - 中序遍历
	 * 迭代实现
	 * 
	 * 使用栈stack
	 * 1）//每次获取元素，将left push到stack，直到遍历到最左子节点
	 * 2）//当p = null时，说明已经到达最左子节点，开始从stack中pop元素
	 * 3）//将p指向右节点
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalII(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        
        //每次获取元素，将left push到stack，直到遍历到最左子节点
        while(p != null || !stack.isEmpty()) {
        	if(p != null) {
        		stack.push(p);
        		p = p.left;
        	} else {
        		
        		//当p = null时，说明已经到达最左子节点，开始从stack中pop元素
        		p = stack.pop();
        		list.add(p.val);
        		
        		//将p指向右节点
        		p = p.right;
        	}
        }
        return list;
    }
	
	/**
	 * 二叉树 - 后序遍历
	 * 
	 * 使用栈
	 */
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		postorder(root, list);
		return list;
    }
	
	private void postorder(TreeNode root, List<Integer> list) {
		if(root == null) return;
		postorder(root.left, list);
		postorder(root.right, list);
		list.add(root.val);
	}
	
	public List<Integer> postorderTraversalII(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		TreeNode p = null;
		
		while(!stack.isEmpty()) {
			p = stack.peek();
			if(p.left == null && p.right == null) {
				list.add(p.val);
				stack.pop();
			} else {
				if(p.right != null) {
					stack.push(p.right);
					p.right = null;
				}
				if(p.left != null) {
					stack.push(p.left);
					p.left = null;
				} 
			}
		}
		return list;
    }
	
}
