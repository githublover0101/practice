package start202104;

class ListNode {
	int val;
	ListNode next;
	
	public ListNode() {
		
	}
	
	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}

public class ListNodeUtils {

	public static ListNode makeList(int[] nums) {
		ListNode head = new ListNode();
		ListNode p = head;
		for(int i = 0; i < nums.length; i++) {
			p.next = new ListNode(nums[i]);
			p = p.next;
		}
		return head.next;
	}
	
	public static void print(ListNode node) {
		if(node == null) return;
		ListNode p = node;
		while(p != null) {
			System.out.print(p.val + ",");
			p = p.next;
		}
		System.out.println();
	}
	
}
