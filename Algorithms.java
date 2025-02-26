import java.util.Random;
import java.util.Arrays;

public class Algorithm {
    
    private int[] array;
    
    public Algorithm(int size) {
        // Generate array with random values between -10,000 and 10,000
        Random rand = new Random();
        this.array = new int[size];
        
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(20001) - 10000; // Range from -10000 to 10000
        }
    }

    public void standardSort() {
        // Sort the array using Java's built-in sort (Dual-Pivot Quicksort for primitives)
        Arrays.sort(array);
    }
    
    // Method for Radix Sort
    public void radixSort() {
        int max = getMax(array);
        int min = getMin(array); // Get minimum value to handle negative numbers
        for (int exp = 1; (max - min) / exp > 0; exp *= 10) {
            countSort(array, exp, min);
        }
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private int getMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    private void countSort(int[] array, int exp, int min) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[19]; // Range from -10000 to 10000, so 19 possible remainders

        // Count occurrences of each digit
        for (int i = 0; i < n; i++) {
            int digit = (array[i] - min) / exp % 10 + 9; // Normalize negative numbers by offsetting with +9
            count[digit]++;
        }

        // Cumulative count
        for (int i = 1; i < 19; i++) {
            count[i] += count[i - 1];
        }

        // Place the elements in the correct position in the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] - min) / exp % 10 + 9; // Normalize again
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copy the sorted array into the original array
        System.arraycopy(output, 0, array, 0, n);
    }

    // Method for Shell Sort
    public void shellSort() {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j = i;
                while (j >= gap && array[j - gap] > temp) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = temp;
            }
        }
    }
    
    // Method for Heap Sort
    public void heapSort() {
        int n = array.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
        
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }
        
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);
        }
    }
    
    // Method for Insertion Sort
    public void insertionSort() {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
    
    // Method to print time taken for sorting algorithms
    public void algorithmsTimeSpends() {
        // Measure and print time for Radix Sort
        int[] copyArray = array.clone();
        long startTime = System.nanoTime();
        radixSort();
        long endTime = System.nanoTime();
        System.out.println("Radix Sort took:         " + (endTime - startTime) + " nanoseconds");
        
        // Measure and print time for Shell Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        shellSort();
        endTime = System.nanoTime();
        System.out.println("Shell Sort took:         " + (endTime - startTime) + " nanoseconds");
        
        // Measure and print time for Heap Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        heapSort();
        endTime = System.nanoTime();
        System.out.println("Heap Sort took:          " + (endTime - startTime) + " nanoseconds");
        
        // Measure and print time for Insertion Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        insertionSort();
        endTime = System.nanoTime();
        System.out.println("Insertion Sort took:     " + (endTime - startTime) + " nanoseconds");

        // Measure and print time for Standard Java Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        standardSort();
        endTime = System.nanoTime();
        System.out.println("Standard Java Sort took: " + (endTime - startTime) + " nanoseconds");
    }
}
