package dp;

public class DecodeWays {

	public static int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        if(s.charAt(0) == '0') return 0;
        int len = s.length();
        int[] num = new int[len];
        num[0] = 1;
        if(len == 1) return 1;
        int temp = Integer.parseInt(s.substring(0,2));
        if(s.charAt(1) == '0') {
            num[1] = 1;
        } else {
            num[1] = temp > 10 && temp <= 26 ? 2 : 1;
        }
        for(int i = 2; i < len; i ++) {
            if(s.charAt(i-1) == '0' && s.charAt(i) == '0') {
                num[i] = 0;
            } else {
                num[i] = num[i-1];
            }
            int tmp = Integer.parseInt(s.substring(i-1, i+1));
            if(s.charAt(i-1) != '0' && s.charAt(i) != '0' && tmp > 0 && tmp <= 26) {
                num[i] += num[i-2];
            }
        }
        return num[len-1];
    }
	
	public static void main(String[] s) {
		String ss = "110";
		int result = numDecodings(ss);
		System.out.println(result);
	}
	
	public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p = head;
        ListNode q = null;
        ListNode pre = null;
        while(p != null) {
            q = p.next; //target
            if(q.val >= p.val) {
                p = p.next;
            } else {
                p.next = q.next;
                pre = head;
                if(pre.val >= q.val) {
                    q.next = pre;
                    head = q;
                } else {
                    while(pre.next != q && pre.next.val < q.val) pre = pre.next;
                    q.next = pre.next;
                    pre.next = q;
                }
            }
        }
        return head;
    }
	
}

class ListNode {
	int val;
	ListNode next;
}

