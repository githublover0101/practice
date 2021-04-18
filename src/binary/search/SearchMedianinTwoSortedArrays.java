package binary.search;

public class SearchMedianTwoSortedArrays {

	public int searchMedian(int[] array1, int[] array2) {
		int len1 = array1.length;
		int len2 = array2.length;
		int[] array = new int[len1+len2];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < len1 && j < len2) {
			if(array1[i] < array2[j]) {
				array[k] = array1[i];
				i++;
			} else {
				array[k] = array2[j];
				j++;
			}
			k++;
		}
		while(i < len1) array[k++] = array1[i++];
		while(j < len2) 
	}
	
}
