package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecoverBST {
	// public void recoverTree(TreeNode root) {
 //        if(root == null) return;
 //        if(root.left == null && root.right == null) return;
 //        List<TreeNode> allList = inorder(root);
 //        TreeNode temp = null;
 //        int i = 0;
 //        for(; i < allList.size()-1; i ++) {
 //        	if(allList.get(i).val > allList.get(i+1).val) {
 //        		break;
 //        	}
 //        }

 //        int j = i+1;
 //        for(; j < allList.size()-1; j ++) {
 //        	if(allList.get(j).val > allList.get(j+1).val) {
 //        		break;
 //        	}
 //        }
 //        temp = allList.get(i);
 //        allList.set(i, allList.get(j));
 //        allList.set(j, temp);

 //    }

    public static void main(String[] args) {
    	RecoverBST test = new RecoverBST();
    	TreeNode root = new TreeNode(1);
    	root.left = new TreeNode(2);
        root.right = new TreeNode(3);
    	test.recoverTree(root);
    	
    }

    // public List<TreeNode> inorder(TreeNode root) {
    // 	List<TreeNode> allList = new ArrayList<TreeNode>();
    // 	if(root == null) return allList; 
    // 	inorderHelper(root, allList);
    // 	return allList;
    // }

    // public void inorderHelper(TreeNode root, List<TreeNode> allList) {
    // 	if(root == null) return;
    // 	inorderHelper(root.left, allList);
    // 	allList.add(root);
    // 	inorderHelper(root.right, allList);
    // }

    public void recoverTree(TreeNode root) {
        
    }

    public void recoverTree2(TreeNode root) {
        List<TreeNode> treeList = inorder(root);
        treeList.toArray()
        for(TreeNode node: treeList) {
            System.out.println(node.val);
        }

        int len = treeList.size();

        int i = 0;
        while(i < treeList.size()-1 && treeList.get(i).val <= treeList.get(i+1).val) {
            i++;
        }
        int j = len-1;
        while(j > 0 && treeList.get(j).val >= treeList.get(j-1).val) {
            j--;
        }

        TreeNode p = treeList.get(i);
        TreeNode q = treeList.get(j);
        int tmp = p.val;
        p.val = q.val;
        q.val = tmp;
    }

    public List<TreeNode> inorder(TreeNode root) {
        List<TreeNode> allList = new ArrayList<TreeNode>();
        if(root == null) return allList; 
        inorderHelper(root, allList);
        return allList;
    }

    public void inorderHelper(TreeNode root, List<TreeNode> allList) {
        if(root == null) return;
        inorderHelper(root.left, allList);
        allList.add(root);
        inorderHelper(root.right, allList);
    }
}