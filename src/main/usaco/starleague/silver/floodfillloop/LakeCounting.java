package usaco.starleague.silver.floodfillloop;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class LakeCounting {

    private static char[][] map;
    private static int mapX;
    private static int mapY;

    public static void main(String[] args) {
        Utils.resetLogger("LakeCounting", "floodfillloop", true);
        Scanner consoleIn = new Scanner(System.in);

        int pondCount = 0;
        mapY = consoleIn.nextInt();
        mapX = consoleIn.nextInt();
        //vetical (first index) is y, second index is x
        //[[0, 0, 0],
        // [0, 0, 0],
        // [0, 0, 0]]
        map = new char[mapY][mapX];
        Utils.getLogger().info("mapX: " + mapX + "   mapY: " + mapY);

        for(int i = 0; i < mapY; i++) {
            // in case some stupid people
            // put space in between
            map[i] = consoleIn.next().toCharArray();
        }
        Utils.getLogger().info("map: " + printArray(map));

        for(int i = 0; i < mapX; i++) {
            for(int j = 0; j < mapY; j++) {
                if(map[j][i] == 'W') {
                    floodfillWs(i, j);
                    pondCount++;
                }
            }
        }
        
        Utils.getLogger().info("marked map: " + printArray(map));
        Utils.println(pondCount);

        consoleIn.close();
    }

    private static void floodfillWs(int x, int y) {
        map[y][x] = 'X';

        boolean doesMarked;
        do {
            doesMarked = false;

            for(int i = 0; i < mapX; i++) {
                for(int j = 0; j < mapY; j++) {
                    Utils.getLogger().debug("at x: " + i + " y: " + j + " current: " + map[j][i]);

                    if(map[j][i] == 'W' && hasXNeghibor(i, j)) {
                        map[j][i] = 'X';
                        doesMarked = true;
                    }
                }
            }

        } while(doesMarked);
    }

    private static boolean hasXNeghibor(int x, int y) {
        for(int i = -1; i <= 1; i++) {      //x
            if(x + i < 0 || x + i >= mapX) continue;

            for(int j = -1; j <= 1; j++) {  //y
                if(y + j < 0 || y + j >= mapY) continue;
                if(i == 0 && j == 0) continue;

                if(map[y + j][x + i] == 'X') {
                    return true;
                }
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
