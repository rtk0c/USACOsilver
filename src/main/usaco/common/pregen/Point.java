package usaco.common.pregen;

import java.util.*;
import usaco.common.*;
import usaco.common.utils.*;


public class Point extends DummyBase implements IWithCoord, Comparable<Point> {

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }



    @Override
    public String getFormattedOutput() {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public int compareTo(Point p) {
        // if @return is negitive or 0, sort don't do anything
        // if @return is positive, sort swaps the values
        if(this.x != p.getX()) {
            return this.x - p.getX();
        }
        return this.y - p.getY();
    }

}
