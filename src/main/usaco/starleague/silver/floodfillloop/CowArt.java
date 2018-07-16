import java.util.*;

public class CowArt {

    private static char[][] paint;
    private static int paintLength;

    // (int) 'R' 'G' 'B' < 91
    // (int) 'x' 'y' 'z' > 96
    public static void main(String[] args) {
        Utils.resetLogger("CowArt", "floodfillloop", false);
        Scanner consoleIn = new Scanner(System.in);

        paintLength = consoleIn.nextInt();
        //vetical (first index) is y, second index is x
        //[[0, 0, 0],
        // [0, 0, 0],
        // [0, 0, 0]]
        paint = new char[paintLength][paintLength];

        Utils.getLogger().info("paintLength: " + paintLength);

        for(int i = 0; i < paintLength; i++) {
            paint[i] = consoleIn.next().toCharArray();
        }
        Utils.getLogger().info("paint: " + printArray(paint));

        int humanRegions = count(new IConverter() {
            @Override
            public char convertFrom(char c) {
                return c == 'R' ? 'r' : c == 'G' ? 'g' : c == 'B' ? 'b' : '0';
            }
        });

        // reset 'r' or 'g' to 'RG', 'b' to 'B'
        for(int i = 0; i < paintLength; i++) {
            for(int j = 0; j < paintLength; j++) {
                paint[j][i] = paint[j][i] == 'r' || paint[j][i] == 'g' ? 'Y' : paint[j][i] == 'b' ? 'B' : '0';
            }
        }
        Utils.getLogger().info("reset paint: " + printArray(paint));

        int cowRegions = count(new IConverter() {
            @Override
            public char convertFrom(char c) {
                return c == 'Y' ? 'y' : c == 'B' ? 'b' : '0';
            }
        });

        Utils.getLogger().info("marked paint: " + printArray(paint));
        Utils.println(humanRegions + " " + cowRegions);

        consoleIn.close();
    }

    private static int count(IConverter converter) {
        int regions = 0;
        for(int i = 0; i < paintLength; i++) {
            for(int j = 0; j < paintLength; j++) {
                //Utils.getLogger().debug("at x: " + i + " y: " + j + " current: " + paint[j][i]);

                // paint[j][i] is a capital letter
                if((int) paint[j][i] < 91) {
                    floodfillWs(i, j, paint[j][i], converter.convertFrom(paint[j][i]));
                    regions++;
                }
            }
        }

        return regions;
    }

    private static void floodfillWs(int x, int y, char checkIs, char replaceWith) {
        paint[y][x] = replaceWith;

        boolean doesMarked;
        do {
            doesMarked = false;

            for(int i = 0; i < paintLength; i++) {
                for(int j = 0; j < paintLength; j++) {
                    // paint[j][i] is a capital letter
                    if((int) paint[j][i] < 91 && paint[j][i] == checkIs && hasSameNeghibor(i, j, replaceWith)) {
                        Utils.getLogger().info("at x: " + i + " y: " + j + " current: " + paint[j][i] + "   and set to: " + replaceWith);

                        paint[j][i] = replaceWith;
                        doesMarked = true;
                    }
                }
            }

        } while(doesMarked);
    }

    private static boolean hasSameNeghibor(int x, int y, char sameAs) {
        final int[] DX = new int[]{-1, 0, 1, 0};
        final int[] DY = new int[]{0, -1, 0, 1};

        for(int i = 0; i < DX.length; i++) {
            if(x + DX[i] < 0 || x + DX[i] >= paintLength ||
               y + DY[i] < 0 || y + DY[i] >= paintLength) {
                   continue;
            }

            if(paint[y + DY[i]][x + DX[i]] == sameAs) {
                Utils.getLogger().debug("detected neghibor: " + (x + DX[i]) + ", " + (y + DY[i]) + "   ");
                return true;
            }
        }

        return false;
    }



    private static String printArray(char[][] a) {
        StringBuilder output = new StringBuilder(a.length);

        output.append("\n");
        for(int i = 0; i < a.length; i++) {
            output.append(Arrays.toString(a[i]));
            output.append(", \n");
        }

        return output.toString();
    }

}

interface IConverter {

    char convertFrom(char c);

}









//////////////////////////////////////////////
//                WARNING!!!                //
//                                          //
//                WARNING!!!                //
//                                          //
//                WARNING!!!                //
//                                          //
//                WARNING!!!                //
//                                          //
//////////////////////////////////////////////
//-----TEN MILLION LINE DEBUGGER BELOW------//
//--------NOT THE PROBLEM SOLUTION----------//
//////////////////////////////////////////////
//                                          //
//                WARNING!!!                //
//                                          //
//                WARNING!!!                //
//                                          //
//                WARNING!!!                //
//                                          //
//                WARNING!!!                //
//////////////////////////////////////////////









/*
import java.utils.*;
*/


class Utils {

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

class MathUtils {

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

class MapUtils {

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

class ListUtils {

    private ListUtils() {
    }



