/**
 * This class implements multiple sort algorithms.
 *
 * @author (your name)
 * @version (CSSSKL 143)
 */

public class Sort {
    public static final int SIZE = 10;

    public static void main(String[] args) {
        int[] bubbleArray = new int[SIZE];
        int[] selectionArray = new int[SIZE];
        int[] insertionArray = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            bubbleArray[i] = (int) (Math.random() * 52);
            selectionArray[i] = (int) (Math.random() * 52);
            insertionArray[i] = (int) (Math.random() * 52);
        }

        System.out.println("Array before bubble sort:");
        System.out.println(Arrays.toString(bubbleArray));
        bubbleSort(bubbleArray);
        System.out.println("Array after bubble sort:");
        System.out.println(Arrays.toString(bubbleArray));
        System.out.println();

//        System.out.println("Array before selection sort:");
//        System.out.println(Arrays.toString(selectionArray));
//        selectionSort(selectionArray);
//        System.out.println("Array after selection sort:");
//        System.out.println(Arrays.toString(selectionArray));
//        System.out.println();
//
//        System.out.println("Array before insertion sort:");
//        System.out.println(Arrays.toString(insertionArray));
//        insertionSort(insertionArray);
//        System.out.println("Array after insertion sort:");
//        System.out.println(Arrays.toString(insertionArray));

        // TODO Test your string sort here
    }

    /**
     *
     * @param numbers
     */
    public static void bubbleSort(int[] numbers) {
        // Implement your sort, call a helper swap method
    }

    /**
     *
     *
     * @param numbers
     * @param indexA
     * @param indexB
     */
    public static void swap(int[] numbers, int indexA, int indexB) {
        // swap the elements at indexA and indexB
    }

    // selection sort for ints
    public static void selectionSort(int[] numbers) {
        // Implement your sort, call swapSelection(int[] intList, int i, int nextMin)
    }

    public static int findSmallest(int[] arr, int begin, int end) {
        int minIndex = begin;
        int minValue = arr[begin];
        for (int i = begin + 1; i < end; i++) {
            if (arr[i] < minValue) {
                minIndex = i;
                minValue = arr[i];
            }
        }
        return minIndex;
    }

    /**
     *
     * @param numbers
     */
    public static void insertionSort(int[] numbers) {
        // Implement your insertion sort
    }
}
