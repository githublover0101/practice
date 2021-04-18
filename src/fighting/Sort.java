
public class Sort {

	// quick sort, O(nlgn)
	public static int partition(int[] array, int left, int right) {
		int key = array[right];
		int i = left - 1;
		int j = left;
		for(; j < right; j ++) {
			if(array[j] <= key) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i+1, right);
		return i+1;
	}

	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void quickSort(int[] array, int left, int right) {
		if(left < right) {
			int poivt = partition(array, left, right);
			quickSort(array, left, poivt-1);
			quickSort(array, poivt+1, right);
		}
	}


	// insert sort, O(n^2)
	public static void insertSort(int[] array) {
		for(int j = 1; j < array.length; j ++) {
			int key = array[j];
			int i = j-1;
			while(i >= 0 && array[i] > key) {
				array[i+1] = array[i];
				i--;
			}
			array[i+1] = key;
		}
	}

	// merge sort
	public static void mergeSort(int[] array) {

	}

	public static int[] merge(int[] array1, int array2) {

	}

	// bubble sort
}