import java.util.ArrayList;
import java.util.List;

public class TestVersion {

	public int compareVersions(String v1, String v2) {
		String[] nums1 = v1.split("\\.");
		String[] nums2 = v2.split("\\.");
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		//预处理，先把0去掉
		for(int i = 0; i < nums1.length; i++) {
			nums1[i] = removePreZero(nums1[i]);
			list1.add(Integer.parseInt(nums1[i]));
		}
		for(int i = 0; i < nums2.length; i++) {
			nums2[i] = removePreZero(nums2[i]);
			list2.add(Integer.parseInt(nums2[i]));
		}
		int offset = Math.abs(nums2.length - nums1.length);
		int len = Math.max(nums1.length, nums2.length);
		if(nums1.length > nums2.length) {
			fill(list2, offset);
		} else {
			fill(list1, offset);
		}
		
		print(list1);
		print(list2);
		
		for(int i = 0; i < len; i ++) {
			if(list1.get(i) > list2.get(i)) {
				return 1;
			} else if(list1.get(i) < list2.get(i)) {
				return -1;
			}
		}
		return 0;
	}
	
	private void fill(List<Integer> list, int offset) {
		for(int i = 0; i < offset; i++) {
			list.add(0);
		}
	}
	
	private String removePreZero(String num) {
		char[] chars = num.toCharArray();
		if(chars.length == 0) return num;
		int i = 0;
		while(i < chars.length && chars[i] == '0') i++;
		return i == chars.length ? "0" : num.substring(i);
	}
	
	private void print(List<Integer> list) {
		for(Integer e: list) {
			System.out.print(e + ",");
		}
		System.out.println("---------");
	}
	
	public static void main(String[] args) {
		TestVersion test = new TestVersion();
		int res = test.compareVersions("1.0.1", "1");
		System.out.println(res);
		
		res = test.compareVersions("1.1.9", "1.0");
		System.out.println(res);
		
		res = test.compareVersions("1.0.009", "1.0.9");
		System.out.println(res);
	}
	
}
