package start202104;

public class ListNodeSolution {

	/**
	 * https://leetcode-cn.com/problems/reorder-list/
	 * 重排链表
	 * 
	 * @param head
	 */
	public void reorderList(ListNode head) {
		//1.拆分成两个链表，前半部分和后半部分
		//2.对后半部分链表，进行反转
		//3.合并前半链表+反转后的后半链表
		
		if(head == null || head.next == null) return;
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		
		ListNode first = head;
		ListNode second = slow.next;
		slow.next = null;
		
		second = reverse(second);
		ListNodeUtils.print(first);
		ListNodeUtils.print(second);
		
		mergeListNodes(first, second);
		ListNodeUtils.print(head);
    }
	
	private ListNode reverse(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode pre = head;
		ListNode p = head;
		while(p.next != null) {
			ListNode cur = p.next;
			p.next = cur.next;
			cur.next = pre;
			pre = cur;
		}
		return pre;
	}
	
	//将node2合并到node1中
	private void mergeListNodes(ListNode node1, ListNode node2) {
		if(node1 == null) return;
		if(node2 == null) return;
		ListNode p1 = node1.next;
		ListNode p2 = node2;
		ListNode p = node1;
		int index = 0;
		while(p1 != null && p2 != null) {
			if(index%2 == 0) {
				p.next = p2;
				p2 = p2.next;
			} else {
				p.next = p1;
				p1 = p1.next;
			}
			p = p.next;
			index++;
		}
		if(p1 != null) {
			p.next = p1;
		}
		if(p2 != null) {
			p.next = p2;
		}
	}
	
	
	public static void main(String[] args) {
		ListNodeSolution test = new ListNodeSolution();
		int[] nums = {1,2,3,4,5};
		ListNode node1 = ListNodeUtils.makeList(nums);
		test.reorderList(node1);
	}
	 
}
