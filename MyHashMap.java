/**
 * @author Christian Burke and Michael D'Amico
 * @version 26 November 2024
 */
package twitterpack;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * A custom implementation of a hash map that uses separate chaining for collision resolution.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class MyHashMap<K, V> implements Iterable<K> {
	
	private static final double LOAD_FACTOR = 0.75; // Load factor threshold for resizing
    private LinkedList<MyEntry<K, V>>[] table; // Array of linked lists for separate chaining
    private int size; // Number of entries in the map
    private int tableSize; // Current size of the hash table
    private int threshold; // Resize threshold based on load factor
    private int resizeCount; // Number of times the table has been resized
    private int collisions; // Number of collisions encountered during insertion

    
    /**
     * Represents a key-value pair stored in the hash map.
     */
    protected static class MyEntry<K, V> {
        final K key; // Key associated with the entry
        V value; // Value associated with the entry

        /**
         * Constructs a new entry with the specified key and value.
         *
         * @param key   the key
         * @param value the value
         */
        MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    

    /**
     * Constructs a new, empty hash map with an initial table size of 32.
     */
    @SuppressWarnings("unchecked")
	public MyHashMap() {
        tableSize = 32;
        table = new LinkedList[tableSize];
        threshold = (int) (LOAD_FACTOR * tableSize);
        resizeCount = 0;
        collisions = 0;
    }

    /**
     * Computes the index in the hash table for the given key.
     *
     * @param key the key
     * @return the index corresponding to the key
     */
    private int indexFor(K key) {
        int hash = key.hashCode();
        hash ^= (hash >>> 16);
        return hash & (tableSize - 1);
    }

    /**
     * Retrieves the value associated with the specified key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or {@code null} if the key is not found
     */
    public V get(K key) {
        int index = indexFor(key);

        if (table[index] == null) {
            return null;
        }
        for (MyEntry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /**
     * Associates the specified value with the specified key in the map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(K key, V value) {
        int index = indexFor(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (MyEntry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        collisions++;
        table[index].add(new MyEntry<>(key, value));
        size++;

        if (size > threshold) {
            resize();
        }
    }

    /**
     * Resizes the hash table when the load factor exceeds the threshold.
     */
    private void resize() {
        tableSize *= 2;
        threshold = (int) (LOAD_FACTOR * tableSize);
        @SuppressWarnings("unchecked")
		LinkedList<MyEntry<K, V>>[] newTable = new LinkedList[tableSize];

        for (LinkedList<MyEntry<K, V>> bucket : table) {
            if (bucket != null) {
                for (MyEntry<K, V> entry : bucket) {
                    int newIndex = indexFor(entry.key);
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new LinkedList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }
        table = newTable;
        resizeCount++;
    }

    

    /**
     * Returns the number of times the table has been resized.
     *
     * @return the resize count
     */
    public int getResizeCount() {
        return resizeCount;
    }
    
    
    /**
     * Returns the number of collisions encountered during insertion.
     *
     * @return the collision count
     */
    public int getCollisions() {
        return collisions;
    }
    
    
    /**
     * Retrieves the value associated with the specified key, or a default value if the key is not found.
     *
     * @param key          the key whose associated value is to be returned
     * @param defaultValue the default value to return if the key is not found
     * @return the value associated with the key, or the default value if the key is not found
     */
    public V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return (value != null) ? value : defaultValue;
    }
    
   /**
     * Returns an iterator over the keys in the hash map.
     *
     * @return an iterator over the keys
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int bucketIndex = 0;
            private Iterator<MyEntry<K, V>> bucketIterator = (table[bucketIndex] == null) ? null : table[bucketIndex].iterator();

            private void advanceBucket() {
                while (bucketIndex < tableSize && (table[bucketIndex] == null || !bucketIterator.hasNext())) {
                    bucketIndex++;
                    bucketIterator = (bucketIndex < tableSize && table[bucketIndex] != null) ? table[bucketIndex].iterator() : null;
                }
            }

            @Override
            public boolean hasNext() {
                if (bucketIterator != null && bucketIterator.hasNext()) {
                    return true;
                }
                advanceBucket();
                return bucketIterator != null && bucketIterator.hasNext();
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                return bucketIterator.next().key;
            }
        };
    }
    
}
