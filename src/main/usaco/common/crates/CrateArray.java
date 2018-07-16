package usaco.common.crate;

import java.util.*;
import usaco.common.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;

public class CrateArray<E> {

    private int capacity;
    private int nextInsertion = 0;
    private Object[] elementData;

    public CrateArray(int capacity) {
        this.capacity = capacity;
        this.elementData = new Object[capacity];
    }

    public void append(E e) {
        elementData[nextInsertion++] = e;
    }
    public void removeEnd() {
        elementData[--nextInsertion] = null;
    }



    /*public E at(int i) {
        return this.elementData[i];
    }
    public E first() {
        return this.elementData[0];
    }
    public E last() {
        return this.elementData[capacity-1];
    }*/
    
    
}