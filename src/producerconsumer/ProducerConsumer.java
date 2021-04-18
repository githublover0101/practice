package producerconsumer;

class Tudou {

	private int id;
	public Tudou(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Tudou: " + id;
	}
}

public class ProducerConsumer {
	public static void main(String[] args) {
		StackBasket ss = new StackBasket();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		Thread tp = new Thread(p);
		Thread tc = new Thread(c);
		tp.start();
		tc.start();
	}
}

class StackBasket {
	
	Tudou[] list = new Tudou[10];
	int index = 0;
	
	public synchronized void push(Tudou t) {
		try {
			while(index == list.length) {
				System.out.println("生产真满了！！");
				this.wait();
			}
			this.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list[index] = t;
		index++;
		System.out.println("生产了：" + t + ", 共" + index + "个土豆");
	}
	
	public synchronized Tudou pop() {
		try {
			while(index == 0) {
				System.out.println("消费完了！！！");
				this.wait();
			}
			this.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		index --;
		System.out.println("消费了：" + list[index] + ", 剩下" + index + "个土豆！");
		return list[index];
	}
}

class Producer implements Runnable {
	StackBasket ss = new StackBasket();

	Producer(StackBasket ss) {
		this.ss = ss;
	}
	@Override
	public void run() {
		for(int i = 0; i < 20; i ++) {
			Tudou t = new Tudou(i);
			ss.push(t);
			System.out.println("Producer: current thread:" + Thread.currentThread().getName());
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}

class Consumer implements Runnable {
	StackBasket ss = new StackBasket();
	Consumer(StackBasket ss) {
		this.ss = ss;
	}
	
	@Override
	public void run() {
		for(int i = 0 ; i < 20; i ++) {
			Tudou t = ss.pop();
			System.out.println("Consumer: current thread:" + Thread.currentThread().getName());
			try {
				Thread.sleep((int)(Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

