package start202209;

import java.util.Stack;

public class TransactionalStack {

	private Stack<Integer> content;
	private Stack<TransactionalStack> transactions;
	
	public TransactionalStack() {
		content = new Stack<Integer>();
		transactions = new Stack<TransactionalStack>();
		transactions.push(this);
	}
	
	public void push(int value) {
		transactions.peek().content.push(value);
	}
	
	public int top() {
		Stack<Integer> topContent = transactions.peek().content;
		return topContent.isEmpty() ? 0 : topContent.peek();
	}
	
	public int pop() {
		Stack<Integer> topContent = transactions.peek().content;
		return topContent.isEmpty() ? 0 : topContent.pop();
	}
	
	public void begin() {
		TransactionalStack newTransaction = new TransactionalStack();
		newTransaction.content = (Stack<Integer>)content.clone();
		transactions.add(newTransaction);
	}
	
	public boolean rollback() {
		TransactionalStack lastTransaction = transactions.peek();
		if(lastTransaction != this) {
			lastTransaction.pop();
			return true;
		}
		return false;
	}
	
	public boolean commit() {
		TransactionalStack lastTransaction = transactions.peek();
		if(lastTransaction != this) {
			lastTransaction = transactions.pop();
			transactions.peek().content = lastTransaction.content;
			return true;
		}
		return false;
	}
	
	public void print() {
		for(int e: content) {
			System.out.print( e + ",");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		TransactionalStack sol = new TransactionalStack();
        sol.push(4);
        sol.begin();                    // start transaction 1
        sol.push(7);                    // stack: [4,7]
        sol.begin();                    // start transaction 2
        sol.push(2);                    // stack: [4,7,2]
        System.out.println(sol.rollback());// == true;  // rollback transaction 2
        System.out.println(sol.top());// == 7;          // stack: [4,7]
        sol.begin();                    // start transaction 3
        sol.push(10);                   // stack: [4,7,10]
        System.out.println(sol.commit());// == true;    // transaction 3 is committed
        System.out.println(sol.top()); //== 10;
        System.out.println(sol.rollback());// == true;  // rollback transaction 1
        System.out.println(sol.top());// == 4;          // stack: [4]
        System.out.println(sol.commit());// == false;   // there is no open transaction
	}
}
