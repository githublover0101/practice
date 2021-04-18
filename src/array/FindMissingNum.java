package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMissingNum {

	// 0-100, find the missing one
	// time complexity is O(n), and space complexity is O(1)
	public int findMissing(int[] num) {
		int sum1 = 0;
		int sum2 = 0;
		for(int i = 0; i < 99; i ++) {
			sum1 += num[i];
			sum2 += (i+1);
		}
		sum2 += 100;
		return sum2 - sum1;
	}

	public static void main(String[] args) {
		FindMissingNum test = new FindMissingNum();
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= 100; i ++) {
			list.add(i);
		}
		Collections.shuffle(list);
		list.remove(8);
		int[] num = new int[99];
		int i = 0;
		for(int e: list) {
			num[i++] = e;
		}
		int missing = test.findMissing(num);
		System.out.println(missing);
	}
}