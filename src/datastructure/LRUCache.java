package datastructure;

import java.util.HashMap;

// use a double linked list to store the nodes
// when get a node, update it to be the first one of the list
// keep the last one is the least recently used item
// if the capacity is full, just remove the last one
// use a hashmap to save the nodes
public class LRUCache {
    
    private DoubleLinkedNode head;
    private DoubleLinkedNode end;
    private HashMap<Integer, DoubleLinkedNode> map = null;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DoubleLinkedNode(-1,-1);
        end = new DoubleLinkedNode(-1,-1);
        head.next = end;
        end.pre = head;
        map = new HashMap<Integer, DoubleLinkedNode>();
    }
    
    // first judge whether there exists in map
    // if no, return -1
    // if yes, return the node & update the node to be the first one
    public int get(int key) {
        if(map.containsKey(key)) {
            DoubleLinkedNode node = map.get(key);
            setNodeFirst(node);
            return node.val;
        } else {
            return -1;
        }
    }
    
    public void setNodeFirst(DoubleLinkedNode node) {
        // should make sure node's pre & next works well
        if(node.pre != null && node.next != null) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        DoubleLinkedNode first = head.next;
        first.pre = node;
        node.next = first;
        node.pre = head;
        head.next = node;
    }

    // if the capacity is not full, just put, and insert the map
    // if is full, remove the last one, and remove from hashmap
    // insert to the first one, insert the map
    public void set(int key, int value) {
        if(map.containsKey(key)) {
            DoubleLinkedNode node = map.get(key);
            node.val = value;
            setNodeFirst(node);
        } else {
            if(map.size() == capacity) {
                int nodeKey = removeLast();
                map.remove(new Integer(nodeKey));
            } 
            DoubleLinkedNode newNode = new DoubleLinkedNode(key, value);
            map.put(key, newNode);
            setNodeFirst(newNode);
        }
    }
    
    public DoubleLinkedNode removeLast() {
        DoubleLinkedNode last = end.pre;
        last.pre.next = end;
        end.pre = last.pre;
        last.next = null;
        last.pre = null;
        return last.key;
    }

    public static void main(String[] args) {
    	LRUCache test = new LRUCache(2);
        test.set(2, 1);
        test.set(1, 1);
        test.get(2);
        test.set(4, 1);
        test.get(1);
        test.get(2);
    }
}

class DoubleLinkedNode {
    int key;
    int val;
    DoubleLinkedNode pre;
    DoubleLinkedNode next;

    public DoubleLinkedNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}