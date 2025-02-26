import java.util.Random;
import java.util.Arrays;
/**
 * The Algorithm class provides various sorting algorithms to sort an array of integers.
 * It supports sorting with Radix Sort, Shell Sort, Heap Sort, Insertion Sort, and Java's
 * built-in standard sort. The class also includes utility methods for measuring the
 * execution time of these sorting algorithms.
 * 
 * The array is initialized with random values in the range [-10,000, 10,000].
 */
public class Algorithm {

    /**
     * The array of integers to be sorted.
     */
    private int[] array;
    /**
     * Constructs an Algorithm instance and initializes the array with random values.
     * 
     * @param size The size of the array to be created.
     */
    public Algorithm(int size) {
        // Generate array with random values between -10,000 and 10,000
        Random rand = new Random();
        this.array = new int[size];
        
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(20001) - 10000; // Range from -10000 to 10000
        }
    }
    /**
     * Sorts the array using Java's built-in sort (Dual-Pivot Quicksort for primitives).
     */
    public void standardSort() {
        Arrays.sort(array);
    }
    
    /**
     * Sorts the array using the Radix Sort algorithm, handling both positive and
     * negative integers.
     */
    public void radixSort() {
        int max = getMax(array);
        int min = getMin(array); // Get minimum value to handle negative numbers
        for (int exp = 1; (max - min) / exp > 0; exp *= 10) {
            countSort(array, exp, min);
        }
    }
    /**
     * Finds the maximum value in the array.
     * 
     * @param array The array to search.
     * @return The maximum value in the array.
     */
    private int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    /**
     * Finds the minimum value in the array.
     * 
     * @param array The array to search.
     * @return The minimum value in the array.
     */
    private int getMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Performs counting sort on the array based on the specified digit position.
     * 
     * @param array The array to be sorted.
     * @param exp   The digit position to sort by (e.g., 1 for units, 10 for tens).
     * @param min   The minimum value in the array for normalization.
     */
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

    /**
     * Sorts the array using the Shell Sort algorithm.
     */
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
    
    /**
     * Sorts the array using the Heap Sort algorithm.
     */
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

    /**
     * Performs heapify operation for Heap Sort.
     * 
     * @param array The array to be heapified.
     * @param n     The size of the heap.
     * @param i     The root index to heapify.
     */
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
    
    /**
     * Sorts the array using the Insertion Sort algorithm.
     */
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
    
    /**
     * Measures and prints the time taken by each sorting algorithm to sort the array.
     */
    public void algorithmsTimeSpends() {
        clearTerminal();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║        Sorting Time Spends For Different Algorithms          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        ArrayComparator comparator = new ArrayComparator();
        // Measure and print time for Radix Sort
        int[] copyArray = array.clone();
        long startTime = System.nanoTime();
        radixSort();
        long endTime = System.nanoTime();
        int[] sortedarray = array.clone();
        Arrays.sort(sortedarray);
        if(comparator.compare(array, sortedarray) == 1)
            System.out.println("Radix Sort took:        " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds (Approved)");
        else
            System.out.println("Radix Sort took:        " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds");
        
        // Measure and print time for Shell Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        shellSort();
        endTime = System.nanoTime();
        sortedarray = array.clone();
        Arrays.sort(sortedarray);
        if(comparator.compare(array, sortedarray) == 1)
            System.out.println("Shell Sort took:        " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds (Approved)");
        else
            System.out.println("Shell Sort took:        " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds");
        
        // Measure and print time for Heap Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        heapSort();
        endTime = System.nanoTime();
        sortedarray = array.clone();
        Arrays.sort(sortedarray);
        if(comparator.compare(array, sortedarray) == 1)
            System.out.println("Heap Sort took:         " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds (Approved)");
        else
            System.out.println("Heap Sort took:         " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds");
        
        // Measure and print time for Insertion Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        insertionSort();
        endTime = System.nanoTime();

        sortedarray = array.clone();
        Arrays.sort(sortedarray);
        if(comparator.compare(array, sortedarray) == 1)
            System.out.println("Insertion Sort took:    " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds (Approved)");
        else
            System.out.println("Insertion Sort took:    " + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds");

        // Measure and print time for Standard Java Sort
        array = copyArray.clone();
        startTime = System.nanoTime();
        standardSort();
        endTime = System.nanoTime();
        sortedarray = array.clone();
        Arrays.sort(sortedarray);
        if(comparator.compare(array, sortedarray) == 1)
            System.out.println("Standard Java Sort took:" + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds (Approved)");
        else
            System.out.println("Standard Java Sort took:" + padLeft(Long.toString(endTime - startTime), 9) + " nanoseconds");
    }

    /**
     * Pads the given string with spaces on the left to make it of the specified length.
     * 
     * @param str    The string to pad.
     * @param length The desired length of the resulting string.
     * @return The padded string.
     */
    public static String padLeft(String str, int length) {
        return String.format("%" + length + "s", str);
    }
    /**
     * Clears the terminal screen.
     * This method handles both Windows and Unix-based systems.
     */
    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
