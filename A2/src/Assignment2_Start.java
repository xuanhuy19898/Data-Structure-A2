/**I, Xuan Huy Pham, 000899551, certify that this material is my original work. No other person's work has been used without suitable acknowledgment and I have not made my work available to anyone else.
 */

/**
 * Assignment 2
 *
 * a. list in order (fastest to slowest) with 20 elements
 * shellSort (dSort)
 * mergeSort (eSort)
 * radixSort (gSort)
 * quickSort (fSort)
 * bubbleSort (aSort)
 * selectionSort (bSort)
 * insertionSort (cSort)
 *
 * b. list in order (fastest to slowest) with 50 000 elements
 * mergeSort (eSort)
 * shellSort (dSort)
 * radixSort (gSort)
 * bubbleSort (aSort)
 * insertionSort (cSort)
 * selectionSort (bSort)
 * quickSort (fSort)
 *
 *c. at 50000 elements, mergeSort has the lowest instruction set time.
 * it impacts the selection of the fastest algorithm because its worst-case time can degrade to O(n^2) and make it only suitable for large datasets
 *
 *d. table
 *==========================================================
 * Sort                 Sort Algorithm            Big O         Big O
 Algorithm                Name                   (time)       (space)
 * -----------------------------------------------------------------
 * aSort                bubbleSort               O(n^2)        O(1)
 * bSort                selectionSort            O(n^2)        O(1)
 * cSort                insertionSort            O(n^2)        O(1)
 * dSort                shellSort                O(n log n)    O(1)
 * eSort                mergeSort                O(n log n)    O(n)
 * fSort                quickSort                O(n^2)        O(log n)
 * gSort                radixSort                O(n*k)        O(n+k)
 * ---------------------------------------------------------------
 *
 */

import java.util.Arrays;
import java.util.Random;
/**
 * @author Xuan Huy Pham, 000899551
 */
public class Assignment2_Start {
    private static long startNanoTime;
    private static long endNanoTime;

