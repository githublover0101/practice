package abc;

public class TwoSum {

	public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        for(int i = 0; i < numbers.length-1; i ++) {
            int j = binarySearch(numbers, i+1, numbers.length-1, target-numbers[i]);
            if(j != -1) {
                result[0] = i+1;
                result[1] = j+1;
                break;
            }
        }
        return result;
    }
    
    public int binarySearch(int[] numbers, int left, int right, int target) {
        int mid = 0;
        while(left <= right) {
            mid = left + (right-left)/2;
            if(numbers[mid] == target) return mid;
            if(numbers[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }
    
    public static void main(String[] args) {
    	TwoSum test = new TwoSum();
    	int[] numbers = {2,3,4};
    	int target = 6;
    	int[] result = test.twoSum(numbers, target);
    	for(int e: result) {
    		System.out.println(e);
    	}
    }
	
}
