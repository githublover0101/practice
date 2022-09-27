package start202209;


public class ValidIPAddress {
    private final static String IPV6_STR = "0123456789abcdefABCDEF";
    public String validIPAddress(String queryIP) {
        if(isValidIPv4(queryIP)){
            return "IPv4"; 
        } else if(isValidIPv6(queryIP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    private boolean isValidIPv4(String ip) {
        int len = ip.length();
        if(!isValid4(ip.substring(len-1))) return false;

        String[] ipVals = ip.split("\\.");
        if(ipVals.length != 4) return false;
        if("0".equals(ipVals[0])) return false;
        for(int i = 0; i < 4; i++) {
            if(!isValid4(ipVals[i])) return false;
        }
        return true;
    }

    private boolean isValid4(String ipVal) {
        if(ipVal == null || "".equals(ipVal)) return false;
        if(ipVal.charAt(0) == '0') return "0".equals(ipVal);
        int val = 0;
        try {
            val = Integer.parseInt(ipVal);
        } catch(Exception e) {
            return false;
        }
        return val >= 1 && val <= 255;
    }

    private boolean isValidIPv6(String ip) {
        int len = ip.length();
        if(!isValid6(ip.substring(len-1))) return false;
        String[] ipVals = ip.split("\\:");
        if(ipVals.length != 8) return false;
        for(int i = 0; i < 8; i++) {
            if(!isValid6(ipVals[i])) return false;
        }
        return true;
    }

    private boolean isValid6(String ipVal) {
        if(ipVal == null || "".equals(ipVal)) return false;
        int len = ipVal.length();
        if(len < 1 || len > 4) return false;
        for(int i = 0; i < len; i++) {
            char c = ipVal.charAt(i);
            // if(!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) return false;
            if(IPV6_STR.indexOf(c) == -1) {
            	System.out.println(ipVal);
            	return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	ValidIPAddress obj = new ValidIPAddress();
    	String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334";
    	String res = obj.validIPAddress(ip);
    	System.out.println(res);
    }
}