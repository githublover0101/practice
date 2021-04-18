package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KSum {

	public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
        //non-descending order
        //no duplicate triplets
        ArrayList<ArrayList<Integer>> allList = new ArrayList<ArrayList<Integer>>();
        int len = numbers.length;
        if(len < 3) return allList;
        ArrayList<Integer> subList = null;
        Arrays.sort(numbers);
        for(int i = 0; i < len-2; i++) {
            while(i > 0 && i < len-2 && numbers[i] == numbers[i-1]) i++;
            int j = i+1;
            // while(j < len-1 && numbers[j] == numbers[i]) j++; //skip duplicate elements
            int k = len-1;
            while(j < k) {
                if(numbers[i] + numbers[j] + numbers[k] == 0) {
                    subList = new ArrayList<Integer>();
                    subList.add(numbers[i]);
                    subList.add(numbers[j]);
                    subList.add(numbers[k]);
                    allList.add(subList);
                    j++;
                    k--;
                    while(j < k && numbers[j] == numbers[j-1]) j++; //skip duplicate elements
                    while(j < k && numbers[k] == numbers[k+1]) k--; //skip duplicate elements
                } else if(numbers[i] + numbers[j] + numbers[k] > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return allList;
    }

    public int threeSumClosest(int[] numbers ,int target) {
        // ArrayList<ArrayList<Integer>> allList = new ArrayList<ArrayList<Integer>>();
        int len = numbers.length;
        // ArrayList<Integer> subList = null;
        Arrays.sort(numbers);
        int minGap = Integer.MAX_VALUE;
        int minSum = 0;
        for(int i = 0; i < len-2; i ++) {
            //notice this, skip duplicate elements
            // while(i > 0 && i < len-2 && numbers[i] == numbers[i-1]) i++;
            int j = i+1;
            int k = len-1;
            while(j < k) {
                int curGap = numbers[i] + numbers[j] + numbers[k] - target;
                if(Math.abs(curGap) < minGap) {
                    minGap = Math.abs(curGap);
                    minSum = numbers[i] + numbers[j] + numbers[k];
                    // j ++;
//                    j++;
                    // while(j < k && numbers[j] == numbers[j-1]) j++; //skip duplicate elements
                    // while(j < k && numbers[k] == numbers[k+1]) k--; //skip duplicate elements
                // } else if(numbers[i] + numbers[j] + numbers[k] > target) {
                //     k--;
                } 
                if(numbers[i] + numbers[j] + numbers[k] > target) {
                	k--;
                } else {
                    j++;
                }
            }
        }
        return minSum;
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {     
        ArrayList<ArrayList<Integer>> allList = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(numbers);
        ArrayList<Integer> subList = null;
        
        //key is num, and value is the count of the num
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < numbers.length-1; i ++) {
            for(int j = i+1; j < numbers.length; j ++) {
                int sum = numbers[i]+numbers[j];
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                list.add(j);
                map.put(sum, list);
            }
        }
        
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            int anotherSum = target - entry.getKey();
            if(map.containsKey(anotherSum)) {
                List<Integer> indexes1 = entry.getValue();
                List<Integer> indexes2 = map.get(anotherSum);
                int index1 = indexes1.get(0);
                int index2 = indexes1.get(1);
                int index3 = indexes2.get(0);
                int index4 = indexes2.get(1);
                if(index1 != index3 && index1 != index4 && index2 != index3 && index2 != index4) {
                    subList = new ArrayList<Integer>();
                    subList.add(numbers[index1]);
                    subList.add(numbers[index2]);
                    subList.add(numbers[index3]);
                    subList.add(numbers[index4]);
                    allList.add(subList);
                }
            }
        }
        return allList;
    }

    //Google phone interview, time complexity is O(n)
    //iterative
    public ListNode reverseListNode(ListNode head) {
        // pre = head, p = pre.next; p.next = head, head = p
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode p = null;
        while(pre.next != null) {
            p = pre.next;
            pre.next = p.next;
            p.next = head;
            head = p;
        }
        return head;
    }

    public ListNode reverseListNode(ListNode head) {
        // pre = head, p = pre.next; p.next = head, head = p
        if(head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode p = null;
        while(pre.next != null) {
            p = pre.next;
            pre.next = p.next;
            p.next = head;
            head = p;
        }
        return head;
    }

    //Google phone interview, time complexity is O(nlgn)
    public int getTripletCountBy3Sum(int[] nums, int target) {
        // should check whether duplicated elements or not
        Arrays.sort(nums); //O(nlgn)
        int count = 0;
        int len = nums.length;

        //O(n^2)
        for(int i = 0; i < len; i ++) {
            while(i > 0 && i < len && nums[i] == nums[i-1]) i++; //skip duplicated elements
            int j = i+1;
            int k = len-1;
            while(j < k) {
                if(nums[i] + nums[j] + nums[k] == target) {
                    count++;
                    j++;
                    k--;
                    while(j < k && nums[j] == nums[j-1]) j++;
                    while(j < k && nums[k] == nums[k+1]) k--;
                } else if(nums[i] + nums[j] + nums[k] < target) {
                    count++;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
    	KSum test = new KSum();
    	int[] numbers = {1,0,-1,-1,-1,-1,0,1,1,1,2};
    	// ArrayList<ArrayList<Integer>> result = test.threeSum(numbers);
    	// System.out.println(result);
        int target = 2;
        ArrayList<ArrayList<Integer>> result = test.fourSum(numbers, target);
        System.out.println(result);
    }
}