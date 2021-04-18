package abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OddAndEven {

	// left is odd, and right is even
	public void sortOddAndEven(int[] nums) {
		int i = 0;
		int j = nums.length-1;
		int tmp = 0;
		while(i < j) {
			if(isEven(nums[i]) && isOdd(nums[j])) {
				tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
				i++;
				j--;
			} else if(isEven(nums[i])) {
				j--;
			} else {
				i++;
			}
		}
	}
	
	public void sortOddAndEvenWithQuickSort(int[] nums) {
		int i = -1;
		int tmp = 0;
		for(int j = 0; j < nums.length; j ++) {
			if(isOdd(nums[j])) {
				i++;
				tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
			}
		}
	}
	
	private boolean isOdd(int num) {
		return num%2 != 0;
	}

	private boolean isEven(int num) {
		return num%2 == 0;
	}
	
	public void print(int[] nums) {
		for(int e: nums) {
			System.out.print(e + ",");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		OddAndEven test = new OddAndEven();
		int[] nums = {3,2,1,6,7,4,5,4,39,11,23,22,56,90};
		test.sortOddAndEvenWithQuickSort(nums);
		test.print(nums);
	}
}