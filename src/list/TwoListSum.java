package list;

public class TwoListSum {
	public ListNode addLists(ListNode l1, ListNode l2) {
        //reverse l1 & l2
        //do add 
        //reverse the result list
        ListNode head1 = reverse(l1);
        ListNode head2 = reverse(l2);
        ListNode head = add(head1, head2);
        return reverse(head);
    }

    public ListNode add(ListNode head1, ListNode head2) {
        if(head1 == null) return head2;
        if(head2 == null) return head1;
        int computor = 0;
        ListNode p = head1;
        ListNode q = head2;
        ListNode head = null;
        ListNode tmp = null;
        int sum = 0;
        while(p != null || q != null) {
            int num1 = p == null ? 0 : p.val;
            int num2 = q == null ? 0 : q.val;
            if(p != null) p = p.next;
            if(q != null) q = q.next;
            
            sum = num1 + num2 + computor;
            if(sum >= 10) {
                computor = 1;
                sum = sum - 10;
            } else {
                computor = 0;
            }
            if(head == null) {
                head = new ListNode(sum);
                tmp = head;
            } else {
                tmp.next = new ListNode(sum);
                tmp = tmp.next;
            }
        }

        if(computor == 1) tmp.next = new ListNode(1);
        
        return head;
    }
    
    public ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode p = null;
        while(pre.next != null) {
            p = pre.next;
            pre.next = p.next;
            p.next = head;
            head = p;
        }
        return head;
    }

    public static void main(String[] args) {
    	TwoListSum test = new TwoListSum();
    	ListNode l1 = new ListNode();
    	ListNode l2 = new ListNode();
    	ListNode head = test.addLists(l1, l2);
    	System.out.println(head);
    }
}