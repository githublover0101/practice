public class Read extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        if(n < 4) {
        	read4(buf);
        	return n; 
        } 
        int i = 0;
        while(i + 4 <= n) {
        	
        }
    }
}