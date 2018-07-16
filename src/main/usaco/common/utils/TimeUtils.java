package usaco.common.utils;

import java.util.*;


public class TimeUtils {

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