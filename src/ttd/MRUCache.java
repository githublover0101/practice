package ttd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MRUCache<K,V> {

	private int capacity = 0;
	private Map<K, CacheNode<K,V>> map = null;
	private CacheNode<K,V> head = null;
	private CacheNode<K,V> tail = null;
	
	public MRUCache(int capacity) {
		this.capacity = capacity;
		this.map = new ConcurrentHashMap<K, CacheNode<K,V>>();
		this.head = new CacheNode<K,V>();
		this.tail = new CacheNode<K,V>();
		this.head.next = this.tail;
		this.tail.pre = this.head;
	}

	public V get(K key) {
		if(map.containsKey(key)) {
			CacheNode<K,V> target = map.get(key);
			moveToLast(target); //move to last of the double linked list
			return target.value;
		}
		return null;
	}
	
	public void set(K key, V value) {
		if(map.containsKey(key)) {
			CacheNode<K,V> target = map.get(key);
			target.value = value;
			moveToLast(target);
			return;
		}		
		
		if(map.size() == this.capacity) {
			// remove the last one
			CacheNode<K,V> lastNode = this.tail.pre;
			lastNode.pre.next = this.tail;
			this.tail.pre = lastNode.pre;
			map.remove(lastNode.key);
		}
		
		CacheNode<K,V> node = new CacheNode<K,V>(key, value);
		map.put(key, node);
		moveToLast(node);
	}
	
	private void moveToLast(CacheNode<K,V> node) {
		if(node.pre != null && node.next != null) {
			//means not a new node, need to handle the pre node and the next node
			node.pre.next = node.next;
			node.next.pre = node.pre;
		}
		CacheNode<K,V> preNode = this.tail.pre;
		node.pre = preNode;
		preNode.next = node;
		node.next = this.tail;
		this.tail.pre = node;
	}
}

class CacheNode<K,V> {
	K key;
	V value;
	
	CacheNode<K, V> pre;
	CacheNode<K, V> next;
	
	public CacheNode() {
		
	}
	
	public CacheNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
}
