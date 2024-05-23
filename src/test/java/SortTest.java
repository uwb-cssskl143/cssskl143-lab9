/*
 * DO NOT MAKE ANY CHANGES
 */

import org.junit.Assume;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class SortTest {

    @Test
    public void testSwapInteger(){
        //Test the swap method in the Sort class
        Sort sort = new Sort();
        int[] numbers = {1, 2, 3, 4, 5};
        sort.swap(numbers, 0, 4);
        assertArrayEquals("-SORT CLASS: swap method does not swap array elements correctly",new int[]{5, 2, 3, 4, 1}, numbers);
    }

    @Test
    public void testBubbleSortInteger(){
        Sort sort = new Sort();
        int[] numbers = {5, 4, 3, 2, 1};
        sort.bubbleSort(numbers);
        assertArrayEquals("-SORT CLASS: bubbleSort method does not sort integer array correctly",new int[]{1, 2, 3, 4, 5}, numbers);
    }

    @Test
    public void testSwapString(){
        Sort sort = new Sort();
        String[] strings = {"eee", "ddd", "ccc", "bbb", "aaa"};
        try{
            Method method = Sort.class.getMethod("swap", String[].class, int.class, int.class);
            method.invoke(sort, strings, 0, 4);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -SORT CLASS: swap method with String[] parameter not found");
        } catch (Exception e) {
            fail("Unexpected error occurred: " + e.getMessage());
        }
        assertArrayEquals("-SORT CLASS: swap method does not swap String array elements correctly",new String[]{"aaa", "ddd", "ccc", "bbb", "eee"}, strings);
    }

    @Test
    public void testBubbleSortString(){
        Sort sort = new Sort();
        String[] strings = {"eee", "ddd", "ccc", "bbb", "aaa"};
        try {
            Method method = Sort.class.getMethod("bubbleSort", String[].class);
            method.invoke(sort, (Object) strings);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -SORT CLASS: bubbleSort method with String[] parameter not found");
        } catch (Exception e) {
            fail("Unexpected error occurred: " + e.getMessage());
        }
        assertArrayEquals("-SORT CLASS: bubbleSort method does not sort String array correctly",new String[]{"aaa", "bbb", "ccc", "ddd", "eee"}, strings);
    }

    @Test
    public void testSelectionSort(){
        Sort sort = new Sort();
        int[] numbers = {5, 4, 3, 2, 1};
        try {
            Method method = Sort.class.getMethod("selectionSort", int[].class);
            method.invoke(sort, (Object) numbers);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -SORT CLASS: selectionSort method with int[] parameter not found");
        } catch (Exception e) {
            fail("Unexpected error occurred: " + e.getMessage());
        }
        assertArrayEquals("-SORT CLASS: selectionSort method does not sort integer array correctly",new int[]{1, 2, 3, 4, 5}, numbers);
    }

    public void testInsertionSort(){
        Sort sort = new Sort();
        int[] numbers = {5, 4, 3, 2, 1};
        try {
            Method method = Sort.class.getMethod("insertionSort", int[].class);
            method.invoke(sort, (Object) numbers);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -SORT CLASS: insertionSort method with int[] parameter not found");
        } catch (Exception e) {
            fail("Unexpected error occurred: " + e.getMessage());
        }
        assertArrayEquals("-SORT CLASS: insertionSort method does not sort integer array correctly",new int[]{1, 2, 3, 4, 5}, numbers);
    }
}
