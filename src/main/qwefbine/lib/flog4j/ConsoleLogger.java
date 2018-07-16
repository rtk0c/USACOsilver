package qwefbine.lib.flog4j;

import java.time.*;
import java.util.*;


public class ConsoleLogger extends Logger{

    private String name;
    private String threadName;
    private boolean withTime;

    private int builderCapacity;

    public ConsoleLogger(String name) {
        this(name, false);
    }
    public ConsoleLogger(String name, boolean logWithTime) {
        this(name, "main", logWithTime);
    }
    public ConsoleLogger(String name, Thread t) {
        this(name, t, false);
    }
    public ConsoleLogger(String name, Thread t, boolean logWithTime) {
        this(name, t.getName(), logWithTime);
    }
    public ConsoleLogger(String name, String threadName) {
        this(name, threadName, false);
    }
    public ConsoleLogger(String name, String threadName, boolean logWithTime) {
        this.name = name;
        this.threadName = threadName;
        this.withTime = logWithTime;

        if(this.withTime) {
            this.builderCapacity = 11;
        } else {
            this.builderCapacity = 8;
        }
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
        StringBuilder out = new StringBuilder(this.builderCapacity);

        if(this.withTime) {
            this.addDate(out);
        }

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
    
    private void addDate(StringBuilder builder) {
        builder.append("[");
        builder.append(LocalTime.now().withNano(0));
        builder.append("] ");
    }

}