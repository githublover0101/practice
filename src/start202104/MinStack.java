package start202104;

import java.util.Stack;

public class MinStack {

	private Stack<Integer> stack = null;
	private Stack<Integer> minStack = null;
	
	public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
        
    }
    
    public void push(int val) {
        stack.push(val);
        
        //需要分第一次push和非第一次push的情况
        if(!minStack.isEmpty()) {
            int curMin = minStack.peek();
            curMin = Math.min(val, curMin);
            minStack.push(curMin);
        } else {
            minStack.push(val);
        }
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
	
}
