package start202209.cache;

public class CacheElement<K,V> {
	
	K key;
	V value;
	
	CacheElement<K, V> pre;
	CacheElement<K, V> next;
	
	public CacheElement() {

	}

	public CacheElement(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
}
