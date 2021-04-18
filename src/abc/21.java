import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        List<Integer> subList = null;
        for (int i = 0; i < nums.length - 2;) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    subList = new ArrayList<Integer>();
                    subList.add(nums[i]);
                    subList.add(nums[j]);
                    subList.add(nums[k]);
                    result.add(subList);
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
            i++;
            while (i < nums.length - 2 && nums[i] == nums[i - 1])
                i++;
        }
        return result;
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        return findKthLargest(nums, left, right, k);
    }

    public int findKthLargest(int[] nums, int left, int right, int k) {
        if (left < right) {
            int pvoit = partition(nums, left, right);
            if (pvoit == k - 1)
                return nums[k - 1];
            if (pvoit > k - 1)
                findKthLargest(nums, left, pvoit - 1, k);
            else
                findKthLargest(nums, pvoit + 1, right, k);
        }
        return -1;
    }

    public int partition(int[] nums, int left, int right) {
        int i = left - 1;
        int key = nums[right];
        for (int j = 0; j < right - 1; j++) {
            if (nums[j] < key) {
                i++;
                exchange(nums, i, j);
            }
        }
        exchange(nums, i + 1, right);
        return i + 1;
    }

    public void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}