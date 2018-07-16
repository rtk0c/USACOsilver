package usaco.common;

import java.util.*;
import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public abstract class DummyBase implements IStringSerializable, IWithCoord, IWithTime, IConsoleOutput {

    protected int x;
    protected int y;

    protected int hours;
    protected int minutes;
    protected int seconds;
    protected int time6d;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getX() { return this.x; }
    @Override
    public int getY() { return this.y; }


    @Override
    public int getHours() { return this.hours; }
    @Override
    public int getMinutes() { return this.minutes; }
    @Override
    public int getSeconds() { return this.seconds; }


    @Override
    public int get6dTime() {
        return TimeUtils.get6dTime(this.hours, this.minutes, this.seconds);
    }

    @Override
    public String formatTime(String separator) {
        return TimeUtils.formatTime(this.hours, this.minutes, this.seconds, separator);
    }


    @Override
    public Object[] getData() {
        return new Object[]{};
    }

    @Override
    public Object getFormattedData() {
        return new Object();
    }

    @Override
    public String getFormattedOutput() {
        return "";
    }

    @Override 
    public void printData() {
        System.out.println(this.getFormattedOutput());
    }





    /*
    protected static ListPP<DummyBase> instances = new ArrayListPP<DummyBase>();

    public static void putInstance(DummyBase instance) {
        instances.add(instance);
    }
    public static void printAllData() {
        for(int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
        }
    }
    */

}
