package abc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Basic {

	public static void main(String[] args) {
		int[] nums = {23,12,13,9,15,11,78};
		List list = Arrays.asList(nums);
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}
	
}
