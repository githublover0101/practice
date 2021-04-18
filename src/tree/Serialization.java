package tree;

public class Serialization {

	public String serialization(TreeNode root) {
	    StringBuffer sb = new StringBuffer();
	    inorder(root, sb);
	    return sb.toString();   
	}

	public void inorder(TreeNode root, StringBuffer result) {
	    if(root == null) {
	        result.append("# ");
	    } else {
	        result.append(root.val).append(" ");
	        inorder(root.left, result);
	        inorder(root.right, result);
	    }
	}

	int index = 0;
	public TreeNode deserialization(String str) {
	    String[] s = str.split(" ");
	    TreeNode root = null;
	    create(root, s);
	    return root;
	}

	public void create(TreeNode root, String[] s) {
	    if(index == s.length) {
	        return;
	    }
	    if(s[index] != "#") {
	        root = new TreeNode(Integer.parseInt(s[index]));
	        index++;
	        create(root.left, s);
	        create(root.right, s);
	    }
	}

	public static void main(String[] args) {
		Serialization test = new Serialization();
		TreeNode root = new TreeNode(30);
		root.left = new TreeNode(10);
		root.left.left = new TreeNode(50);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(45);
		root.right.right = new TreeNode(35);
		String result = test.serialization(root);
		System.out.println(result);
		TreeNode node = test.deserialization(result);
		String ss = test.serialization(node);
		System.out.println(ss);
	}
}