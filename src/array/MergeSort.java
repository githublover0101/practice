package array;

public class MergeSort {

	public void mergeSort(int[] num, int start, int end) {
        if(start < end) {
            int mid = start + (end - start)/2;
            mergeSort(num, start, mid);
            mergeSort(num, mid+1, end);
            merge(num, start, mid, end);
        }
    }
    
    public void merge(int[] num, int start, int mid, int end) {
        int len1 = mid - start + 1;
        int len2 = end - mid;
        
        int[] A1 = new int[len1];
        int[] A2 = new int[len2];
        
        for(int i = start; i <= mid; i++) {
            A1[i-start] = num[i];
        }
        
        for(int i = mid+1; i <= end; i++) {
            A2[i-mid-1] = num[i];
        }
        
        int i = 0;
        int j = 0;
        int index = start;
        while(i < len1 && j < len2) {
            if(A1[i] < A2[j]) {
                num[index++] = A1[i++];
            } else {
                num[index++] = A2[j++];
            }
        }
        if(i < len1) {
            while(i < len1) num[index++] = A1[i++];
        }
        if(j < len2) {
            while(j < len2) num[index++] = A1[j++];
        }
    }
    
    public static void main(String[] args) {
    	int[] list = new int[]{-1,0,1};
    	MergeSort test = new MergeSort();
    	test.mergeSort(list, 0, list.length-1);
    	
    	for(int e: list) {
    		System.out.print(e + ", ");
    	}
    }
}
