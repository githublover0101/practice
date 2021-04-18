package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MissingRanges {

	public List<String> getMissingRanges1(int[] ranges, int start, int end) {
		List<String> list = new ArrayList<String>();
		if(ranges.length == 0) {
			list.add(start + "->" + end);
			return list;
		}
		// start ... ranges[0]
		if(start < ranges[0]) {
			if(start + 1 == ranges[0]) {
				list.add(""+start);
			} else {
				// start -> ranges[0]-1
				list.add(start + "->" + (ranges[0]-1));
			}
		}
		for(int j = 0; j < ranges.length-1; j ++) {
			if(ranges[j] + 2 == ranges[j+1]) {
				// ranges[j]+1
				list.add("" + (ranges[j]+1));
			} else if(ranges[j] + 2 < ranges[j+1]) {
				// ranges[j] -> ranges[j+1]
				list.add((ranges[j] +1) + "->" + (ranges[j+1]-1));
			}	
		}
		if(ranges[ranges.length-1] < end) {
			if(ranges[ranges.length-1] + 1 == end) {
				list.add("" + end);
			} else {
				list.add((ranges[ranges.length-1]+1) + "->" + end);
			}
		}
		return list;
	}
	
	public List<String> getMissingRanges(int[] ranges, int start, int end) {
		List<String> list = new ArrayList<String>();
		
//		pre == post, do nothing
//		pre < post & pre+1 == post, do nothing
//		pre < post & pre + 2 == post, return pre+1, check [pre+1, post-1]
//		pre < post & pre + 2 < post, return "pre+1 -> post-1", check
		int pre = start-1;
		int post = 0;
		for(int i = 0; i <= ranges.length; i ++) {
			post = i == ranges.length ? end+1 : ranges[i];
			if(pre + 2 <= post) {
				list.add(checkMissing(pre+1, post-1));
			}
			pre = post;
		}
		return list;
	} 
	
	public String checkMissing(int start, int end) {
		if(start == end) {
			return "" + start;
		} else {
			return start + "-" + end;
		}
	}

	public String findMissing(int[] nums) {
		List<String> list = new ArrayList<String>();
		int pre = -1;
		int post = 0;
		for(int i = 0; i <= nums.length; i ++) {
			if(i == nums.length) {
				post = 100;
			} else {
				post = nums[i];
			}
			if(pre + 2 <= post) {
				list.add(checkMissing(pre+1, post-1));
			}
			pre = post;
		}
		StringBuffer sb = new StringBuffer();
		for(String e: list) {
			sb.append(e);
		}
		return sb.toString();
	}

	public String getMissingRangeString(int[] nums) {
		StringBuffer sb = new StringBuffer();
		int pre = -1;
		int post = 0;
		for(int i = 0; i <= nums.length; i++) {
			post = i == nums.length ? 100 : nums[i];
			if(post - pre >= 2) {
				sb.append(getRange(pre+1, post-1)).append(",");
			}
			pre = post;
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	public String getRange(int left, int right) {
		if(left == right) {
			return ""+left;
		} else {
			return left + "-" + right;
		}
	}


	public static void main(String[] args) {
		MissingRanges test = new MissingRanges();
//		int[] ranges = {0,1,3,50,75};
//		int start = 0;
//		int end = 99;
//		List<String> list = test.getMissingRanges(ranges, start, end);
//		for(String s: list) {
//			System.out.print(s + ", ");
//		}
//		System.out.println();
//		
//		int[] ranges2 = {0,1,3,50,52,75};
//		start = -9;
//		end = 100;
//		list = test.getMissingRanges(ranges2, start, end);
//		for(String s: list) {
//			System.out.print(s + ", ");
//		}
//		System.out.println();
		
		int[] nums = {0, 1, 2, 50, 52, 75};
		String result = test.getMissingRangeString(nums);
		System.out.println(result);
		
		int[] nums1 = {2, 19, 50, 52, 75};
		result = test.getMissingRangeString(nums1);
		System.out.println(result);
	}
}