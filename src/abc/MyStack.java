package abc;

import java.util.LinkedList;

public class MyStack {
	private LinkedList<Integer> q1 = null;
    private LinkedList<Integer> q2 = null;
    
    public MyStack() {
        q1 = new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
    }
    
    // Push element x onto stack.
    public void push(int x) {
        q1.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(empty()) return;
        while(q1.size() > 1) {
            int e = q1.poll();
            q2.offer(e);
        }
        q1.poll();
        while(!q2.isEmpty()) {
            int e = q2.poll();
            q1.offer(e);
        }
    }

    // Get the top element.
    public int top() {
        if(empty()) return -1;
        while(q1.size() > 1) {
            int e = q1.poll();
            q2.offer(e);
        }
        int top = q1.peek();
        q2.offer(q1.poll());
        while(!q2.isEmpty()) {
            int e = q2.poll();
            q1.add(e);
        }
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public static void main(String[] args) {
        MyStack test = new MyStack();
        test.push(1);
        test.push(2);
        int top = test.top();
        top = test.top();
        System.out.println(top);
    }
}