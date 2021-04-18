package abc;

public class RemoveElements {

	public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        ListNode p = head;
        ListNode pre = null;
        while(p != null) {
            if(p.val == val) {
                while(p != null && p.val == val) {
                    p = p.next;
                }
                if(pre == null) {
                    head = p;
                } else {
                    pre.next = p;
                }
            } else {
                pre = p;
                p = p.next;
            }
        }
        return head;
    }
	
	public static void main(String[] args) {
		RemoveElements test = new RemoveElements();
		ListNode head = new ListNode(7);
		ListNode result = test.removeElements(head, 7);
		System.out.println(result.val);
	}
	
}
