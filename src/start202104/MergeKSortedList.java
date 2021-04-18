package start202104;

public class MergeKSortedList {

	/**
	 * https://leetcode.com/problems/merge-k-sorted-lists/
	 * 23. 合并K个排序链表
	 * 
	 * K个有序链表，每次分割一半，总共需要分割logK次，
	 * 而每一轮merging的时间复杂度为O(n)，注意这里的n是所有链表的节点总数，因为每一次merging所有的节点都需要比较一次，可参考上述分治合并过程的图片。
	 * 则时间复杂度求和为：
	 * 	logK * O(n) = O(n* logK)
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists.length == 0) return null;
		return mergeKLists(lists, 0, lists.length-1);
    }
	
	public ListNode mergeKLists(ListNode[] lists, int left, int right) {
		if(left < right) {
			int mid = (left + right)/2;
			ListNode node1 = mergeKLists(lists, left, mid);
			ListNode node2 = mergeKLists(lists, mid+1, right);
			return mergeList(node1, node2);
		}
		return lists[left];
	}
	
	private ListNode mergeList(ListNode node1, ListNode node2) {
		if(node1 == null) return node2;
		if(node2 == null) return node1;
		
		ListNode p1 = node1;
		ListNode p2 = node2;
		ListNode head = new ListNode();
		ListNode p = head;
		
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
		return head.next;
	}
	
}
