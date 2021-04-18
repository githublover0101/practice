package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Populating {

//	public void connect(TreeLinkNode root) {
//        if (root == null) return;
//        if (root.left != null)
//            root.left.next = root.right;
//        if (root.right != null && root.next!= null)
//            root.right.next = root.next.left;
//            
//        connect(root.left);
//        connect(root.right);
//    }
	
	public void connect(TreeLinkNode root) {
        // if (root == null) return;
        // if(root.right != null) {
        //     if(root.left != null) {
        //         root.left.next = root.right;
        //     }
        //     TreeLinkNode p = root;
        //     while(p.next != null && (p.next.left == null && p.next.right == null)) p = p.next;
        //     root.right.next = p.left != null ? p.left : p.right;
        // } else {
        //     if(root.left != null) {
        //         TreeLinkNode p = root;
        //         while(p.next != null && (p.next.left == null && p.next.right == null)) p = p.next;
        //         root.left.next = p.left != null ? p.left : p.right;
        //     }
        // }
        // connect(root.right);
        // connect(root.left);
        if(root == null) return;
        List<List<TreeLinkNode>> tree = levelOrder(root);
        for(List<TreeLinkNode> list: tree) {
            int count = list.size();
            for(int i = 0; i < count-1; i ++) {
                list.get(i).next = list.get(i+1);
            }
        }
    }
    
    public List<List<TreeLinkNode>> levelOrder(TreeLinkNode root) {
        List<List<TreeLinkNode>> tree = new ArrayList<List<TreeLinkNode>>();
        LinkedList<TreeLinkNode> nodeQueue = new LinkedList<TreeLinkNode>();
        LinkedList<Integer> heightQueue = new LinkedList<Integer>();
        
        List<TreeLinkNode> levelList = new ArrayList<TreeLinkNode>();
        nodeQueue.add(root);
        levelList.add(root);
        heightQueue.add(0);
        
        tree.add(levelList);
        
        while(!nodeQueue.isEmpty()) {
            TreeLinkNode curr = nodeQueue.pop();
            int height = heightQueue.pop()+1;
            if(height < tree.size()) {
                levelList = tree.get(height);
            } else {
                levelList = new ArrayList<TreeLinkNode>();
            }
            
            if(curr.left != null) {
                levelList.add(curr.left);
                heightQueue.add(height);
                nodeQueue.add(curr.left);
            }
            
            if(curr.right != null) {
                levelList.add(curr.right);
                heightQueue.add(height);
                nodeQueue.add(curr.right);
            }
            
            if(height >= tree.size() && levelList.size() > 0) {
                tree.add(levelList);
            }
        }
        return tree;
    }
	
	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.right = new TreeLinkNode(3);
//		root.right.left = new TreeLinkNode(4);
//		root.right.right = new TreeLinkNode(5);
		
		Populating test = new Populating();
		
		List<List<TreeLinkNode>> tree;
		tree = test.levelOrder(root);
		for(List<TreeLinkNode> list: tree) {
			for(TreeLinkNode node: list) {
				System.out.print(node.val + ",");
			}
			System.out.println();
		}
		
//		test.preprint(root);
//		test.connect(root);
//		test.preprint(root);
	}
	
	public void preprint(TreeLinkNode root) {
		if(root == null) return;
		System.out.print(root.val+",");
		preprint(root.left);
		preprint(root.right);
	}
	
}

class TreeLinkNode{
	int val;
	TreeLinkNode left, right, next;
	public TreeLinkNode(int val) {
		this.val = val;
	}
}