    /***
     * this method generates an array of random integers
     * @param size the size of the array to generate
     * @return the array
     */
    private static int[] generateRandomIntArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size * 10);
            //generate numbers up to 10 times the size of the array
        }
        return arr;
    }

    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param array where elements are to be swapped.
     * @param a The subscript of the first element.
     * @param b The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }


    /*-------------------------------a Sort-----------------------------------------*/
    /***
     * ---aSort / bubble sort------
     * sort an array using bubble sort
     * it iterates through the array multiple times,
     * comparing adjacent elements and swapping them if they are in the wrong order
     * @param array the array to sort
     * @return the number of comparisons
     */
    private static long aSort(int[] array) {
        int lastPos;
        int index;
        long totalComparisons = 0;
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            for (index = 0; index <= lastPos - 1; index++) {
                if (array[index] > array[index + 1]) {
                    swap(array, index, index + 1);
                }
                totalComparisons++;
            }
        }
        return totalComparisons;
    }

    /*-------------------------------b Sort-----------------------------------------*/
    /***
     * ---bSort / selection sort------
     * sort an array using selection sort
     * it divides the array into two parts: sorted and unsorted
     * in each iteration, find the minimum element from the unsorted part and moves it to the sorted part
     * @param array  the array
     * @return the number of comparisons
     */
    private static long bSort(int[] array) {
        int startScan;
        int index;
        int minIndex;
        int minValue;
        long totalComparisons = 0;
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            minIndex = startScan;
            minValue = array[startScan];
            for (index = startScan + 1; index < array.length; index++) {
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
                totalComparisons++;
            }
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
        return totalComparisons;
    }


    /*-------------------------------c Sort-----------------------------------------*/
    /***
     * ---cSort / insert sort------
     * sort an array using insertion sort
     * divides the array into 2 parts: sorted and unsorted
     * it will iterate through the unsorted part, selecting each element and inserting it into its correct position in the sorted part
     * @param array the array
     * @return the number of comparisons
     */
    private static long cSort(int[] array) {
        int unsortedValue;
        int scan;
        long totalComparisons = 0;
        for (int index = 1; index < array.length; index++) {
            unsortedValue = array[index];
            scan = index;
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                array[scan] = array[scan - 1];
                scan--;
                totalComparisons++;
            }
            array[scan] = unsortedValue;
        }
        return totalComparisons;
    }


    /*-------------------------------d Sort-----------------------------------------*/
    /***
     * ---dSort / shell sort------
     * sorts an array using shell sort
     * the elements are sorted at larger intervals initially,
     * and then the interval is gradually decreased until it becomes 1, at which point the array is fully sorted
     * @param array the array
     * @return the number of comparisons
     */
    private static long dSort(int[] array) {
        int gap, temp, j;
        long totalComparisons = 0;
        for (gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                temp = array[i];
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                    totalComparisons++;
                }
                array[j] = temp;
            }
        }
        return totalComparisons;
    }


    /*-------------------------------e Sort-----------------------------------------*/
    /***
     * ---eSort / Merge sort------
     * sort an array using merge sort
     * this sort divides the array into two halves, sorts each half recursively, and then merges the sorted halves
     * @param array the array
     * @return the number of comparisons
     */
    private static long eSort(int[] array) {
        int length = array.length;
        int[] temp = new int[length];
        return doMergeSort(array, temp, 0, length - 1);
    }

    /***
     * do merge sort on a portion of the array
     * it recursively divides the array into halves until each half contains only one element, then merges the sorted halves back together
     * @param array the array to sort
     * @param temp temporary array to store intermediate results
     * @param lowerIndex the lower index of the portion to sort
     * @param higherIndex the higher index of the portion to sort
     * @return the number of comparisions
     */
    private static long doMergeSort(int[] array, int[] temp, int lowerIndex, int higherIndex) {
        long totalComparisons = 0;
        if (lowerIndex < higherIndex) {
            //find the middle index
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            //recursively sort the left and right halves
            totalComparisons += doMergeSort(array, temp, lowerIndex, middle);
            totalComparisons += doMergeSort(array, temp, middle + 1, higherIndex);
            //merge the sorted halves
            totalComparisons += mergeParts(array, temp, lowerIndex, middle, higherIndex);
        }
        return totalComparisons;
    }

    /***
     * merge 2 sorted portions of an array
     * merges these two sorted portions into a single sorted portion in the array
     * @param array the array containing the portions to merge
     * @param temp temporary array to store intermediate results
     * @param lowerIndex the lower index of the 1st portion
     * @param middle the middle index separating the portions
     * @param higherIndex the higher index of the 2nd portion
     * @return the number of comparisons
     */
    private static long mergeParts(int[] array, int[] temp, int lowerIndex, int middle, int higherIndex) {
        long totalComparisons = 0;
        //copy the contents of the array to the temporary array
        for (int i = lowerIndex; i <= higherIndex; i++) {
            temp[i] = array[i];
        }

        //merge the 2 portions back into the original array
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (temp[i] <= temp[j]) {
                array[k] = temp[i];
                i++;
            } else {
                array[k] = temp[j];
                j++;
            }
            k++;
            totalComparisons++;
        }
        while (i <= middle) {
            array[k] = temp[i];
            k++;
            i++;
        }
        return totalComparisons;
    }


    /*-------------------------------f Sort-----------------------------------------*/
    /***
     * ---fSort / quick sort------
     * sort an array using quick sort
     * it selects a 'pivot' element from the array and partitioning the other elements into two sub-arrays according to whether
     * they are less than or greater than the pivot
     * the sub-arrays are then recursively sorted
     * @param array the array
     * @return the number of comparisons
     */
    private static long fSort(int[] array) {
        long totalComparisons = 0;
        totalComparisons = doQuickSort(array, 0, array.length - 1);
        return totalComparisons;
    }

    /***
     * do quick sort on a portion of the array
     * @param array the array to sort
     * @param start the starting index of the portion to sort
     * @param end the ending index of the portion to sort
     * @return the number of comparisons
     */
    private static long doQuickSort(int[] array, int start, int end) {
        long totalComparisons = 0;

        //if the start index is less than the end index, there is still data to sort
        if (start < end) {
            int pivot = part1(array, start, end);
            totalComparisons += doQuickSort(array, start, pivot - 1);
            totalComparisons += doQuickSort(array, pivot + 1, end);
        }
        return totalComparisons;
    }


    /***
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array the array to partition
     * @param start the starting index of the portion to partition
     * @param end the ending index of the portion to partition
     * @return the index of the pivot element
     */
    private static int part1(int[] array, int start, int end) {
        int pivot = array[end];
        int i = (start - 1);
        long totalComparisons = 0;
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
            totalComparisons++;
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    /*-------------------------------g Sort-----------------------------------------*/
    /***
     * ---gSort / radix sort------
     * sort an array using radix sort
     * sorts data with integer keys by grouping keys by the individual digits
     * which share the same position and value
     * @param array the array to sort
     * @return the number of comparisons made
     */
    private static long gSort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt(); //find the max element in the array
        long totalComparisons = 0;
        for (int exp = 1; max / exp > 0; exp *= 10) {
            totalComparisons += countSort(array, exp);
        }
        return totalComparisons;
    }

    /***
     * count the sort for radix sort
     * @param array the array to sort
     * @param exp the current exponent for digit extraction
     * @return the number of comparisons made during sorting
     */
    private static long countSort(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];
        long totalComparisons = 0;
        for (int i = 0; i < array.length; i++) {
            count[(array[i] / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
            totalComparisons++;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
        return totalComparisons;
    }


    /**
     * The main method will run through all of the Sorts for the prescribed sizes and produce output for parts A and B
     *
     * Part C should be answered at the VERY TOP of the code in a comment
     * @param args
     */
    public static void main(String[] args) {
        int[] arraySizes = {20, 100, 10000, 50000};
        int runs = 5;

        for (int arraySize : arraySizes) {
            System.out.printf("Comparison for array size of %d (Averaged over %d runs)%n", arraySize, runs);
            System.out.println("==========================================================================================");
            System.out.printf("%-10s %24s %24s %24s%n", "Sort", "Execution Time (ns)", "Compares", "Basic Step (ns)");
            System.out.println("------------------------------------------------------------------------------------------");

            for (int i = 0; i < runs; i++) {
                int[] array = generateRandomIntArray(arraySize);
                long totalNanoTime = 0;
                int totalComparisons = 0;

                //aSort
                startNanoTime = System.nanoTime();
                totalComparisons += aSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("aSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                //bsort
                startNanoTime = System.nanoTime();
                totalComparisons += bSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("bSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                //cSort
                startNanoTime = System.nanoTime();
                totalComparisons += cSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("cSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                //dSort
                startNanoTime = System.nanoTime();
                totalComparisons += dSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("dSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                //eSort
                startNanoTime = System.nanoTime();
                totalComparisons += eSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("eSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                //fSort
                startNanoTime = System.nanoTime();
                totalComparisons += fSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("fSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                //gSort
                startNanoTime = System.nanoTime();
                totalComparisons += gSort(array.clone());
                endNanoTime = System.nanoTime();
                totalNanoTime += (endNanoTime - startNanoTime);
                printResults("gSort", endNanoTime - startNanoTime, totalComparisons, (endNanoTime - startNanoTime) / (double) totalComparisons, array.length);

                System.out.println();
            }
            System.out.println();
        }
    }


    /***
     * print the results of a sorting algorithm execution
     * @param name          name of the sorting algorithm
     * @param time          the execution time in ns
     * @param comparisons   the number of comparisons made during the sort
     * @param basicStep     the execution time per comparison
     * @param arrayLength   the length of the array being sorted
     */
    private static void printResults(String name, long time, int comparisons, double basicStep, int arrayLength) {
        double avgComparisons = comparisons > 0 ? (double) comparisons / arrayLength : 0.0;
        System.out.printf("%-15s %20d %20d %25.2f%n", name, time, comparisons, avgComparisons);
    }



    /**
     * A demonstration of recursive counting in a Binary Search
     * @param array - array to search
     * @param low - the low index - set to 0 to search whiole array
     * @param high - set to length of array to search whole array
     * @param value - item to search for
     * @param count - Used in recursion to accumulate the number of comparisons made (set to 0 on initial call)
     * @return
     */
    private static int[] binarySearchR(int[] array, int low, int high, int value, int count)
    {
        int middle;     // Mid point of search

        // Test for the base case where the value is not found.
        if (low > high)
            return new int[] {-1,count};

        // Calculate the middle position.
        middle = (low + high) / 2;

        // Search for the value.
        if (array[middle] == value)
            // Found match return the index
            return new int[] {middle, count};
        else if (value > array[middle])
            // Recursive method call here (Upper half of remaining data)
            return binarySearchR(array, middle + 1, high, value, count+1);
        else
            // Recursive method call here (Lower half of remaining data)
            return binarySearchR(array, low, middle - 1, value, count+1);
    }
}
