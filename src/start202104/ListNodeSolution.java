package start202104;

public class ListNodeSolution {

	/**
	 * https://leetcode-cn.com/problems/reorder-list/
	 * 重排链表
	 * 
	 * 类似题目：https://mp.weixin.qq.com/s/377FfqvpY8NwMInhpoDgsw
	 * 链表，奇数位置按序增长，偶数位置按序递减，如何能实现链表从小到大？（2020.10 字节跳动-后端）[2]
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
	
	
	/**
	 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
	 * K个一组反转链表
	 * 
	 * 时间复杂度为 O(n*K)，最好的情况为 O(n)，最差的情况未 O(n^2)
	 * 空间复杂度为 O(1)，除了几个必须的节点指针外，没有占用其他空间
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || head.next == null) return head;
		
		//设置辅助节点
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode start = dummy;
		ListNode pre = dummy;
		ListNode end = dummy;
		
		//循环k，每次找到[pre, start, end]节点，针对[start, end]之间进行反转，pre为start前面一个节点
		while(end.next != null) {
			for(int i = 0; i < k && end != null; i++) end = end.next;
			//end==null说明不足k个节点，则跳出循环
			if(end == null) break;
			
			ListNode next = end.next;
			
			start = pre.next;
			end.next = null; //将end.next设置成null，便于reverse反转链表
			
			pre.next = reverse(start); 
			start.next = next; //reverse之后start为最后一个节点，将start链接到next之前
			end = start;
			pre = start;
		}
		
		return dummy.next;
    }
	
	public static void main(String[] args) {
		ListNodeSolution test = new ListNodeSolution();
		int[] nums = {1,2,3,4,5};
		ListNode node1 = ListNodeUtils.makeList(nums);
		test.reorderList(node1);
	}
	 
}
