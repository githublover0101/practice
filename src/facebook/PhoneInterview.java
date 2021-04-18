package facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import tree.TreeNode;


//class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//	TreeNode parent;
//}

class User {
	String id;
	int count;

	public User(String id, int count) {
		this.id = id;
		this.count = count;
	}

	public String toString() {
		return "[" + id + "," + count + "]";
	}
}

public class PhoneInterview {
	// 给一个bst，和其中一个节点的value，求在bst中比这个节点大的下一个node
	// ，O(1) space和 O(log N) time的解法，有Parent指针
	public TreeNode find(TreeNode root, int value) {
		if(root == null) return root;
		if(root.val == value) return root;
		if(root.val > value) 
			return find(root.left, value);
		else 
			return find(root.right, value); 
	}

	// 找一个节点的下一个节点
	// node的下一个节点为：
	// 		如果有右孩子的话，右孩子的最左边最左边
	//		如果没有的话，找
	TreeNode next(TreeNode node) {
		if(node == null) return null;
		TreeNode p = null;
		if(node.right != null) {
			p = node.right;
			while(p.left != null) {
				p = p.left;
			}
		} else {
			p = node.parent;
			while(p != null) {
				if(p.left == node) break;
				node = p;
				p = p.parent;
			}
		}
		
		return p;
	}

	public List<User> findTopKth(List<User> list, int k) {
		int povit = findKth(list, 0, list.size()-1, k);
		return list.subList(0, povit);
	}

	public static void main(String[] args) {
		PhoneInterview test = new PhoneInterview();
		List<User> list = new ArrayList<User>();
		Random random = new Random();
		for(int i = 0; i < 20; i ++) {
			User user = new User(String.valueOf(i+1), random.nextInt(1000));
			list.add(user);
		}
		
		int k = 7;
		List<User> result = test.findTopKth(list, k);
		for(User u: result) {
			System.out.println(u);
		}
		System.out.println("----the original list:-----");
		Collections.sort(list, new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				return o1.count - o2.count;
			}
			
		});
		
		System.out.println(list);
	}

	public int findKth(List<User> list, int start, int end, int k) {
		if(start < end) {
			int povit = partition(list, start, end);
			if(povit == k-1) return povit;
			if(povit > k-1) 
				return findKth(list, start, povit-1, k); 
			else
				return findKth(list, povit+1, end, k); 
		}
		return start;
	}

	public int partition(List<User> list, int start, int end) {
		int i = start-1;
		int key = list.get(end).count;
		for(int j = start; j < end; j++) {
			if(list.get(j).count > key) {
				i++;
				exchange(list, i, j);
			}
		}
		exchange(list, i+1, end);
		return i+1;
	}

	public void exchange(List<User> list, int i, int j) {
		User tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}
}