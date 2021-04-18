package tree;

public class BSTIterator {

	private List<Integer> list = null;
	private int counter = 0;
	private int len = 0;

	//the iterator init to be the most left node
    public BSTIterator(TreeNode root) {
    	list = new ArrayList<Integer>();
    	inorder(root, list);
        len = list.size();
    }

    private void inorder(TreeNode root, List<Integer> list) {
    	if(root == null) return;
    	inorder(root.left, list);
    	list.add(root.val);
    	inorder(root.right, list);
    }

    //when the iterator points to the most right node, has next is false
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return counter < len;
    }

    /** @return the next smallest number */
    public int next() {
        if(hasNext()) {
        	int num = list.get(counter);
        	counter++;
        	return num;
        } 
        return -1;
    }
}

public class BSTIterator2 {

	//use two stacks to save the height nodes in left tree & right tree
	private Stack<TreeNode> leftStack = null;
	private Stack<TreeNode> rightStack = null;

	//the iterator init to be the most left node
    public BSTIterator(TreeNode root) {
    	leftStack = new Stack<TreeNode>();
    	rightStack = new Stack<TreeNode>();
    }

    //when the iterator points to the most right node, has next is false
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !(leftStack.isEmpty() && rightStack.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        if(!leftStack.isEmpty()) {
        	TreeNode cur = leftStack.peek();
        	if(cur.right == null) {
        		leftStack.pop();
        		return cur.val;
        	} else {
        		// find cur node's right's most left node, that is the next one, and push it to the stack
        		TreeNode p = cur.right;
        		while(p.left != null) {
        			leftStack.push(p);
        			p = p.left;
        		}
        		leftStack.push(p);
        		return p.val;
        	}
        }
    }
}