package list;

public class ReverseList {

	// the time complexity is O(n)
	public ListNode reverse(ListNode head) {
		if(head == null || head.next == null) return head;
		return reverse(head, null);
	}

	public ListNode reverse(ListNode node, ListNode previous) {
		if(node.next == null) {
			node.next = previous;
			return node;
		} else {
			ListNode next = reverse(node.next, node);
			node.next = previous;
			return next;
		}
	}

	public ListNode reverseIteratively(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode pre = head;
		ListNode p = null
		while(pre.next != null) {
			p = pre.next;
			pre.next = p.next;
			p.next = head;
			head = p;
		}
		return head;
	}
}