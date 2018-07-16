package usaco.common.utils;

import java.util.*;


public class MathUtils {

    private MathUtils() {
    }



    public static int max(int a, int b) {
        return a > b ? a : b;
    }
    public static int max(int... a) {
        int maximum = 0;
        for(int i = 0; i < a.length; i++) {
            if(a[i] > maximum) {
                maximum = a[i];
            }
        }

        return maximum;
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }
    public static int min(int... a) {
        int minimum = a[0];
        for(int i = 0; i < a.length; i++) {
            if(a[i] < minimum) {
                minimum = a[i];
            }
        }

        return minimum;
    }


    public static int pow(int base, int exponent) {
        return (int) Math.pow(base, exponent);
    }
    public static int pow(int base) {
        return base * base;
    }


    /**
     * Checks for how many combinations of two squares
     * that can make x.
     */
    public static int combsSquareAB(int x) {
        int sqrtX = (int) Math.sqrt(x);
        int count = 0;

        for(int a = 0; a <= sqrtX; a++) {
            for(int b = 0; b <= sqrtX; b++) {
                if(a*a + b*b == x) {
                    count++;
                }
            }
        }

        return count;
    }

}