package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindKthSmallest {

	public int findKth(int[] num, int k) {
		return quickSort(num, 0, num.length-1, k);
	}

	public int quickSort(int[] num, int start, int end, int k) {
		if(start < end) {
            int pivot = partition(num, start, end);
            if(pivot == k-1) {
            	return num[pivot];
            } else if(pivot > k-1) {
            	return quickSort(num, start, pivot-1, k);
            } else {
            	return quickSort(num, pivot+1, end, k);
            }
        }
		return -1;
	}

	public int partition(int[] num, int start, int end) {
		int key = num[end];
        int i = start-1;
        for(int j = start; j < end; j++) {
            if(num[j] >= key) {
                i++;
                exchange(num, i, j);
            }
        }
        exchange(num, i+1, end);
        return i+1;
	}

	public int kthLargestElement(int k, ArrayList<Integer> numbers) {
        int len = numbers.size();
        if(k > len) return -1;
        int[] nums = new int[len];
        int i = 0;
        for(int num: numbers) {
            nums[i++] = num;
        }
        return findKth(nums, 0, nums.length-1, k);
    }
    
    public int findKth(int[] nums, int start, int end, int k) {
        int povit = partition(nums, start, end);
        if(povit == k-1) return nums[k-1];
        if(povit < k-1) 
            return findKth(nums, povit+1, end, k);
        else
            return findKth(nums, start, povit-1, k);
    }

	
	public void exchange(int[] num, int i, int j) {
		int tmp = num[i];
		num[i] = num[j];
		num[j] = tmp;
	}

	public static void main(String[] args) {
		FindKthSmallest test = new FindKthSmallest();
		// int[] num = {5,6,7,1,2,3,12,-8};
		// int k = 3;
		// int result = test.findKth(num, k);
		// System.out.println(result);
		// int[] nums = {1, 5, 4, 2, 7, 2, 6, 5};
		// int len = test.removeDuplicates(nums);
		// for(int i = 0; i < len; i ++) {
		// 	System.out.print(nums[i] + ",");
		// }
		int k = 10;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int[] nums = {1,2,3,4,5,6,8,9,10,7};
		for(int e: nums) {
			numbers.add(e);
		}
		int num = test.kthLargestElement(k, numbers);
		System.out.println(num);
	}

	// 当有N个人时，M%N数需要出局 
	// 对于上一轮出局小朋友的下一位开始，即lastIndexStart， 这一轮要出局的编号为(lastIndexStart+M)%i
	// 假设d[i] 表示有i个小朋友时报M退出最后一个小朋友的编号，则会有d[i] = (d[i-1]+m)%i
	// 每一次计算只需记录上一次的lastIndexStart
	public int getLastNum(int N, int M) {
		int lastIndexStart = 0;
		for(int i = 1; i <= N; i ++) {
			lastIndexStart = (lastIndexStart + M)%i;
		}
		return lastIndexStart;
	}

	public int removeDuplicates(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		if(nums.length == 0) return 0;
		int len = 0;
		for(int i = 0; i < nums.length; i ++) {
			if(!set.contains(nums[i])) {
				nums[len++] = nums[i];
				set.add(nums[i]);
			}
		}
		return len;
	}
 
}