package start202209.cache;

public class CacheTest {

	
	public static void main(String[] args) {
		Cache<Integer, String> cache = new LRUCache<Integer, String>(3);
		cache.put(1, "abc");
		cache.put(2, "def");
		cache.printCache();
		cache.get(1);
		
		cache.printCache();
		cache.put(3, "xyz");
		cache.get(2);
		cache.printCache();
		cache.put(4, "uvw");
		cache.printCache();
		cache.put(5, "plm");
		cache.printCache();
		cache.get(4);
		cache.printCache();
	}
	
}
