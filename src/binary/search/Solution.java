package binary.search;

public class Solution {

	public int binarySearch(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;
		int mid = 0;
		while(left <= right) {
			mid = (left + right) / 2;
			if(array[mid] == key) return mid;
			if(array[mid] > key)
				right = mid - 1;
			else
				left = mid + 1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] array = {1,11,13,24,25,36,67};
		Solution test = new Solution();
		System.out.println(test.binarySearch(array, 1) == 0);
		System.out.println(test.binarySearch(array, 9) == -1);
		System.out.println(test.binarySearch(array, 32) == -1);
		System.out.println(test.binarySearch(array, 24) == 3);
	}
}
