package start202209.cache;

public interface Cache<K,V> {

	public void put(K key, V value);
	
	public V get(K key);
	
	public void printCache();
	
}