public class FindMost {

	public int findMost(int[] nums) {
		int current = 0;
		int count = 0;
		for(int i = 0; i < nums; i ++) {
			if(count == 0) {
				current = nums[i];
				count++;
			} else {
				if(current == nums[i]) { //the same number
					count++;
				} else { // diff number
					count--;
				}
			}
		}
		return current;
	}
}