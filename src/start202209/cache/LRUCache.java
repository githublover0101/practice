package start202209.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> implements Cache<K,V> {

	private int capacity = 0;
	
	private Map<K, CacheElement<K,V>> map = null;
	
	private CacheElement<K, V> head = null;
	private CacheElement<K, V> tail = null;
	
	public LRUCache() {
		
	}
	
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new HashMap<K, CacheElement<K, V>>();
		this.head = new CacheElement<K, V>();
		this.tail = new CacheElement<K, V>();
		
		this.head.next = this.tail;
		this.tail.pre = this.head;
	}



	@Override
	public void put(K key, V value) {
		System.out.println("    put key=" + key + ", value=" + value);
		if(map.containsKey(key)) {
			CacheElement<K, V> element = map.get(key);
			moveToFirst(element);
			element.value = value; //set to new value
			return;
		}
		
		int curSize = map.size();
		if(curSize >= capacity) { //reach to the limit
			removeLast();
		}
		CacheElement<K, V> newElement = new CacheElement<K, V>(key, value);
		moveToFirst(newElement);
		map.put(key, newElement);
	}

	@Override
	public V get(K key) {
		if(map.containsKey(key)) {
			CacheElement<K, V> targetElement = map.get(key);
			moveToFirst(targetElement);
			
			V value = targetElement.value;
			System.out.println("    get key=" + key + ", value=" + value);
			
			return value;
		}
		return null;
	}


	private void moveToFirst(CacheElement<K,V> element) {
//		TODO
		if(!isNewElement(element)) {
			element.pre.next = element.next;
			element.next.pre = element.pre;
		}
		//add to the next of head
		element.next = head.next;
		head.next.pre = element;
		element.pre = head;
		head.next = element;
	}
	
	private boolean isNewElement(CacheElement<K, V> element) {
		return element.pre == null && element.next == null;
	}
	
	private void removeLast() {
		if(map.size() == 0) return;
		CacheElement<K, V> lastElement = tail.pre;
		lastElement.pre.next = tail;
		tail.pre = lastElement.pre;
		map.remove(lastElement.key);
	}
	
	public void printCache() {
		CacheElement<K, V> p = head.next;
		StringBuilder sb = new StringBuilder();
		while(p != tail) {
			K key = p.key;
			V value = p.value;
			sb.append("[").append(key).append(",").append(value).append("], ");
			p = p.next;
		}
		System.out.println(sb.toString());
	}
	
}
