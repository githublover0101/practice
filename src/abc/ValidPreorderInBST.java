package abc;

public class ValidPreorderInBST {

	public boolean validPreorder(int[] array) {
		return validPreorder(array, 0, array.length-1);
	}
	
	public boolean validPreorder(int[] array, int start, int end) {
		if(start == end) return true; //recursively return value
		int root = array[start];
		int k = start+1;
		while(array[k] < root && k <= end) k++;
		
		//[start+1, k-1] is left
		//[k, end] is right
		if(k == start+1) { // means no left child, return checking right tree
			return validPreorder(array, start+1, end);
		} else {
			
			// check left tree elements
			for(int i = start+1; i < k; i++) {
				if(array[i] > root) return false;
			}
			
			if(k <= end) {
				// check right tree elements
				for(int j = k; j <= end; j++) {
					if(array[j] < root) return false;
				}
				return validPreorder(array, start+1, k-1) && validPreorder(array, k, end);
			} else {
				return validPreorder(array, start+1, k-1);
			}
		}
	}
	
	public static void main(String[] args) {
		ValidPreorderInBST test = new ValidPreorderInBST();
		int[] array = {6,4,2,1,3,5,8,7,9};
		boolean result = test.validPreorder(array);
		System.out.println(result);
	}
}
