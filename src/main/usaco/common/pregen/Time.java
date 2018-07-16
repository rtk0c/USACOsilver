package usaco.common.pregen;

import java.util.*;
import usaco.common.*;
import usaco.common.utils.*;


public class Time extends DummyBase implements IWithTime, Comparable<Time> {

    public Time(int h, int m, int s) {
        this.hours = h;
        this.minutes = m;
        this.seconds = s;
    }



    @Override
    public String getFormattedOutput() {
        return this.hours + " " + this.minutes + " " + this.seconds;
    } 


    @Override
    public int compareTo(Time t) {
        // if @return is negitive or 0, sort don't do anything
        // if @return is positive, sort swaps the values
        if(this.hours != t.getHours()) {
            return this.hours - t.getHours();
        }
        if(this.minutes != t.getMinutes()) {
            return this.minutes - t.getMinutes();
        }
        return this.seconds - t.getSeconds();
    }

}
