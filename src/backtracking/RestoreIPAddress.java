public class RestoreIPAddress {

	public List<String> restoreIpAddresses(String s) {
        List<String> allList = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        boolean[] visited = new boolean[s.length()];
        restore(s, allList, sb, visited);
        return allList;
    }

    public void restore(String s, List<Integer> allList, StringBuffer sb, boolean[] visited) {
    	if(s.length() == 0) {
    		allList.add(sb.toString());
    		return;
    	}
    	
    }
}