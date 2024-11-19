package twitterpack;

import java.util.LinkedList;
import java.util.Map.Entry;

public class MyHashMap <K, V> implements Iterable <K> {
	
	protected static class MyEntry <K,V>{
		final K key;
		V value;
		
		MyEntry(K key, V value){
			this.key = key;
			this.value = value;
		}

		public static void put(int indexFor) {
			// TODO Auto-generated method stub
			
		}
	}
	private static final double LOAD_FACTOR = 0.75;
	private LinkedList<MyEntry<K,V>> [] table;
	private int size;
	private int tableSize;
	private int threshold;
	private int resizeCount;
	
	public MyHashMap() {
		tableSize = 32;
		table = new LinkedList[tableSize];
		threshold = (int) LOAD_FACTOR * table.length;
		resizeCount = 0;
	}
	
	private int indexFor(K key) {
		int hash = key.hashCode();
		int index = hash & (tableSize -1 );
		return index;
		}
	
	public V get(K key) {
		int index = indexFor(key);
		
		if (table[index] == null) {
			return null;
		}
		for(MyEntry <K,V> entry : table[index]){
			if(Entry.comparingByKey().equals(key)) {
				return entry.value;
				
			}
		}
		return null;
	}
	
	
	public void put(K key, V value) {
		int index = indexFor(key);
		
		if (table[index] == null) {
			table[index] = new LinkedList<>(); //initilizes linked list
		}
		
		for(MyEntry <K,V> entry : table[index]) { //if there is already a linkedlist with cat for instance this will add cow to that list 
			if (entry.key.equals(key)) {
				entry.value = value;
				return;
			}
		}
		table[index].add(new MyEntry (key, value)); // adds to list
		size++;
		
		if (size > threshold) { // this incase threshold is met and we need to resisze
			return resize();
		}
	}

	
	

}
