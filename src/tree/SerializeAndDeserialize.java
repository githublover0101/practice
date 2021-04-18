package tree;

import java.util.HashMap;
import java.util.StringTokenizer;

public class SerializeAndDeserialize {

	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return null;
        StringBuffer sb = new StringBuffer();
        serialize(root, sb);
        return sb.substring(0, sb.length()-1);
    }
    
    private void serialize(TreeNode node, StringBuffer sb) {
        if(node == null) {
            sb.append("null,");
        } else {
            sb.append(node.val).append(",");
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if(data == null || data.length() == 0) return null;
        StringTokenizer tokens = new StringTokenizer(data, ",");
        return deserialize1(tokens);
    }
    
    public TreeNode deserialize1(StringTokenizer tokens) {
        if(!tokens.hasMoreTokens()) return null;
        String token = tokens.nextToken();
        if("null".equals(token)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserialize1(tokens);
        node.right = deserialize1(tokens);
        return node;
    }
    
    int index = 0;
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        String[] tokens = data.split(",");
        return deserialize(tokens);
    }
    
    public TreeNode deserialize(String[] tokens) {
        if(index == tokens.length) return null;
        String token = tokens[index];
        index++;
        if("null".equals(token)) return null;
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = deserialize(tokens);
        node.right = deserialize(tokens);
        return node;
    }
    
//    levelorder and append node value
//    public String serializeNonRecursively(TreeNode root) {
//    	if(root == null) return null;
//        StringBuffer sb = new StringBuffer();
//    	
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        queue.add(root);
//        sb.append(root.val).append(",");
//        while(!queue.isEmpty()) {
//            TreeNode cur = queue.poll();
//            if() {
//            	
//            }
//        }
//    	return sb.substring(0, sb.length()-1);
//    }
    
    public TreeNode deserializeNonRecursively(String data) {
    	return null;
    }
    
    public static void main(String[] args) {
    	String s = "{1,#,2}";
//    	SerializeAndDeserialize test = new SerializeAndDeserialize();
//    	test.deserialize(s);
//    	HashMap<Integer, String> map = new HashMap<Integer, String>();
//    	
    	String a = new String("abc");
    	String b = new String("abc");
    	System.out.println(a == b);
    	System.out.println(a.equals(b));
    	System.out.println(a.hashCode());
    	System.out.println(a.hashCode());
    }
	
}
