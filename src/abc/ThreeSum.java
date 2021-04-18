package abc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> subList = null;
        for(int i = 0; i < nums.length-2; i++) {
            int j = i+1;
            int k = nums.length-1;
            while(j < k) {
                if(nums[i] + nums[j] + nums[k] == 0) {
                	subList = new ArrayList<Integer>();
                    subList.add(nums[i]);
                    subList.add(nums[j]);
                    subList.add(nums[k]);
                    if(!list.contains(subList)) list.add(subList);
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j-1]) j++; 
                    while(j < k && nums[k] == nums[k+1]) k--; 
                } else if(nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
            // while(i < nums.length-2 && nums[i] == nums[i-1]) i++;
        }
        return list;
    }
	
	public static void main(String[] args) {
		ThreeSum test = new ThreeSum();
		int[] nums = {-13,11,11,0,-5,-14,12,-11,-11,-14,-3,0,-3,12,-1,-9,-5,-13,9,-7,-2,9,-1,4,-6,-13,-7,10,10,9,7,13,5,4,-2,7,5,-13,11,10,-12,-14,-5,-8,13,2,-2,-14,4,-8,-6,-13,9,8,6,10,2,6,5,-10,0,-11,-12,12,8,-7,-4,-9,-13,-7,8,12,-14,10,-10,14,-3,3,-15,-14,3,-14,10,-11,1,1,14,-11,14,4,-6,-1,0,-11,-12,-14,-11,0,14,-9,0,7,-12,1,-6};
		List<List<Integer>> result = test.threeSum(nums);
		for(List<Integer> subList: result) {
			for(Integer e: subList) {
				System.out.print(e + ",");
			}
			System.out.println();
		}
		
		char c = 'Z';
		System.out.println((int)c);
	}
	
}
