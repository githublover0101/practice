package start202209;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {

	public List<String> restoreIpAddresses(String s) {
		if(s == null || s.length() == 0) return null;
		
		List<String> result = new ArrayList<String>();
		String res = "";
		restore(s, result, res, 0);
		return result;
	}
	
	private void restore(String s, List<String> resultList, String result, int count) {
		if(count == 3 && isValidIPNum(s)) {
			resultList.add(result + s);
			return;
		}
		
		for(int i = 0; i < 4 && i < s.length(); i++) {
			String cur = s.substring(0, i); // i不包含在内，[0,3), ip数字的位数最多不超过3
			if(isValidIPNum(cur)) {
				restore(s.substring(i), resultList, result + cur + ".", count+1);
			}
		}
	}
	
	private boolean isValidIPNum(String ip) {
		if(ip == null || ip.length() == 0) return false;
		if(ip.charAt(0) == '0') { // 以0开头，并且只等于0时
			return "0".equals(ip);
		}
		int num = Integer.parseInt(ip);
		return num > 0 && num <= 255;
	}
	
	public static void main(String[] args) {
		RestoreIPAddress obj = new RestoreIPAddress();
		String ip = "25525511135";
		System.out.println(obj.restoreIpAddresses(ip));
		ip = "0000";
		System.out.println(obj.restoreIpAddresses(ip));
		ip = "101023";
		System.out.println(obj.restoreIpAddresses(ip));
	}
	
}
