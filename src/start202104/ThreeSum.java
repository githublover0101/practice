package start202104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(nums.length < 3) return list;
        
        //先对整个数组进行排序
        Arrays.sort(nums);
        
        List<Integer> subList = null;
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < nums.length - 2) {
        	j = i+1;
        	k = nums.length-1;
        	while(j < k) {
        		if(nums[j] + nums[k] + nums[i] == 0) {
                    subList = new ArrayList<Integer>();
        			subList.add(nums[i]);
        			subList.add(nums[j]);
        			subList.add(nums[k]);
        			list.add(subList);
                    j++;
                    k--;
                    
                    //需要考虑去重，结果集不能重复
        			while(j < nums.length-1 && nums[j] == nums[j-1]) j++;
        			while(k >= 0 && nums[k+1] == nums[k]) k--;
        		} else if(nums[j] + nums[k] + nums[i] < 0) {
        			j++;
        		} else {
        			k--;
        		}
        	}
            i++;
            
            //需要考虑去重，结果集不能重复
            while(i < nums.length - 2 && nums[i] == nums[i-1]) i++;
        }
        return list;
        
    }
	
}
