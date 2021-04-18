public class Tree {

	public boolean isValidBSTPreorder(int[] nodes) {
		if(nodes.length == 0) return true;
		return isValidBSTPreorder(nodes, 0, nodes.length-1);
	}

	public boolean isValidBSTPreorder(int[] nodes, int start, int end) {
		if(start < end) {
			int root = nodes[start];
			// [start+1, index] all small than root
			// [index+1, end] all big than root
			int sStart = start+1;
			int index = start+1;
			while(nodes[index] < root) {
				index++;
			}
			if(index > sStart) {
				int bStart = index;
				while(bStart <= end) {
					if(nodes[bStart] <= root) return false;
					bStart++; 
				}
				index--;
			}
			return isValidBSTPreorder(nodes, start+1, index) && isValidBSTPreorder(nodes, index+1, end);
		}
		return true;
	}
}