package usaco.common;


public interface IWithTime {

    int getHours();
    int getMinutes();
    int getSeconds();

    int get6dTime();
    String formatTime(String separator);

}