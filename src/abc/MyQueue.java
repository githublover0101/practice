package abc;

import java.util.Stack;

public class MyQueue {

    private MaxStack s1 = null;
    private MaxStack s2 = null;

    public MyQueue() {
        s1 = new MaxStack();
        s2 = new MaxStack();
    }

    // Push element x to the back of queue.
    public void push(int x) {
        s1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if(empty()) return;
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                int e = s1.pop();
                s2.push(e);
            }
        } 
        s2.pop();
    }

    // Get the front element.
    public int peek() {
        if(empty()) return -1;
        if(s2.isEmpty()) {
            while(!s1.isEmpty()) {
                int e = s1.pop();
                s2.push(e);
            }
        } 
        return s2.peek();
    }

    public int max() {
        int e1 = s1.max();
        int e2 = s2.max();
        return Math.max(e1, e2);
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    public static void main(String[] args) {
    	MyQueue test = new MyQueue();
    	MaxStack s = new MaxStack();
    	s.push(1);
    	s.push(5);
    	s.push(3);
    	s.push(4);
    	s.push(1);
    	
    	for(int e: s.nodeStack) {
    		System.out.print(e + ",");
    	}
    	System.out.println();
    	
    	for(int e: s.maxStack) {
    		System.out.print(e + ",");
    	}
    }
}

class MaxStack {
    protected Stack<Integer> nodeStack = null;
    protected Stack<Integer> maxStack = null;

    public MaxStack() {
        nodeStack = new Stack<Integer>();
        maxStack = new Stack<Integer>();
    }

    public void push(int e) {
        nodeStack.push(e);
        if(maxStack.isEmpty()) {
            maxStack.push(e);
        } else {
            int max = maxStack.peek();
            max = Math.max(max, e);
            maxStack.push(max);
        }
    }

    public int pop() {
        if(nodeStack.isEmpty()) return -1;
        int e = nodeStack.pop();
        maxStack.pop();
        return e;
    }

    public int max() {
        if(maxStack.isEmpty()) return -1;
        return maxStack.peek();
    }
    
    public int peek() {
    	return nodeStack.peek();
    }
    
    public boolean isEmpty() {
    	return nodeStack.isEmpty();
    }
}