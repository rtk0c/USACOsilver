package usaco.common.pregen;

import java.util.*;
import usaco.common.*;
import usaco.common.utils.*;


public interface ListPP<E> extends List<E> {

    void sort();
    void reverse();

    E max();
    E min();

}
