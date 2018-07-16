package usaco.common.utils;

import java.util.*;


public class ListUtils {

    private ListUtils() {
    }



    /**
     * Regular bubble sort.
     * <b>WARNING: this method changes the array</b>
     *
     * @param a The array gets sorted, result is the parameter.
     */
    public static void bubbleSort(int[] a) {
        boolean swaped;
        do {
            swaped = false;

            for(int i = 0; i < a.length-1; i++) {
                if(a[i+1] < a[i]) {
                    int tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;

                    swaped = true;
                }
            }
        } while(swaped);
    }






    /**
     * For primitive type arrays, use 
     * Arrays.toString(...);
     */
    public static void printArray(Object[] array) {
        StringBuilder out = new StringBuilder();
        out.append("[");

        for(int i = 0; i < array.length; i++) {
            out.append(array[i]);
            out.append(", ");
        }
        out.append("]");

        System.out.println(out.toString());
    }

}