public class TwoSum {

	// use a array to store the elements, keep the array ascending
	// time complexity: add is O(n), find is O(n)
	// space complexity is O(n)
	private DoubleLinkedNode head;
	private DoubleLinkedNode end;

	public TwoSum() {
		head = new DoubleLinkedNode(-1);
		end = new DoubleLinkedNode(-1);
		head.next = end;
		end.pre = head;
	}

	// O(n), use insert order
	public void add(int value) {
		DoubleLinkedNode curr = head.next;
		while(curr != end && curr.val < value) {
			curr = curr.next;
		}
		DoubleLinkedNode newNode = new DoubleLinkedNode(value);
		newNode.next = curr;
		newNode.pre = curr.pre;
		curr.pre = newNode;
	}

	// O(n), 
	public boolean find(int value) {
		DoubleLinkedNode p = head.next;
		DoubleLinkedNode q = end.pre;
		while(p != q) {
			if(p.val + q.val == value) {
				return true;
			}
			if(p.val + q.val > value) {
				q = q.pre;
			} else {
				p = p.next;
			}
		}
		return false;
	}

	public int[] twoSum(int[] numbers, int target) {

	}

	//the runtime complexity is O(n)
	public int[] twoSumII(int[] numbers, int target) {
		int i = 0;
		int j = numbers.length-1;
		while(i < j) {
			if(numbers[i] + numbers[j] == target) {
				return new int[]{numbers[i], numbers[j]};
			} 
			if(numbers[i] + numbers[j] > target) {
				j--;
			} else {
				i++;
			}
		}
		return new int[]{-1, -1};
	}

	class DoubleLinkedNode {
		int val;
		DoubleLinkedNode pre;
		DoubleLinkedNode next;
		public DoubleLinkedNode(int val) {
			this.val = val;
		}
	}

}