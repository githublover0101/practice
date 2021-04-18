package binary.search;

public class FindRange {
	public int findCount(int[] num, int target) {
		int start = searchLeftIndex(num, target);
		int end = searchRightIndex(num, target);
		if(start == end && start == 0) 
			return 0;
		return end - start + 1;
	}

	public int searchLeftIndex(int[] num, int target) {
		int start = 0;
		int end = num.length-1;
		int mid = 0;
		while(start < end) {
			mid = start + (end - start)/2;
			if(num[mid] < target)
				start = mid + 1;
			else 
				end = mid;
		}
		return start;
	}

	public int searchRightIndex(int[] num, int target) {
		int start = 0;
		int end = num.length-1;
		int mid = 0;
		while(start < end) {
			mid = start + (end - start)/2 + 1;
			if(num[mid] > target)
				end = mid - 1;
			else 
				start = mid;
		}
		return start;
	}

	public static void main(String[] args) {
		FindRange test = new FindRange();
		int[] num = {0,0,1,1,1,1,1,1,3,3,4,5,6,7,8,9};
		int result = test.findCount(num, 2);

		String s = "abbbbbbccccABGHHHJ";
		String result1 = test.compressString(s);
		System.out.println(result1);
	}

//	public TreeNode findLCA(TreeNode root, TreeNode node1, TreeNode node2) {
//		if(root == null) return root;
//		if(root == node1 || root == node2) return root;
//		int left = findLCA(root.left, node1, node2);
//		int right = findLCA(root.left, node1, node2);
//		if(left != null && right != null) 
//			return root;
//		return left == null ? right : left;
//	}
//
//	public boolean findPath(TreeNode root, TreeNode node1, Stack<TreeNode> stack) {
//		if(root == null) return false;
//		if(root == node1) return true;
//		stack.push(root);
//		return findPath(root.left, node1, stack) || findPath(root.right, node1, stack);
//	}

	public String compressString1(String s) {
		if(s == null || "".equals(s)) return s;
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		sb.append(c[0]);
		int count = 0;
		for(int i = 1; i < c.length; i ++) {
			if(c[i] == c[i-1]) {
				count++;
			} else {
				if(count > 1) sb.append(count);
				sb.append(c[i]);
				count = 1;
			}
		}
		return sb.toString();
	}
	
	public String compressString(String s) {
		if(s == null || "".equals(s)) return s;
		char[] c = s.toCharArray();
		int newLen = 1;
		int count = 0;
		for(int i = 1; i < c.length; i ++) {
			if(c[i] == c[i-1]) {
				count++;
			} else {
				if(count > 1) c[newLen++] = (char)(count + '0'); //char --> int in java
				c[newLen++] = c[i];
				count = 1;
			}
		}
		return new String(c, 0, newLen);
	}
}