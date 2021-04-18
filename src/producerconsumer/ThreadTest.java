package producerconsumer;

class Storage {

	int[] list = new int[10];
	int index = 0;
	
	public synchronized void produce(int num) {
		while(index == list.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		list[index] = num;
		index++;
		System.out.println("produce: " + num + ", and the total is: " + index);
	}
	
	public synchronized void consume() {
		while(index == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		index--;
		System.out.println("consume one, and the total is: " + index);
	}
	
}

class ConsumerThread extends Thread {
	
	Storage list = new Storage();
	
	public ConsumerThread(Storage list) {
		this.list = list;
	}
	
	public void run() {
		for(int i = 0; i < 20; i++) {
			list.consume();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ProducerThread extends Thread {
	Storage list = new Storage();
	
	public ProducerThread(Storage list) {
		this.list = list;
	}
	
	public void run() {
		for(int i = 0; i < 20; i++) {
			list.produce(i);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadTest {
	public static void main(String[] args) {
		Storage list = new Storage();
		ConsumerThread c = new ConsumerThread(list);
		ProducerThread p = new ProducerThread(list);
		ProducerThread p2 = new ProducerThread(list);
		c.start();
		p.start();
		p2.start();
	}
}
