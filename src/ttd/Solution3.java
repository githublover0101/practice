package ttd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Solution3 {
    public static void main(String[] args) throws IOException {
//        SetAssociativeCacheRunner.parseInput(System.in);
    	String firstLine = "4,5,LRUReplacementAlgo";
    	OutParam<String> LRUReplacementAlgo = new OutParam<>();
    	String[] lines = {
    			"Set,k1,v1",
    			"Set,k2,v2",
    			"ContainsKey,k1",
    			"Get,k1",
    			"Set,k3,v3",
    			"Set,k4,v4",
    			"Get,k1",
    			"Set,k5,v5",
    			"ContainsKey,k5",
    			"ContainsKey,k1",
    			"Get,k1",
    			};
    	
    	
    	SetAssociativeCache<String, String> cache = createCache(firstLine, LRUReplacementAlgo);
    	for(String line: lines) {
    		Object retValue = SetAssociativeCacheFactory.InvokeCacheMethod(line, cache);
    		System.out.println(retValue);
    	}
    }

    /**
     * Parses Test Case input to instantiate and invoke a SetAssociativeCache
     *
     * NOTE: You can typically ignore anything in here. Feel free to collapse...
     */
    static class SetAssociativeCacheRunner {
        public static void parseInput(InputStream inputStream) throws IOException {
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);

            String line;
            int lineCount = 0;
            SetAssociativeCache<String, String> cache = null;

            while (!isNullOrEmpty(line = reader.readLine())) {
                lineCount++;
                OutParam<String> replacementAlgoName = new OutParam<>();

                if (lineCount == 1) {

                    cache = createCache(line, replacementAlgoName);

                } else {

                    // All remaining lines invoke instance methods on the SetAssociativeCache
                    Object retValue = SetAssociativeCacheFactory.InvokeCacheMethod(line, cache);

                    // Write the method's return value (if any) to stdout
                    if (retValue != null) {
                        System.out.println(retValue);
                    }
                }
            }
        }
    }

    private static SetAssociativeCache<String, String> createCache(String inputLine, OutParam<String> replacementAlgoName) {
        String[] cacheParams = Arrays.stream(inputLine.split(",")).map(s -> s.trim()).toArray(n -> new String[n]);
        int setCount = Integer.parseInt(cacheParams[0]);
        int setSize = Integer.parseInt(cacheParams[1]);
        replacementAlgoName.value = cacheParams[2];
        return SetAssociativeCacheFactory.CreateStringCache(setCount, setSize, replacementAlgoName.value);
    }


    // ############################ BEGIN Solution Classes ############################

    /**
     * NOTE: You are free to modify anything below, except for class names and generic interface.
     * Other public interface changes may require updating one or more of the helper classes above
     * for test cases to run and pass.
     * <p>
     * A Set-Associative Cache data structure with fixed capacity.
     * <p>
     * - Data is structured into setCount # of setSize-sized sets.
     * - Every possible key is associated with exactly one set via a hashing algorithm
     * - If more items are added to a set than it has capacity for (i.e. > setSize items),
     *      a replacement victim is chosen from that set using an LRU algorithm.
     * <p>
     * NOTE: Part of the exercise is to allow for different kinds of replacement algorithms...
     */
    public static class SetAssociativeCache<TKey, TValue> {
        int Capacity;
        int SetSize;
        int SetCount;
        CacheSet<TKey, TValue>[] Sets;
        ReadWriteLock readWriteLock;

        public SetAssociativeCache(int setCount, int setSize, IReplacementAlgo<TKey, TValue> replacementAlgo) {
            this.SetCount = setCount;
            this.SetSize = setSize;
            this.Capacity = this.SetCount * this.SetSize;

            // Initialize the sets
            this.Sets = new CacheSet[this.SetCount];
            for (int i = 0; i < this.SetCount; i++) {
//                Sets = new CacheSet<>(setSize, replacementAlgo);
            	Sets[i] = new CacheSet<>(setSize, replacementAlgo);
            }
            this.readWriteLock = new ReentrantReadWriteLock();
        }

        /** Gets the value associated with `key`. Throws if key not found. */
        public TValue get(TKey key) {
            TValue value = null;
            int setIndex = this.getSetIndex(key);
            this.readWriteLock.readLock().lock();
            try {
            	CacheSet<TKey, TValue> set = this.Sets[setIndex];
                value = set.get(key);
            } finally {
            	this.readWriteLock.readLock().unlock();
            }
            return value;
        }

        /**
         * Adds the `key` to the cache with the associated value, or overwrites the existing key.
         * If adding would exceed capacity, an existing key is chosen to replace using an LRU algorithm
         * (NOTE: It is part of this exercise to allow for more replacement algos)
         */
        public void set(TKey key, TValue value) {
            int setIndex = this.getSetIndex(key);
            this.readWriteLock.writeLock().lock();
            try {
            	CacheSet<TKey, TValue> set = this.Sets[setIndex];
                set.set(key, value);
            } finally {
                this.readWriteLock.writeLock().unlock();
            }
        }

        /** Returns the count of items in the cache */
        public int getCount() {
            int count = 0;
            for (int i = 0; i < this.Sets.length; i++) {
                count += this.Sets[i].Count.get();
            }
            return count;
        }

        /** Returns `true` if the given `key` is present in the set; otherwise, `false`. */
        public boolean containsKey(TKey key) {
            int setIndex = this.getSetIndex(key);
            CacheSet<TKey, TValue> set = this.Sets[setIndex];
            return set.containsKey(key);
        }

        /** Maps a key to a set */
        private int getSetIndex(TKey key) {
            int startIndex = Math.abs(key.hashCode()) % SetCount;
            return startIndex;
        }

    }

    /**
     * An internal data structure representing one set in a N-Way Set-Associative Cache
     */
    static class CacheSet<TKey, TValue> {
        int Capacity;
        Map<TKey, CacheNode<TKey, TValue>> map;
//        LinkedList<TKey> UsageTracker;
        
        CacheNode<TKey, TValue> head;
        CacheNode<TKey, TValue> tail;
        
        ReentrantReadWriteLock readWriteLock;
        IReplacementAlgo<TKey, TValue> replacementAlgo;
        public AtomicInteger Count;
                
        public CacheSet(int capacity, IReplacementAlgo<TKey, TValue> replacementAlgo) {
            this.Capacity = capacity;
            this.Count = new AtomicInteger();
            this.head = new CacheNode<>();
            this.tail = new CacheNode<>();
            this.head.next = this.tail;
            this.tail.pre = this.head;
            this.map = new ConcurrentHashMap<>(capacity);
            this.replacementAlgo = replacementAlgo;
            this.readWriteLock = new ReentrantReadWriteLock();
        }

        //remove the tail
        public void remove() {
        	this.readWriteLock.writeLock().lock();
        	try {
        		CacheNode<TKey, TValue> lastNode = tail.pre;
            	tail.pre = lastNode.pre;
            	lastNode.pre.next = tail;
                map.remove(lastNode.key);
                Count.decrementAndGet();
        	} finally {
        		this.readWriteLock.writeLock().unlock();
        	}
        }
        
        public void moveNode(CacheNode<TKey, TValue> head, CacheNode<TKey, TValue> tail, CacheNode<TKey, TValue> targetNode) {
        	this.readWriteLock.writeLock().lock();
        	try {
            	replacementAlgo.updateTracker(head, tail, targetNode);
        	} finally {
            	this.readWriteLock.writeLock().unlock();
        	}
        }
        
        /** Gets the value associated with `key`. Throws if key not found. */
        public TValue get(TKey key) {
            if (map.containsKey(key)) {
//                replacementAlgo.updateTracker(UsageTracker, key);
            	CacheNode<TKey, TValue> targetNode = map.get(key);
            	moveNode(head, tail, targetNode);
                return targetNode.value;
            }else {
                 throw new RuntimeException(String.format("The key '%s' was not found", key));
            }
        }

        /**
         * Adds the `key` to the cache with the associated value, or overwrites the existing key.
         * If adding would exceed capacity, an existing key is chosen to replace using an LRU algorithm
         * (NOTE: It is part of this exercise to allow for more replacement algos)
         */
        public void set(TKey key, TValue value) {
        	CacheNode<TKey, TValue> targetNode = null;
        	if(map.containsKey(key)) {
        		targetNode = map.get(key);
        		targetNode.value = value;
        	} else {
        		if (this.Count.get() == this.Capacity) {
        			this.remove();
        		}
        		targetNode = new CacheNode<>(key, value);
        		Count.incrementAndGet();
            }
        	moveNode(head, tail, targetNode);
            map.put(key, targetNode);
        }

        /** Returns `true` if the given `key` is present in the set; otherwise, `false`. */
        public boolean containsKey(TKey key) {
            return this.map.containsKey(key);
        }
    }

    /**
     * An internal data structure representing a single item in an N-Way Set-Associative Cache
     */
    static class CacheNode<TKey, TValue> {
        public TKey key;
        public TValue value;
        public CacheNode<TKey, TValue> pre;
        public CacheNode<TKey, TValue> next;

        public CacheNode() {
        	
        }
        
        public CacheNode(TKey key, TValue value) {
            this.key = key;
            this.value = value;
        }
    }


    public final static String LruAlgorithm = "LRUReplacementAlgo";
    public final static String MruAlgorithm = "MRUReplacementAlgo";

    /**
     * A common interface for replacement algos, which decide which item in a CacheSet to evict
     */
    interface IReplacementAlgo<TKey, TValue> {
        public void updateTracker(CacheNode<TKey, TValue> head, CacheNode<TKey, TValue> tail, CacheNode<TKey, TValue> targetNode);
    }

    static class LRUReplacementAlgo<TKey, TValue> implements IReplacementAlgo<TKey, TValue> {
    	public void updateTracker(CacheNode<TKey, TValue> head, CacheNode<TKey, TValue> tail, CacheNode<TKey, TValue> targetNode) {
    		//move to first
    		if(targetNode.pre != null && targetNode.next != null) {
    			targetNode.pre.next = targetNode.next;
    			targetNode.next.pre = targetNode.pre;
    		}
    		targetNode.next = head.next;
    		head.next.pre = targetNode;
    		head.next = targetNode;
    		targetNode.pre = head;
    	}
    }

    static class MRUReplacementAlgo<TKey, TValue> implements IReplacementAlgo<TKey, TValue> {
    	public void updateTracker(CacheNode<TKey, TValue> head, CacheNode<TKey, TValue> tail, CacheNode<TKey, TValue> targetNode) {
    		//move to last
    		if(targetNode.pre != null && targetNode.next != null) {
    			targetNode.pre.next = targetNode.next;
    			targetNode.next.pre = targetNode.pre;
    		}
    		targetNode.next = tail;
    		targetNode.pre = tail.pre;
    		tail.pre.next = targetNode;
    		tail.pre = targetNode;
    	}
    }

    // ############################ BEGIN Helper Classes ############################
    // NOTE: Your code in the classes below will not be evaluated as part of the exericse.
    // They are just used by the stub code in the header to help run HackerRank test cases.
    // You may need to make small modifications to these classes, depending on your interface design,
    // for tests to run and pass, but it is not a core part of the exercise
    //
    static class OutParam<T> {
        public T value;
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static class SetAssociativeCacheFactory {
        /// NOTE: replacementAlgoName is provided in case you need it here. Whether you do will depend on your interface design.
        public static SetAssociativeCache<String, String> CreateStringCache(int setCount, int setSize, String replacementAlgoName) {
            IReplacementAlgo<String, String> replacementAlgo =
                replacementAlgoName.equals(LruAlgorithm) ? new LRUReplacementAlgo<String, String>() : new MRUReplacementAlgo<String, String>();
            return new SetAssociativeCache<>(setCount, setSize, replacementAlgo);
        }

        /// NOTE: Modify only if you change the main interface of SetAssociativeCache
        public static Object InvokeCacheMethod(String inputLine, SetAssociativeCache<String, String> cacheInstance) {
            String[] callArgs = Arrays.stream(inputLine.split(",", -1)).map(a -> a.trim()).toArray(n -> new String[n]);

            String methodName = callArgs[0].toLowerCase();
            //String[] callParams = Arrays.copyOfRange(callArgs, 1, callArgs.length - 1); // TODO: This is unused

            switch (methodName) {
                case "get":
                    return cacheInstance.get(callArgs[1]);
                case "set":
                    cacheInstance.set(callArgs[1], callArgs[2]);
                    return null;
                case "containskey":
                    return cacheInstance.containsKey(callArgs[1]);
                case "getcount":
                    return cacheInstance.getCount();

                // TODO: If you want to add and test other public methods to SetAssociativeCache,
                //  add them to the switch statement here... (this is not common)

                default:
                    throw new RuntimeException(String.format("Unknown method name '{%s}'", methodName));
            }
        }
    }

    // TODO: Consider making use of this in the `SetAssociativeCacheFactory` above to map replacement algo name
    // to a IReplacementAlgo instance for the interface you design
    public class ReplacementAlgoFactory {
        IReplacementAlgo createReplacementAlgo(String replacementAlgoName) {
            switch (replacementAlgoName) {
                case LruAlgorithm:
                    return new LRUReplacementAlgo();
                case MruAlgorithm:
                    return new MRUReplacementAlgo();
                default:
                    // TODO: If you want to test other replacement algos, add them to the switch statement here...
                    throw new RuntimeException(String.format("Unknown replacement algo '%s'", replacementAlgoName));
            }
        }
    }

    // ^^ ######################### END Helper Classes ######################### ^^

}

