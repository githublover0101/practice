public class ConstructBinaryTree {

	// preorder[0] is the root of the tree, root
	// find root in inorder, index is mid, left is left[start, mid-1], right is right[mid+1, end] 
	// 
	public TreeNode buildTreeWithPreorderAndInorder(int[] preorder, int[] inorder) {
  		return buildTreeWithPreorderAndInorder(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    public TreeNode buildTreeWithPreorderAndInorder(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
    	if(preStart > preEnd || inStart > inEnd) return null;
    	int rootVal = preorder[preStart];
    	TreeNode root = new TreeNode(rootVal);
    	int mid = inStart;
    	for(; mid <= inEnd; mid++) {
    		if(inorder[mid] == rootVal) {
    			break;
    		}
    	}
    	// [inStart...mid-1], [mid+1, inEnd] in inorder
    	// [preStart+1, preStart+(mid-inStart)] [preStart+(mid-inStart)+1, preEnd]
    	root.left = buildTree(preorder, inorder, preStart+1, preStart+(mid-inStart), inStart, mid-1);
    	root.right = buildTree(preorder, inorder, preStart+(mid-inStart)+1, preEnd, mid+1, inEnd);
    	return root;
    }

    // build tree using inorder & postorder
    public TreeNode buildTreeWithInorderAndPostorder(int[] inorder, int[] postorder) {
        return  buildTreeWithInorderAndPostorder(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }

    public TreeNode buildTreeWithInorderAndPostorder(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
    	if(inStart > inEnd || postStart > postEnd) return null;
    	int rootVal = postorder[postEnd];
    	TreeNode root = new TreeNode(rootVal);
    	int mid = inStart;
    	for(; mid <= inEnd; mid++) {
    		if(inorder[mid] == rootVal) {
    			break;
    		}
    	}
    	
    	// [inStart...mid-1], [mid+1...inEnd] in inorder 
    	// [postStart...postStart+(mid-inStart)-1], [preStart+(mid-inStart), preEnd-1]
    	root.left = buildTreeWithInorderAndPostorder(inorder, postorder, inStart, mid-1, postStart, postStart+(mid-inStart)-1);
    	root.right = buildTreeWithInorderAndPostorder(inorder, postorder, mid+1, inEnd, postStart+(mid-inStart), postEnd-1);
    	return root;
    }	

    public void connect(TreeLinkNode root) {
     	if(root == null) return;
     	if(root.left != null)
     		root.left.next = root.right;
     	if(root.right != null && root.next != null)
     		root.right.next = root.next.left;
     	connect(root.left);
     	connect(root.right);  	   
    }
}