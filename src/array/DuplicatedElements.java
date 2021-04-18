package array;

import java.util.HashSet;

public class DuplicatedElements {

	//there exists only one element in a array, try to find it. 
	//space complexity is O(n)
	public int findOnlyRepeatedElement(int[] num) {
		int diff = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int e: num) {
			if(set.contains(e)) {
				diff = e;
				break;
			}
			set.add(e);
		}
		return diff;
	}

	public static void main(String[] args) {
		DuplicatedElements test = new DuplicatedElements();
		int[] nums = {89,67,2,56,17,28,3,9,12,2};
		int sum = test.findOnlyRepeatedElement(nums);
		System.out.println(sum);
	}

}