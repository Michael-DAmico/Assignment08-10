/**
 * @author Christian Burke and Michael D'Amico
 * @version 4 December 2024
 */package twitterpack;

import java.util.ArrayList;

public class MyHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;
    
    /**
     * Constructs an empty heap.
     */
    public MyHeap() {
        heap = new ArrayList<>();
    }

    
    /**
     * Adds an element to the heap and restores the heap property.
     *
     * @param key The element to be added.
     */
    public void add(T key) {
        heap.add(key);
        heapifyUp(heap.size() - 1);
    }
	
	
    /**
     * Checks if the heap is empty.
     *
     * @return True if the heap has no elements; false otherwise.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
	
    /**
     * Deletes and returns the root element of the heap, restoring the heap property.
     *
     * @return The root element, or null if the heap is empty.
     */
    public T delete() {
        if (isEmpty()) return null;
        T root = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown(0);
        return root;
    }
	
    /**
     * Returns the root element of the heap without removing it.
     *
     * @return The root element, or null if the heap is empty.
     */
    public T peek() {
        return isEmpty() ? null : heap.get(0);
    }
	
    /**
     * Returns the number of elements in the heap.
     *
     * @return The size of the heap.
     */
    public int size() {
        return heap.size();
    }
    
    /**
     * Restores the heap property by bubbling up an element at the given index.
     *
     * @param index The index of the element to bubble up.
     */
    private void heapifyUp(int index) {
        // TODO: Implement heapify up logic
    }

    /**
     * Restores the heap property by bubbling down an element at the given index.
     *
     * @param index The index of the element to bubble down.
     */
    private void heapifyDown(int index) {
        // TODO: Implement heapify down logic
    }

}
