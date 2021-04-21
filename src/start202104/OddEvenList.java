package start202104;

public class OddEvenList {

	public static void main(String[] args) {
		OddEvenList test = new OddEvenList();
		int[] nums = {1,2,3,4,5};
		ListNode node = ListNodeUtils.makeList(nums);
		ListNodeUtils.print(node);
		
		ListNodeUtils.print(test.reverseKGroup(node, 3));
	}
	
	/**
	 * 头条面试题
	 * 一个链表奇数位上升序，偶数位上降序，不用额外空间让这个链表整体升序
		1->8->3->6->5->4->7->2->9
		
		题解：
		这道题可以分成三步：
		
		1）根据奇数位和偶数位拆分成两个链表。
		2）对偶数链表进行反转。
		3）将两个有序链表进行合并。
	 * @param node
	 * @return
	 */
	public ListNode sortOddEvenList(ListNode node) {
		if(node == null || node.next == null) return node;
		ListNode oddNode = new ListNode();
		ListNode evenNode = new ListNode();
		ListNode p1 = oddNode;
		ListNode p2 = evenNode;
		
		
		//遍历链表，通过单双数index来识别奇数还是偶数pointer		
		int index = 1;
		ListNode cur = node;
		while(cur != null) {
			if(index % 2 != 0) { //奇数位
				p1.next = cur;
				p1 = p1.next;
			} else {
				p2.next = cur;
				p2 = p2.next;
			}
			index++;
			cur = cur.next;
		}
		p1.next = null;
		p2.next = null;

		ListNode head1 = oddNode.next;
		ListNode head2 = evenNode.next;
		
		ListNodeUtils.print(head1);
		ListNodeUtils.print(head2);
		
		head2 = reverse(head2);
		return mergeList(head1, head2);
	}
	
	private ListNode reverse(ListNode node) {
		if(node == null || node.next == null) return node;
		ListNode pre = node;
		ListNode p = node;
		ListNode cur = null;
		while(p.next != null) {
			cur = p.next;
			p.next = p.next.next;
			cur.next = pre;
			pre = cur;
		}
		return pre;
	}
	
	private ListNode mergeList(ListNode node1, ListNode node2) {
		if(node1 == null) return node2;
		if(node2 == null) return node1;
		
		ListNode p1 = node1;
		ListNode p2 = node2;
		ListNode head = null;
		ListNode p = null;
		
		if(p1.val < p2.val) {
			head = p1;
			p1 = p1.next;
		} else {
			head = p2;
			p2 = p2.next;
		}
		p = head;
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
		
		if(p1 != null) {
			p.next = p1;
		}
		if(p2 != null) {
			p.next = p2;
		}
		return head;
	}
	
	public ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || head.next == null) return head;
		int len = 0;
		ListNode p = head;
		while(p != null) {
			len++;
			p = p.next;
		}
		int total = len/k;
		ListNode dummy = new ListNode();
		dummy.next = head;
		
		ListNode pre = dummy;
		ListNode left = null;
		int count = 0;
		while(count < total) {
			left = pre.next;
			int index = 1;
			p = left;
			while(index < k) {
				ListNode cur = p.next;
				p.next = p.next.next;
				cur.next = pre.next;
				pre.next = cur;
				
				p = p.next;
				index++;
			}
			pre = left;
			count++;
		}
		
		return dummy.next;
    }
	
}
