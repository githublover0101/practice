package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Google {

	// google interview
	// Given a 2D array that describes a map (0 = water, 1 = land), 
	// write an algorithm that counts the number of distinct islands 
	// (two pieces of land touch if they are next to each other vertically or horizontally, not diagonally).
	// time complexity is O(mn)
	public int countIslands(int[][] nums) {
		int count = 0;
		int m = nums.length;
		if(m == 0) return 0; 
		int n = nums[0].length;
		if(n == 0) return 0; 
		for(int i = 0; i < m; i ++) {
			for(int j = 0; j < n; j ++) {
				if(nums[i][j] == 1 && check(nums, i, j)) {
					count++;
				}
			}
		}
		return count;
	}

	// nums[i-1][j], nums[i][j-1], nums[i+1][j], nums[i][j+1]
	public boolean check(int[][] nums, int i, int j) {
		int m = nums.length;
		int n = nums[0].length;
		boolean result = true;
		result  = result && (i == 0 || nums[i-1][j] == 0);
		result  = result && (i == m-1 || nums[i+1][j] == 0);
		result  = result && (j == 0 || nums[i][j-1] == 0);
		result  = result && (j == n-1 || nums[i][j+1] == 0);
		return result;
	}

	// Find intersection of two sorted array A, B.
	public List<Integer> findIntersection(int[] A, int[] B) {
		// A[i] < B[j], i++
		// A[i] > B[j], j++
		// A[i] == B[j], i++, j++, check duplicates
		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		int j = 0;
		while(i < A.length && j < B.length) {
			if(A[i] < B[j]) {
				i++;
			} else if(A[i] > B[j]) {
				j++;
			} else {
				if(list.size() == 0 || A[i] != list.get(list.size()-1)) {
					list.add(A[i]);
				}
				i++;
				j++;
			}
		}
		return list;
	}

	public boolean checkSudoku(int[][] nums) {
		// check each row
		// check each column
		// check each little 3*3
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < 9; i ++) {
			set.clear();
			for(int j = 0; j < 9; j ++) {
				if(set.contains(nums[i][j])) {
					return false;
				}
				set.add(nums[i][j]);
			}
		}

		for(int j = 0; j < 9; j ++) {
			set.clear();
			for(int i = 0; i < 9; i ++) {
				if(set.contains(nums[i][j])) {
					return false;
				}
				set.add(nums[i][j]);
			}
		}

		for(int i = 1; i < 9; i = i+3) {
			for(int j = 1; j < 9; j =j+3) {
				set.clear();
				if(checkDuplicates(nums, i, j, set)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkDuplicates(int[][] nums, int i, int j, HashSet<Integer> set) {
		set.add(nums[i][j]);
		if(set.contains(nums[i][j-1])) {
			return true;
		} else {
			set.add(nums[i][j-1]);
		}

		if(set.contains(nums[i][j+1])) {
			return true;
		} else {
			set.add(nums[i][j+1]);
		}

		if(set.contains(nums[i-1][j-1])) {
			return true;
		} else {
			set.add(nums[i-1][j-1]);
		}

		if(set.contains(nums[i-1][j])) {
			return true;
		} else {
			set.add(nums[i-1][j]);
		}

		if(set.contains(nums[i-1][j+1])) {
			return true;
		} else {
			set.add(nums[i-1][j+1]);
		}

		if(set.contains(nums[i+1][j-1])) {
			return true;
		} else {
			set.add(nums[i+1][j-1]);
		}

		if(set.contains(nums[i+1][j])) {
			return true;
		} else {
			set.add(nums[i+1][j]);
		}

		if(set.contains(nums[i+1][j+1])) {
			return true;
		} else {
			set.add(nums[i+1][j+1]);
		}
		return false;
	}

	public int findPeak(int[] nums) {
		return findPeak(nums, 0, nums.length-1);
	}

	public int findPeak(int[] nums, int start, int end) {
		if(start < end) {
			int mid = start + (end - start)/2;
			if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) return nums[mid];
			if(nums[mid] < nums[mid+1]) 
				return findPeak(nums, mid+1, end);
			else
				return findPeak(nums, start, mid-1);  
		}
		return nums[start];
	}

	public static void main(String[] args) {
		Google test = new Google();
		int[][] nums = {
			{0,1,0,0,1},
			{1,0,1,1,0},
			{0,0,0,1,0},
			{1,0,1,0,0},
			{1,0,0,0,1}
		};
		int count = test.countIslands(nums);
		System.out.println(count);

		// find intersections
		int[] A = {1,3,3,6,8,9,10,11,11,11,11,19};
		int[] B = {2,3,5,7,8,10,11,11,18,19,20};
		List<Integer> list = test.findIntersection(A, B);
		System.out.println(list);

		int[] elems = {1,2,3,9,6,5};
		int peak = test.findPeak(elems);
		System.out.println(peak);
	}
}