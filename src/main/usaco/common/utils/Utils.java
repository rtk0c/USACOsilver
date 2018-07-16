package usaco.common.utils;

import java.util.*;
import qwefbine.lib.flog4j.Logger;
import qwefbine.lib.flog4j.ConsoleLogger;
import qwefbine.lib.flog4j.FakeLogger;
import usaco.common.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;

public class Utils {

    private Utils() {
    }



    public static void loop(int stop, IIntConsumer func, int start, int step) {
        for(int i = start; i <= stop; i += step) {
            func.run(i);
        }
    }

    private static Logger changableLogger;

    public static void resetLogger(String name, String group, boolean logs) {
        if(logs) {
            changableLogger = new ConsoleLogger(name, group);
        } else {
            changableLogger = new FakeLogger();  // a fake logger
        }
    }
    public static Logger getLogger() {
        return changableLogger;
    }


    public static void println(Object t) {
        System.out.println(t);
    }
    public static void println(int t) {
        System.out.println(t);
    }
    public static void println(long t) {
        System.out.println(t);
    }
    public static void println(boolean t) {
        System.out.println(t);
    }

}