package twitterpack;

import java.util.Comparator;

public class MyQuickSort<T> {
    private Comparator<T> comparator;
    
    /**
     * Sets the comparator used for comparing elements during the sorting process.
     * The comparator defines the order in which elements are sorted.
     *
     * @param comparator A Comparator that defines the order of the elements.
     */
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Sorts the given array using the QuickSort algorithm.
     * The array is sorted in-place, meaning no additional array is used.
     * 
     * @param array The array of elements to be sorted.
     * @throws IllegalArgumentException if the input array is null or empty.
     */
    public void sort(T[] array) {
        if (array == null || array.length == 0) return;
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursively sorts the portion of the array between the indices low and high
     * using the QuickSort algorithm.
     * 
     * @param array The array to be sorted.
     * @param low The starting index of the portion to be sorted.
     * @param high The ending index of the portion to be sorted.
     */
    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array around a pivot element, which is chosen to be the first element.
     * Elements smaller than the pivot are moved to the left, and those larger than the pivot
     * are moved to the right. The method returns the final position of the pivot.
     * 
     * @param array The array to partition.
     * @param low The starting index of the portion to partition.
     * @param high The ending index of the portion to partition.
     * @return The index where the pivot is placed in the array.
     */
    private int partition(T[] array, int low, int high) {
        T pivot = array[low];
        int left = low + 1;
        int right = high;

        while (true) {
            while (left <= right && comparator.compare(array[left], pivot) <= 0) {
                left++;
            }
            while (right >= left && comparator.compare(array[right], pivot) > 0) {
                right--;
            }
            if (left > right) {
                break;
            }
            swap(array, left, right);
        }
        swap(array, low, right);
        return right;
    }

    /**
     * Swaps the elements at the given indices in the array.
     *
     * @param array The array in which the elements will be swapped.
     * @param i The index of the first element to swap.
     * @param j The index of the second element to swap.
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


