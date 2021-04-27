package start202104;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	
	/***
	 * https://leetcode.com/problems/validate-binary-search-tree/
	 * 判断二叉树是否为二叉搜索树
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if(root == null) return true;
		//初始化传入为空
		return isValidBST(root, null, null);
    }
	
	private boolean isValidBST(TreeNode root, Integer min, Integer max) {
		if(root == null) return true;
		//只有满足root条件时，才递归检测左子树和右子树
		if((min == null || root.val > min) && (max == null || root.val < max)) {
			return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
		}
		return false;
	}
	
	/**
	 * https://leetcode-cn.com/problems/path-sum/
	 * 路径总和为targetSum
	 * 
	 * @param root
	 * @param targetSum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == targetSum) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
	
	
	/**
	 * https://leetcode.com/problems/invert-binary-tree/
	 * 翻转二叉树
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if(root == null) return root;
		if(root.left == null && root.right == null) return root;
		
		// 递归处理左子树		
		TreeNode left = invertTree(root.left);
		// 递归处理右子树
		TreeNode right = invertTree(root.right);
		
		//左右交换
		root.left = right;
		root.right = left;
		return root;
    }
	
	
	public void flatten(TreeNode root) {
		if(root == null) return;
		if(root.left == null && root.right == null) return;
		dfsFlatten(root);
    }
	
	private TreeNode dfsFlatten(TreeNode root) {
		if(root == null) return root;
		if(root.left == null && root.right == null) return root;
		
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = null;
		if(left != null) {
			root.right = left;
			root = dfsFlatten(left);
		}
		if(right != null) {
			root.right = right;
			root = dfsFlatten(right);
		}
		return root;
	}
	
	/**
	 * 
	 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
	 * 
	 * 前序遍历存储到list中，然后再对数组进行拼接成链表
	 * 
	 * 时间复杂度为O(n)，空间复杂度为O(n)
	 * 
	 * @param root
	 */
	public void flattenByPreorder(TreeNode root) {
		if(root == null) return;
		if(root.left == null && root.right == null) return;
		List<TreeNode> list = new ArrayList<TreeNode>();
		preorderTraversal(root, list);
		int len = list.size();
		for(int i = 1; i < len; i ++) {
			TreeNode pre = list.get(i-1);
			TreeNode cur = list.get(i);
			pre.left = null;
			pre.right = cur;
		}
    }
	
	private void preorderTraversal(TreeNode root, List<TreeNode> list) {
		if(root == null) return;
		list.add(root);
		preorderTraversal(root.left, list);
		preorderTraversal(root.right, list);
	}
	
	
	/**
	 * 
	 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
	 * 二叉树展开为链表
	 * 
	 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
	 * 
	 * 解法二：寻找前置节点的方式
	 * 
	 * 时间复杂度为O(n)，空间复杂度为O(1)
	 * 
	 * @param root
	 */
	public void flattenByPre(TreeNode root) {
		if(root == null) return;
		if(root.left == null && root.right == null) return;
		TreeNode cur = root;
		TreeNode next = null;
		TreeNode pre = null;
		while(cur != null) {
			if(cur.left != null) {
				next = cur.left;
				cur.left = null;
				pre = next;
				
				//遍历找到前驱节点pre
				while(pre.right != null) {
					pre = pre.right;
				}
				pre.right = cur.right;
				cur.right = next;
			}
			cur = cur.right;
		}
		
	}
	
	TreeNode head = null;
	TreeNode tail = null;
	
	/**
	 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
	 * 剑指 Offer 36. 二叉搜索树与双向链表
	 * 
	 * 头条面试题：
	 * 将二叉树以中序遍历的方式转换成双向链表，额外空间复杂度是O(1)
	 * 
	 * @param root
	 * @return
	 */
	public TreeNode treeToDoublyList(TreeNode root) {
		if(root == null) return root;
		
		build(root);
		
		//遍历完成后修改head的前驱left和tail的后继right
		head.left = tail;
		tail.right = head;
		return head;
	}
	
	//采用中序遍历处理
	private void build(TreeNode root) {
		if(root == null) return;
		//先递归遍历左子树
		build(root.left);
		
		if(head == null && tail == null) { //当head和tail是null时，直接赋值成root
			head = tail = root;
		} else {
			//将节点拼接到tail尾结点，并修改尾结点为该节点
			tail.right = root;
			root.left = tail;
			tail = root; //每次修改尾结点指向
		}
		
		//再递归遍历右子树
		build(root.right);
	}
	
	/**
	 * 在二叉搜索树中查找第k小的元素
	 * 时间复杂度为O(n)，空间复杂度为O(n)
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        List<Integer> list = new ArrayList<Integer>();
        
        //先中序遍历，存储到list中，直接获取k-1
        convertToListByInorder(root, list);
        if(k < 1 || k > list.size()) return -1;
        return list.get(k-1);
    }
	
	private void convertToListByInorder(TreeNode root, List<Integer> list) {
		if(root == null) return;
		convertToListByInorder(root.left, list);
		list.add(root.val);
		convertToListByInorder(root.right, list);
	}
	
	
	/**
	 * 中序遍历，遍历到第k个则返回，不需要遍历整棵树
	 * 时间复杂度：O(H+k)，其中 H 指的是树的高度
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallestII(TreeNode root, int k) {
        if(root == null) return -1;
        List<Integer> list = new ArrayList<Integer>();
        //先中序遍历，存储到list中
        convertToListByInorderWithK(root, list, k);
        return list.get(k-1);
    }
	
	//不需要中序遍历所有节点，仅需要遍历k个节点即可
	private void convertToListByInorderWithK(TreeNode root, List<Integer> list, int k) {
		if(root == null) return;
		
		//已经遍历了k个，结束遍历
		if(list.size() == k) return;
		convertToListByInorderWithK(root.left, list, k);
		list.add(root.val);
		convertToListByInorderWithK(root.right, list, k);
	}
	
	//非递归方式中序遍历，遍历到第k个，立即返回
	//时间复杂度为O(H+k)，空间复杂度为O(H+k)，H为树的高度 
	public int kthSmallestIII(TreeNode root, int k) {
        if(root == null) return -1;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        int index = 0;
        while(p != null || !stack.isEmpty()) {
        	if(p != null) {
        		stack.push(p);
        		p = p.left;
        	} else {
        		p = stack.pop();
        		index ++;
        		if(index == k) {
        			return p.val;
        		}
        		p = p.right;
        	}
        }
        return -1;
    }
	
	//获取左右子树节点数：
	// 1）如果左子树节点数 == k-1，则直接返回root；
	// 2）如果左子树节点数 > k-1，在右边查找
	// 3）如果左子树节点数 < k-1，在左边查找
	public int kthSmallestIIII(TreeNode root, int k) {
		if(root == null) return 0;
		int left = numberOfNodes(root.left);
		if(left == k-1) {
			return root.val;
		} else if(left > k-1) {
			return kthSmallestIIII(root.left, k);
		} else {
			//在右子树查找，注意获取个数为 k-left-1
			return kthSmallestIIII(root.right, k-left-1);
		}
	}
	
	//获取节点数函数
	private int numberOfNodes(TreeNode root) {
		if(root == null) return 0;
		return numberOfNodes(root.left) + numberOfNodes(root.right) + 1;
	}

	/** 
	 * https://leetcode.com/problems/path-sum-ii/
	 * 
	 * @param root
	 * @param targetSum
	 * @return
	 */
	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allList = new ArrayList<List<Integer>>();
        if(root == null) return allList;
        List<Integer> subList = new ArrayList<Integer>();
        dfs(root, targetSum, subList, allList);
        return allList;
    }
    
    private void dfs(TreeNode root, int targetSum, List<Integer> subList, List<List<Integer>> allList) {
        if(root == null) return;
        
        subList.add(root.val);
        targetSum = targetSum - root.val;
        
        //如果遇到正确的结果，将其加入到结果列表 allList中
        if(root.left == null && root.right == null && targetSum == 0) {
            allList.add(new ArrayList<Integer>(subList));
        }
        
        dfs(root.left, targetSum, subList, allList);
        dfs(root.right, targetSum, subList, allList);
        subList.remove(subList.size()-1);
    }
    
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if(preorder.length == 0 || inorder.length == 0) return null;
    	return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    
    private TreeNode buildTree(int[] preorder, int preleft, int preright, int[] inorder, int inleft, int inright) {
    	int rootVal = preorder[preleft];
    	TreeNode root = new TreeNode();
    	
    	int index = inleft;
    	while(index <= inright && inorder[index] != rootVal) index++;
    	int leftCount = index - inleft;
    	//pre left [preleft+1, preleft+leftCount], [preleft+leftCount+1, preright]
    	//in left [inleft, inleft+leftCount-1, inleft+leftCount+1, inright]
    	root.left = buildTree(preorder, preleft+1, preleft+leftCount, inorder, inleft, inleft+leftCount-1);
    	root.right = buildTree(preorder, preleft+leftCount+1, preright, inorder, inleft+leftCount+1, inright);
    	return root;
    }
    
    
    /**
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/
     * 129. 求根到叶子节点数字之和
     * 
     * 时间复杂度为O(n), 空间复杂度为O(n)
     */
    int totalSum = 0;
    public int sumNumbers(TreeNode root) {
    	if(root == null) return 0;
    	int subSum = 0;
    	computeSum(root, subSum);
    	return totalSum;
    }
    
    private void computeSum(TreeNode root, int subSum) {
    	if(root == null) return;
    	subSum = subSum*10 + root.val;
    	if(root.left == null && root.right == null) {
    		//当遇到叶子节点的时候，需要将当前子计算结果加入到总结果中
    		totalSum += subSum;
    		return;
    	}
    	computeSum(root.left, subSum);
    	computeSum(root.right, subSum);
    }    
    
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode p = headA;
        ListNode q = headB;
        
        //分别计算出链表的长度len1和len2
        int len1 = 0;
        while(p != null) {
        	len1++;
        	p = p.next;
        }
        int len2 = 0;
        while(q != null) {
        	len2++;
        	q = q.next;
        }
        
        //针对较长的链表，可以用指针先走 len1-len2步
        ListNode first = null; //the long one
        ListNode second = null;
        int size = 0;
        if(len1 > len2) {
        	first = headA;
        	second = headB;
        	size = len1-len2;
        } else {
        	first = headB;
        	second = headA;
        	size = len2-len1;
        }
        int k = 0;
        while(k < size) {
        	first = first.next;
        	k++;
        }
        
        //两个指针再同时一步一步走
        while(first != second) {
        	first = first.next;
        	second = second.next;
        }
        return first;
    }
    
    //判断链表是否有环
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        	if(slow == fast) {
        		return true;
        	}
        }
        return false;
    }
    
    //判断链表是否有环，如果有环找到环的所在节点
    public ListNode detectCycle(ListNode head) {
    	if(head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        	if(slow == fast) {
        		break;
        	}
        }
        //如果fast==null，说明不存在环
        if(fast == null || fast.next == null) return null;
        
        slow = head;
        while(slow != fast) {
        	fast = fast.next;
        	slow = slow.next;
        }
        return slow;
    }
    
    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return false;
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        char c;
        for(int i = 0; i < len; i ++) {
        	c = s.charAt(i);
        	
        	if(map.containsKey(c)) {
        		if(stack.isEmpty() || stack.pop() != map.get(c)) {
        			return false;
        		}
        	} else {
        		stack.push(c);
        	}
        }
        return stack.isEmpty();
    }
    
    /**
     * 二叉树的右视图
     * 层次遍历，遍历每层的元素，将每层的最后一位元素放入到结果集中
     * 
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> list = new ArrayList<Integer>();
    	if(root == null) return list;
    	
    	LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	int index = 0;
    	int levelSize = 0;
    	while(!queue.isEmpty()) {
    		//获取当前队列长度，即每一层的元素个数
    		levelSize = queue.size();
    		index = 0;
    		while(index < levelSize) {
    			TreeNode cur = queue.pop();
    			if(cur.left != null) {
    				queue.add(cur.left);
    			}
    			if(cur.right != null) {
    				queue.add(cur.right);
    			}
    			index++;
    			//当遍历到这一层中最后一个元素时，则放入结果集合中，index == size
    			if(index == levelSize) {
    				list.add(cur.val);
    			}
    		}
    	}
    	return list;
    }
    
    
}
