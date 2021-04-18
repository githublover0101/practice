package list;

class ListNode {
	int val;
	ListNode next = null;
	
	ListNode(int val) {
		this.val = val;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(val);
		ListNode p = next;
		while(p != null) {
			sb.append(" -> ").append(p.val);
			p = p.next;
		}
		return sb.toString();
	}
}

public class SortList {

	public ListNode sortList(ListNode head) {  
		//first break the list into two parts
		//merge sort the first part, and second part respectively
		//merge the two parts result
		if(head == null || head.next == null) return head;
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null) {
			slow = slow.next;
			fast = fast.next;
			if(fast != null) {
				fast = fast.next;
			}
		}
		ListNode head1 = head;
		ListNode head2 = slow.next;
		slow.next = null;
		head1 = sortList(head1);
		head2 = sortList(head2);
		return merge(head1, head2);
	}

	public ListNode merge(ListNode head1, ListNode head2) {
		if(head1 == null) return head2;
		if(head2 == null) return head1;

		ListNode p1 = head1;
		ListNode p2 = head2;
		ListNode head = null;
		ListNode p = null;
		if(p1.val < p2.val) {
			p = p1;
			p1 = p1.next;
		} else {
			p = p2;
			p2 = p2.next;
		}
		head = p;
		while(p1 != null && p2 != null) {
			if(p1.val < p2.val) {
				p.next = p1;
				p1 = p1.next;
			} else {
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		while(p1 != null) {
			p.next = p1;
			p1 = p1.next;
			p = p.next;
		}
		while(p2 != null) {
			p.next = p2;
			p2 = p2.next;
			p = p.next;
		}
		if(p != null) p.next = null;
		return head;
	}

	public static void main(String[] args) {
		SortList test = new SortList();
		ListNode head = new ListNode(1);
		head.next = new ListNode(-1);
		ListNode p = test.sortList(head);
		System.out.println(p);
	}

}