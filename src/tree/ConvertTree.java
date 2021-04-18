public class ConvertTree {

	public TreeNode sortedArrayToBST(int[] num) {
        return sortedArrayToBST(num, 0, num.length-1);
    }

    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
    	if(start > end) return null;
    	int mid = start + (end - start)/2;
    	if(mid%2 == 0) mid = mid + 1;
    	TreeNode root = new TreeNode(num[mid]);
    	root.left = sortedArrayToBST(num, start, mid-1);
    	root.right = sortedArrayToBST(num, mid+1, end);
    	return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
    	int len = 0;
    	ListNode p = head;
    	while(p != null) {
    		len++;
    		p = p.next;
    	}
    	return sortedListToBST(head, len);
    }

    public TreeNode sortedListToBST(ListNode head, int len) {
        if(len <= 0) return null;
    	int mid = (len+1)/2;
    	ListNode p = head;
    	int index = 1;
    	while(index < mid) {
    		p = p.next;
    		index++;
    	}
    	TreeNode root = new TreeNode(p.val);
    	root.left = sortedListToBST(head, mid-1);
    	root.right = sortedListToBST(p.next, len-mid);
    	return root;
    }

}