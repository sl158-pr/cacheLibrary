package util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class Name: ConcurrentLRUCache.java
 * 
 * Simple Cache library(LRU cache)-Generic
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */
public class ConcurrentLRUCache<Key, Value> {

	private final int maxSize;
	private ConcurrentHashMap<Key, Value> map;
	private ConcurrentLinkedQueue<Key> queue;

	public ConcurrentLRUCache(final int maxSize) {
		this.maxSize = maxSize;
		map = new ConcurrentHashMap<Key, Value>(maxSize);
		queue = new ConcurrentLinkedQueue<Key>();
	}

	/**
	 * @param key
	 *            - may not be null!
	 * @param value
	 *            - may not be null!
	 */
	public void put(final Key key, final Value value) {
		if (map.containsKey(key)) {
			queue.remove(key); // remove the key from the FIFO queue
		}

		while (queue.size() >= maxSize) {
			Key oldestKey = queue.poll();
			if (null != oldestKey) {
				map.remove(oldestKey);
			}
		}
		queue.add(key);
		map.put(key, value);
	}

	/**
	 * @param key
	 *            - may not be null!
	 * @return the value associated to the given key or null
	 */
	public Value get(final Key key) {
		return map.get(key);
	}

	public Boolean hasKey(final Key key) {
		if (map.containsKey(key))
			return true;
		return false;

	}

	public void removeOldestByTime(final Key oldestKey) {
		map.remove(oldestKey);
		queue.remove(oldestKey);
	}
}