package array;

class SortedList {

	int value;
	SortedList next;
	SortedList(int value) {
		this.value = value;
	}
}

public class MergeTwoLists {

// L1: 1 3 5 7
// L2: 2 3 5 8

// L1 merge L2: 1 2 3 5 7 8 

	public SortedList removeDuplicated(SortedList A) {
		SortedList p = A;
		while(p != null && p.next != null) {
			if(p.next.value != p.value) {
				p = p.next;
			} else {
				p.next = p.next.next;
			}
		}
		return A;
	}

	public SortedList merge(SortedList A, SortedList B) {
		if(A == null) return removeDuplicated(B);
		if(B == null) return removeDuplicated(A);
	    SortedList p1 = A;
	    SortedList p2 = B;
	    
	    SortedList p = null;
	    // if(A == null) {
	    // 	p = p2;
	    // } else if(B == null) {
	    // 	p = p1;
	    // } else {
	    // 	if(A.value <= B.value) {
		   //      p = p1;
		   //      p1 = p1.next;
		   //  } else {
		   //      p = p2;
		   //      p2 = p2.next;
		   //  }
	    // }

	    if(A.value <= B.value) {
			p = p1;
	        p1 = p1.next;
	    } else {
	        p = p2;
	        p2 = p2.next;
	    }
	    
	    SortedList head = p;   ///Case: L1: 1 1 1 1  L2: 1 Case: L1: null L2: 1 1 2 3
	    SortedList tmp = null;
	    while(p1 != null && p2 != null) {
	        if(p1.value <= p2.value) {         
	            tmp = p1;
	            p1 = p1.next;
	            
	        } else {
	            tmp = p2;
	            p2 = p2.next;
	        }
	        if(tmp.value != p.value) {
	            p.next = tmp;
	            p = p.next;
	        }
	    }
	   
	    tmp = p1 == null ? p2 : p1;
	    while(tmp != null) {
	        if(tmp.value != p.value) {
	        	p.next = tmp;
	        	p = p.next;
	        }
	        tmp = tmp.next;
	    }

	    //attention!!! //Case: L1: 1 1 1 1  L2: 1
	    if(p != null) p.next = null;
	    return head;
	}

	public static void main(String[] args) {
		MergeTwoLists test = new MergeTwoLists();
		SortedList A = new SortedList(1);
		SortedList pA = A;
		for(int i = 2; i < 5; i ++) {
			pA.next = new SortedList(i);
			pA = pA.next;
			if(i == 2) {
				pA.next = new SortedList(i);
				pA = pA.next;
				pA.next = new SortedList(i);
				pA = pA.next;
			}
		}
		print(A);
		SortedList B = new SortedList(3);
		SortedList pB = B;
		for(int i = 5; i < 7; i ++) {
			pB.next = new SortedList(i);
			pB = pB.next;
			if(i == 6) {
				pB.next = new SortedList(i);
				pB = pB.next;
				pB.next = new SortedList(i);
				pB = pB.next;
			}
		}
		print(B);
		SortedList C = test.merge(null, B);
		print(C);
//		SortedList D = test.merge(null, null);
//		print(D);
	}
	
	public static void print(SortedList head) {
		SortedList p = head;
		while(p != null) {
			System.out.print(p.value + " --> ");
			p = p.next;
		}
		System.out.println();
	}
}