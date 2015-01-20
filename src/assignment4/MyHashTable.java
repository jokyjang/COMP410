package assignment4;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyHashTable<K, V> implements HashTable<K, V> {
	private class Entry<K, V> {
		public K key;
		public V value;
		public Entry(K k, V v) {
			key = k;
			value = v;
		}
	}
	private final int BUCKET_SIZE = 1009;
	private List<Entry>[] buckets;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MyHashTable() {
		buckets = new LinkedList[BUCKET_SIZE];
		for(int i = 0; i < BUCKET_SIZE; ++i) {
			buckets[i] = new LinkedList<Entry>();
		}
	}

	public void put(K key, V value) {
		// TODO Auto-generated method stub
		int index = key.hashCode() % BUCKET_SIZE;
		buckets[index].add(new Entry<K, V>(key, value));
	}

	public V get(K key) {
		// TODO Auto-generated method stub
		int index = key.hashCode() % BUCKET_SIZE;
		Iterator<Entry> iterator = buckets[index].iterator();
		while(iterator.hasNext()) {
			Entry<K, V> next = iterator.next();
			if(next.key.equals(key)) return next.value;
		}
		return null;
	}

	public V remove(K key) {
		// TODO Auto-generated method stub
		int index = key.hashCode() % BUCKET_SIZE;
		int i = 0;
		Entry<K, V> current = null;
		while(i < buckets[index].size()) {
			current = buckets[index].get(i);
			if (current.key.equals(key))
				break;
			++i;
		}
		if(i == buckets[index].size()) return null;
		V value = current.value;
		buckets[index].remove(i);
		return value;
	}

}
