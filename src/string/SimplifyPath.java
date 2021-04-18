public class SimplifyPath {

	public String simplifyPath(String path) {
		if(path == null || "".equals(path)) return path; 
     	Stack<String> stack = new Stack<String>();
     	int len = path.length();
     	int i = 0;
     	int j = 0;
     	while(i < len) {
     		j = i+1;
     		while(j < len && path.charAt(j) != '/') j++;
     		String sub = path.substring(i, j);
     		if("/..".equals(sub)) {
     			if(!stack.isEmpty()) {
     				stack.pop();
     			} 
     		} else if(!("/.".equals(sub)) && !("/".equals(sub))) {
     			stack.push(sub);
     		}
     		i = j;
     	}   
     	StringBuffer sb = new StringBuffer();
     	Stack<String> tmpStack = new Stack<String>();
     	while(!stack.isEmpty()) {
     		tmpStack.push(stack.pop());
     	}

     	while(!tmpStack.isEmpty()) {
     		sb.append(tmpStack.pop());
     	}
     	if(sb.length() == 0) sb.append("/");
     	return sb.toString();
    }

    // use String.split to split strings by "/"
    public String simplifyPath2(String path) {
    	Stack<String> stack = new Stack<String>();
    	String splits = path.splits("/");
    	for(String sub: splits) {
    		if("..".equals(sub)) {
    			if(!stack.isEmpty()) stack.pop(); 
    		} else if(!(".".equals(sub)) && !"".equals(sub)) {
    			stack.push(sub);
    		}
    	}
    	StringBuffer sb = new StringBuffer();
    	for(String s: stack) {
    		sb.append("/" + s);
    	}
    	if(sb.length() == 0) sb.append("/");
    	return sb.toString();
    }
}