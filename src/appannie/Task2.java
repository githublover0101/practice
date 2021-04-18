package appannie;

public class Task2 {

//	return 1 or 0
//	expected time complexity is O(n)
//	expected space complexity is O(n)
	
//	time complexity is O(n^2)
//	space complexity is O(n)
//	exist indexes P & Q, make A[i...P-1] = A[P...Q-1] = A[Q...j]
	public int solution(int[] A) {
		int len = A.length;
		if(len == 0) return 0;
		int[] sum = new int[len];
		sum[0] = A[0];
		for(int i = 1; i < len; i++) {
			sum[i] = sum[i-1] + A[i];
		}
		
		for(int i = 0; i < len; i++) {
			for(int j =i+1; j < len-1; j++) {
				if(sum[i] == sum[j]-sum[i+1] && sum[j]-sum[i+1] == sum[len-1]-sum[j+1]) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	
//	two pointers, time complexity is O(n)
	public int solution2(int[] A) {
		int len = A.length;
		if(len == 0) return 0;
		int[] sum = new int[len];
		sum[0] = A[0];
		for(int i = 1; i < len; i++) {
			sum[i] = sum[i-1] + A[i];
		}
		
//		[0...i], [i+1...j], [j+1...len-1]
		int i = 0;
		int j = len-2;
		int leftSum=0;
		int midSum=0;
		int rightSum = 0;
		while(i+1 < j) {
			leftSum = sum[i];
			midSum = sum[j]-sum[i+1];
			rightSum = sum[len-1]-sum[j+1];
			if(leftSum == midSum && midSum == rightSum) {
				return 1;
			} else if(leftSum > rightSum) { //when right sum is bigger than left sum, j--, to make sure right sum bigger
				j--;
			} else if(leftSum < rightSum) {
				i++;
			} else {
				i++;
				j--;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Task2 test = new Task2();
		int[] A = {4,5,1,1,1,1,4,3,1};
		int result = test.solution2(A);
		System.out.println(result);
		A = new int[]{1,2,1,18,91,12};
		result = test.solution(A);
		System.out.println(result);
	}
	
}