    /**
     * Regular bubble sort.
     * <b>WARNING: this method changes the array</b>
     *
     * @param a The array gets sorted, result is the parameter.
     */
    public static void bubbleSort(int[] a) {
        int swaps;
        do {
            swaps = 0;

            for(int i = 0; i < a.length-1; i++) {
                if(a[i+1] < a[i]) {
                    int tmp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = tmp;

                    swaps++;
                }
            }
        } while(swaps > 0);
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

class TimeUtils {

    private TimeUtils() {
    }



        public static int get6dTime(int hours, int minutes, int seconds) {
        /*
        13:30:06
        ^^ -- --  * 10000  //four 0s

        13:30:06
           ^^ --  * 100    //two 0s

        13:30:06
              ^^  *1       //regular seconds
        */
        return hours * 10000 + minutes * 100 + seconds;
    }

    public static String formatTime(int time6d) {
        return formatTime(time6d / 10000, (time6d / 100) % 100, time6d % 100, " ");
    }

    public static String formatTime(int hours, int minutes, int seconds, String separator) {
        return hours + separator + minutes + separator + seconds;
    }



    /** Only [hours, minutes] */
    public static int[] addHourMinute(int[] timeA, int[] timeB) {
        int[] result = new int[2];
        result[0] = timeA[0] + timeB[0];
        result[1] = timeA[1] + timeB[1];

        while(result[1] >= 60) {
            result[1] -= 60;
            result[0]++;
        }

        return result;
    }
    /** Only [hours, minutes] */
    public static int[] subtractHourMinute(int[] timeA, int[] timeB) {
        int[] result = new int[2];

        result[0] = timeA[0] - timeB[0];
        result[1] = timeA[1] - timeB[1];

        if(result[1] < 0) {
            result[0]--;
            result[1] += 60;
        }

        return result;
    }

    /**
     * Convert a time int[] to a single integer represents seconds
     * that is sum of all values
     * 
     * @param time A time that is [hours, minutes, seconds].
     * 
     * @return A single integer represents seconds.
     */
    public static int convertToSeconds(int[] time) {
        int totalSecs = 0;

        totalSecs += time[0] * 3600;
        totalSecs += time[1] * 60;
        totalSecs += time[2];

        return totalSecs;
    }

    public static int[] convertToTime(int seconds) {
        int[] time = new int[3];

        //take out hours
        while(seconds > 3600) {
            time[0]++;
            seconds -= 3600;
        }
        //take out minutes
        while(seconds > 60) {
            time[1]++;
            seconds -= 60;
        }
        time[2] = seconds;

        return time;
    }
    
}


/*
import java.util.*;
*/



class ArrayListPP<E extends Comparable<E>> extends ArrayList<E> implements ListPP<E> {

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
            // 
            if(this.get(i).compareTo(result) == condition)
                result = this.get(i);
        }
        return result;
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





interface ListPP<E> extends List<E> {

    void sort();
    void reverse();

    E max();
    E min();

}

class MethodNotSupportedException extends RuntimeException {

    public static final long serialVersionUID = -1L;

    public MethodNotSupportedException() {
        super();
    }
    public MethodNotSupportedException(String reason) {
        super(reason);
    }

}



class Point extends DummyBase implements IWithCoord, Comparable<Point> {

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

class Time extends DummyBase implements IWithTime, Comparable<Time> {

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

abstract class DummyBase implements IStringSerializable, IWithCoord, IWithTime, IConsoleOutput {

    protected int x;
    protected int y;

    protected int hours;
    protected int minutes;
    protected int seconds;
    protected int time6d;

    protected static List<DummyBase> instances = new ArrayList<DummyBase>();

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





    public static void putInstance(DummyBase instance) {
        instances.add(instance);
    }
    public static void printAllData() {
        for(int i = 0; i < instances.size(); i++) {
            System.out.println(instances.get(i));
        }
    }

}


interface IConsoleOutput {

    Object[] getData();
    Object getFormattedData();

    String getFormattedOutput();
    void printData();

}

interface IIntConsumer {

    void run(int in);

}

interface IStringSerializable {

    String toString();

}

interface IWithCoord {

    int getX();
    int getY();

}

interface IWithTime {

    int getHours();
    int getMinutes();
    int getSeconds();

    int get6dTime();
    String formatTime(String separator);

}


////////////////////////////////////////////////////////////////
//============================================================//
////////////////////////////////////////////////////////////////

class ConsoleLogger extends Logger{

    private static final int BUILDER_CAPACITY = 8;

    private String name;
    private String threadName;

    private int builderCapacity;

    public ConsoleLogger(String name) {
        this(name, "main");
    }
    public ConsoleLogger(String name, String threadName) {
        this.name = name;
        this.threadName = threadName;
    }
    


    @Override
    public void debug(String t) {
        this.log(t, "DEBUG");
    }
    @Override
    public void info(String t) {
        this.log(t, "INFO");
    }
    @Override
    public void warn(String t) {
        this.log(t, "WARN");
    }
    @Override
    public void error(String t) {
        this.log(t, "ERROR");
    }
    @Override
    public void error(Throwable e) {
        this.log("An exception was thrown by " + this.name + ": ", "ERROR");
        e.printStackTrace();
    }



    private void log(String t, String prefix) {
        StringBuilder out = new StringBuilder(BUILDER_CAPACITY);

        out.append("[");
        out.append(this.threadName);
        out.append("/");
        out.append(prefix);
        out.append("] [");
        out.append(this.name);
        out.append("] ");
        out.append(t);

        System.out.println(out.toString());
    }

}

class FakeLogger extends Logger{

    public FakeLogger() {
    }

    @Override
    public void debug(String t) {
    }
    @Override
    public void info(String t) {
    }
    @Override
    public void warn(String t) {
    }
    @Override
    public void error(String t) {
    }
    @Override
    public void error(Throwable e) {
    }

}

abstract class Logger {

    public void debug(String t) {
    }
    public void info(String t) {
    }
    public void warn(String t) {
    }
    public void fatal(String t) {
    }
    public void error(String t) {
    }
    public void error(Throwable e) {
    }

}
