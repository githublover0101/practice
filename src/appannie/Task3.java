package appannie;

import java.util.HashSet;
import java.util.Set;

public class Task3 {

//	expected time complexity is O(nlgn+m)
//	expected space complexity is O(n+m)
//	max weight, Y
//	max people, X
//	max floor, M	
//	A is weight, and B is floors
	
//	space complexity is O(M)
//	time complexity is O(N*M)
	public int solution(int[] A, int[] B, int M, int X, int Y) {
		if(A.length != B.length) return 0;
        int totalStops = 0;
        Set<Integer> floorNums = new HashSet<Integer>();
        int curWeight = 0;
        int curPeople = 0;
        int i = 0;
        while(i < A.length || !floorNums.isEmpty()) {
        	if(i < A.length && curWeight + A[i] <= Y && curPeople < X) {
        		floorNums.add(B[i]);
        		curWeight += A[i];
        		curPeople++;
        		i++;
        	} else {
        		int curStops = floorNums.size();
        		totalStops += curStops;
        		totalStops++;
        		curWeight = 0;
        		curPeople = 0;
        		floorNums.clear(); //takes O(M) to remove elements
        	}
        }
        return totalStops;
    }
	
	// time complexity is O(N*M)
	// space complexity is O(M)
	public int solution2(int[] A, int[] B, int M, int X, int Y) {
		if(A.length != B.length) return 0;
        int totalStops = 0;
        int[] floorNums = new int[M];
        int curWeight = 0;
        int curPeople = 0;
        int i = 0;
        while(i < A.length || hasOne(floorNums)) { //O(N)
        	if(i < A.length && curWeight + A[i] <= Y && curPeople < X) {
        		if(floorNums[i-1] != 1) 
        			floorNums[i-1] = 1;
        		curWeight += A[i];
        		curPeople++;
        		i++;
        	} else {
        		int curStops = countOne(floorNums); //O(M)
        		totalStops += curStops;
        		totalStops++;
        		curWeight = 0;
        		curPeople = 0;
        		allZero(floorNums); //takes O(M) to remove elements
        	}
        }
        return totalStops;
    }
	
	private void allZero(int[] nums) {
		for(int i = 0; i < nums.length; i++) {
			nums[i] = 0;
		}
	}
	
	private int countOne(int[] nums) {
		int count = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 1) 
				count++;
		}
		return count;
	}
	
	private boolean hasOne(int[] nums) {
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 1) 
				return true;
		}
		return false;
	}
	
//	expected time complexity is O(nlgn+m)
//	expected space complexity is O(n+m)
	
//	sort A or B
//	count sums[]
	public int solution3(int[] A, int[] B, int M, int X, int Y) {
		if(A.length != B.length) return 0;
        int[] sums = new int[M];
//      how to sort A or B to solve this problem
        return 1;
	}
	
	public static void main(String[] args) {
		Task3 test = new Task3();
		int[] A = {60,80,40};
		int[] B = {2,3,5};
		int M = 5;
		int X = 2; 
		int Y = 200;
		int result = test.solution3(A, B, M, X, Y);
		System.out.println(result);
		
		A = new int[]{40,40,100,80,20};
		B = new int[]{3,3,2,2,3};
		M = 3;
		X = 5;
		Y = 200;
		result = test.solution3(A, B, M, X, Y);
		System.out.println(result);
	}
	
}
