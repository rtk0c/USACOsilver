package usaco.common.pregen;

import java.util.*;
import usaco.common.*;
import usaco.common.utils.*;


public class ArrayListPP<E extends Comparable<E>> 
               extends ArrayList<E>
               implements ListPP<E> {

    public static final long serialVersionUID = -1L;

    public ArrayListPP() {
        super();
    }
    public ArrayListPP(int initialCapacity) {
        super(initialCapacity);
    }
    public ArrayListPP(Collection<? extends E> collection) {
        super(collection);
    }


    /**
     * Find the maximum element in the whole <tt>ArrayList</tt>.
     * based on Comparable.compareTo()
     * 
     * @return the maximum element overall
     */
    @Override
    public E max() {
        // choose the element only if it's greater than old one
        return compareAll(1);
    }

    /**
     * Find the minimum element in the whole <tt>ArrayList</tt>
     * based on Comparable.compareTo()
     * 
     * @return the minimum element overall
     */
    @Override
    public E min() {
        // choose the element only if it's smaller than old one
        return compareAll(-1);
    }

    private E compareAll(int condition) {
        // varible size is private, so the 
        // only way to do it is elementData.length
        E result = this.get(0);
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i) == null) continue;
            // greater than (1), smaller than (-1), or equal to (0)
            if(this.get(i).compareTo(result) == condition)
                result = this.get(i);
        }
        return result;
    }


    /**
     * Binary search for the element that matches to E target.
     * 
     * @param target The matching target
     * 
     * @return The index of first element that matches the target
     */
    public int indexOf(E target) {
        int l = 0;
        int h = this.size() - 1;
        int mid; //basicly the index that is checking

        while(l <= h) {
            mid = (l + h) / 2;

            if(this.get(mid).compareTo(target) == 0) {
                return mid;
            } else if(this.get(mid).compareTo(target) == -1) {
                l = mid + 1;
            } else if(this.get(mid).compareTo(target) == 1) {
                h = mid - 1;
            }
        }

        return -1;
    }


    @Override
    public void sort() {
        //TODO quicksort self
        Collections.sort(this);
    }

    @Override
    public void reverse() {
        //TODO reserse the list
        Collections.reverse(this);
    }

}
