import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class ThreadPool {

	private static ThreadPool instance = null;
	
	private static int worker_num = 5;
	private static int taskCounter = 0;
	public static boolean systemIsBusy = false;
	
	Executor executor = null;
	ExecutorService executorService = null;
	AbstractExecutorService abstractService = null;
	
	public static void main(String[] args) {
//		ThreadPoolExecutor pool = new ThreadPoolExecutor();
//		ConcurrentHashMap<Integer, String> cMap = new ConcurrentHashMap<Integer, String>();
//		HashMap<Integer, String> map = new HashMap<Integer, String>();
//		Map<Integer, String> sMap = Collections.synchronizedMap(map);
//		for(int i = 0; i < 10; i++) {
//			sMap.put(i, i+"name");
//		}
//		
//		Thread1 t1 = new Thread1(sMap);
//		Thread2 t2 = new Thread2(sMap);
//		t1.start();
//		t2.start();
		
		ThreadPool test = new ThreadPool();
		test.test();
	}
	
//	test java8 new features
	public void test() {
		
//		lambdas and functional interfaces
		Arrays.asList("a","b","c").forEach(e -> System.out.println(e));
		
		Arrays.asList("a", "b", "c").forEach((String e) -> System.out.println(e));
		
		Arrays.asList("a","b").forEach(e -> {
			System.out.println(e);
			System.out.println(e + "hellow");
		});
		
		String s = "heello world, java 8"; //local variables need to be final
		Arrays.asList("a", "v").forEach(e -> {
			System.out.println(e + s);
		});
		
		
		final String ss = ",";
		Arrays.asList("a","v","aaaa").forEach(e -> {
			System.out.print(e + ss);
		});
		
		Thread tThread = new Thread();
		Arrays.asList("a","c","ss").forEach(e -> {
			tThread.setName("abc");
			System.out.print(e + tThread.getName());
		});
		
		String[] array = {"a", "bc", "c", "aa"};
		Arrays.asList(array).sort((e1, e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});
		for(String e: array) {
			System.out.println(e);
		}
		
		try {
			Class.forName("");
		} catch (ClassNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		
	}
	
}

class Thread1 extends Thread {
	private Map<Integer, String> map;
	
	public Thread1(Map<Integer, String> map) {
		this.map = map;
	}
	
	public void run() {
		for(int i = 0; i < 10; i ++) {
			map.put(i, i+"abc");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread2 extends Thread {
	private Map<Integer, String> map;
	
	public Thread2(Map<Integer, String> map) {
		this.map = map;
	}
	
	public void run() {
		Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
		Iterator<Map.Entry<Integer, String>> it = entrySet.iterator();
		Map.Entry<Integer, String> cur = null;
		while(it.hasNext()) {
			cur = it.next();
			cur.setValue(cur.getValue() + "ititit");
			System.out.println(cur.getKey());
			System.out.println(cur.getValue());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
