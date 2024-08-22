package com.iaverin.java.lrucache;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;


public class LruCache<V> {
    private Deque<String> stack;
    private int size;
    private HashMap<String, V> hashMap;

    public LruCache(int cacheSize) {
        this.size = cacheSize;
        this.stack = new ArrayDeque<String>(cacheSize);
        this.hashMap = new HashMap<String, V>();
    }

    public V getItem(String id) {
        if (this.stack.contains(id)) { 
            this.stack.remove(id);
            this.stack.addFirst(id);
            return this.hashMap.get(id);
        }
        return null;
    }

    public void putItem(String id, V item) {
        if (this.stack.contains(id)) {
            this.getItem(id);
            return;
        }

        if ( this.stack.size() < this.size) {
            this.stack.addFirst(id);
            this.hashMap.put(id, item);
            return;
        }
        String lastItemId = this.stack.getLast();
        this.stack.removeLast();
        this.hashMap.remove(lastItemId);
        this.stack.addFirst(id);
        this.hashMap.put(id, item);
    }

    public boolean invalidateItem(String id) {
        if (this.stack.contains(id)) {
            this.stack.remove(id);
            this.hashMap.remove(id);
            return true;
        }
        return false;
    }

    public void printStack() {
        System.out.println("--- LRU Cache Contents ---");
        for (String item : this.stack) {
            System.out.print(item + " ");
        }
    System.out.println();
    System.out.println("---");
    }
}
