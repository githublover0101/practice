package amazon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//存放箱子的locker，实现存箱子和取箱子。
//箱子有大、中、小size的箱子，对应的locker也有大号、中号和小号。
//对于小号的箱子，如果有空余的小箱子，则存到小locker，否则可以存到中locker或大locker
//同样对于中箱子，可以存到中locker或者大locker
//实现存箱子和取箱子的功能
public class LockerSolution {

	private final static int BIG = 1;
	private final static int MID = 2;
	private final static int SMALL = 3;
	
	private Map<Integer, List<Locker>> lockerMap = new HashMap<Integer, List<Locker>>();
	private Map<String, Locker> savedMap = new HashMap<String, Locker>();
	
	public LockerSolution(int big, int mid, int small) {
//		int num = 0;
//		List<Locker> bigList = new LinkedList<Locker>();
//		for(int i = 0; i < big; i++) {
//			bigList.add(new Locker(BIG, num++));
//		}
//		lockerMap.put(BIG, bigList);
//		
//		bigList = new LinkedList<Locker>();
//		for(int i = 0; i < big; i++) {
//			bigList.add(new Locker(BIG, num++));
//		}
//		lockerMap.put(BIG, bigList);
//		
//		bigList = new LinkedList<Locker>();
//		for(int i = 0; i < big; i++) {
//			bigList.add(new Locker(BIG, num++));
//		}
//		lockerMap.put(BIG, bigList);
	}
	
	public String save(int size) {
		Locker locker = null;
		switch(size) {
		case BIG:
			locker = lockerMap.get(BIG).get(0);
			
		}
			
	}
	
	public static void main() {
		
	}
}

class Locker {
	private int size = 0;
	private int num = 0;
	Locker(int size, int num) {
		this.size = size;
		this.num = num;
	}
	
	public int getSize() {
		return this.size;
	}
}
