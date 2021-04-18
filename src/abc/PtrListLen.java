package abc;

public class PtrListLen {

	public int solution(IntList L) {
		int len = 0;
		IntList p = L;
		while(p != null) {
			p = p.next;
			len++;
		}
		return len;
	}
	
}

class IntList {
	public int value;
	public IntList next;
}
