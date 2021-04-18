package array;

import java.util.Arrays;
import java.util.Random;

public class Solution {

	private static final int SIZE = 10;
	private int[] heap = new int[SIZE];


	public int[] findTopOneHandred(int[] nums) {
		for(int i = 0; i < SIZE; i ++) {
			heap[i] = nums[i];
		}
		buildHeap();
		for(int i = SIZE; i < nums.length; i ++) {
			int root = heap[0];
			if(root < nums[i]) {
				heap[0] = nums[i];
				minHeapify(0);
			}
		}
		return heap;
	}

	public void buildHeap() {
		for(int i = SIZE/2-1; i >= 0; i --) {
			minHeapify(i);
		}
	}

	 public void minHeapify(int k) {
	 	int tmp = heap[k];
	 	int child;
	 	for(; 2*k < SIZE; k = child) {
	 		child = 2*k;
	 		if(child != SIZE-1 && heap[child] > heap[child+1]) 
	 			child++;
	 		if(tmp > heap[child])
	 			heap[k] = heap[child];
	 		else
	 			break;
	 	}
	 	heap[k] = tmp;
	 }

	private void maxHeapify(int[] array, int index) { 
        int l = index * 2; 
        int r = l + 1; 
        int largest; 
        
        if (l <= SIZE && array[l-1] < array[index-1]) { 
            largest = l; 
        } else { 
            largest = index; 
        } 
        
        if (r <= SIZE && array[r-1] < array[largest-1]) { 
            largest = r; 
        } 
        
        if (largest != index) { 
            int temp = array[index-1]; 
            array[index-1] = array[largest-1]; 
            array[largest-1] = temp; 
            maxHeapify(array,largest); 
        } 
    } 

	public static void main(String[] args) {
		FindBiggest test = new FindBiggest();
		int[] nums = new int[1000];
		Random rand = new Random();
		for(int i = 0; i < nums.length; i ++) {
			int e = rand.nextInt(2000);
			nums[i] = e;
			System.out.print(e + ",");
		}
		System.out.println();
		int[] heap = test.findTopOneHandred(nums);
		Arrays.sort(heap);
		int k = 9;
		for(int i = 1; i <= 10; i ++) {
//			for(int j = 0; j < 10; j ++) {
//				
//			}
			System.out.print(heap[k--] + ",");
//			System.out.println();
		}
		System.out.println();
		Arrays.sort(nums);
		k = nums.length-1;
		for(int i = 0; i < 10; i ++) {
			System.out.print( nums[k--] + ",");
		}
	}
}