package twitterpack;

import java.util.Comparator;

public class MyQuickSort<T> {
    private Comparator<T> comparator;

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sort(T[] array) {
        if (array == null || array.length == 0) return;
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

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

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


