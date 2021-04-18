package tree;

public class ZigzagLevelOrder{

    // normal level order tree
    // print the list of the result
	public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> tree = levelOrder(root);
        int index = 1;
        for(List<Integer> list: tree) {
        	if(index%2 == 0){
        		Collections.reverse(list);
        	}
        	index++;
        }
        return tree;
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> tree = new ArrayList<List<Integer>>();
        if(root == null) return tree;

        List<Integer> values = new ArrayList<Integer>();
        List<TreeNode> levelNodes = new ArrayList<TreeNode>();
        levelNodes.add(root);
        values.add(root.val);
        tree.add(levelNodes);

        while(!levelNodes.isEmpty()) {
        	List<TreeNode> children = new ArrayList<TreeNode>();
        	values = new ArrayList<Integer>();
        	for(TreeNode node: levelNodes) {
        		if(node.left != null) {
        			children.add(node.left);
        			values.add(node.left.val);
        		}
        		if(node.right != null) {
        			children.add(node.right);
        			values.add(node.right.val);
        		}
        	}
        	if(!children.isEmpty()) {
        		tree.add(values);
        		levelNodes.clear();
        		levelNodes.addAll(children);
        	}
        }
        return tree;
    }

    // use two stacks to save the nodes level by level
    // use stack's specific: 
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> tree = new ArrayList<List<Integer>>();
        if(root == null) return tree;

        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();

        stack1.add(root);
        int count = 0;
        TreeNode currNode = null;
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> levelNodes = new ArrayList<Integer>();
            if(count%2 == 0) {
                while(!stack1.isEmpty()) {
                    currNode = stack1.pop();
                    levelNodes.add(currNode.val);
                    if(currNode.left != null) stack2.push(currNode.left);
                    if(currNode.right != null) stack2.push(currNode.right);
                }
            } else {
                while(!stack2.isEmpty()) {
                    currNode = stack2.pop();
                    levelNodes.add(currNode.val);
                    if(currNode.right != null) stack1.push(currNode.right); //should attention the right&left's order
                    if(currNode.left != null) stack1.push(currNode.left);
                }
            }
            if(levelNodes.size() > 0) tree.add(levelNodes);
            count++;
        }
        return tree;
    }


}