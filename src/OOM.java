import java.util.ArrayList;
import java.util.List;


public class OOM {

//	Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//	endless loop
	public void outOfMemory() {
		List<Integer> list = new ArrayList<Integer>();
		int i = 0;
		while(true) {
			list.add(i);
			i++;
			if(i == Integer.MAX_VALUE) i = 0;
		}
	}
	
//	Exception in thread "main" java.lang.StackOverflowError
//	no stop recursive
	public void outOfStack() {
		outOfStack();
	}
	
	public static void main(String[] args) {
		OOM test = new OOM();
		test.outOfMemory();
	}
	
}
