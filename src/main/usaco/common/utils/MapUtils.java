package usaco.common.utils;

import java.util.*;


public class MapUtils {

    private MapUtils() {
    }



    /**
     * Seaerch for the index that of the input in an array. 
     */
    public static int searchFor(Object target, Object[] from) {
        for(int i = 0; i < from.length; i++) {
            if(from[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }
    /**
     * Similar to searchFor(...), but gives you the coorsponding
     * value inside another array instead of the index.
     */
    public static Object getByKey(Object key, Object[] keys, Object[] values) {
        return values[searchFor(key, keys)];
    }

}