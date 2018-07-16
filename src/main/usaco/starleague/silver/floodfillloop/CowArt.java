package usaco.starleague.silver.floodfillloop;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class CowArt {

    private static char[][] paint;
    private static int paintLength;

    public static void main(String[] args) {
        Utils.resetLogger("CowArt", "floodfillloop", true);
        Scanner consoleIn = new Scanner(System.in);

        paintLength = consoleIn.nextInt();
        //vetical (first index) is y, second index is x
        //[[0, 0, 0],
        // [0, 0, 0],
        // [0, 0, 0]]
        paint = new char[mapY][mapX];e

        Utils.getLogger().info("paintLength: " + paintLength);

        for(int i = 0; i < amountFlooded; i++) {
            paint[i] = consoleIn.next().toCharArray();
        }
        Utils.getLogger().info("paint: " + printArray(map));

        int humanRegions = 0;
        for(int i = 0; i < mapX; i++) {
            for(int j = 0; j < mapY; j++) {
                if(map[j][i] != 'x') {
                    int size = floodfillWs(i, j, String.valueOf(pondId).charAt(0));
                    humanRegions++;
                }
            }
        }
        
        Utils.getLogger().info("marked map: " + printArray(map));
        Utils.println(answer);

        consoleIn.close();
    }

    private static int floodfillWs(int x, int y, char pondId) {
        map[y][x] = pondId;

        boolean doesMarked;
        int pondSize = 1;
        do {
            doesMarked = false;

            for(int i = 0; i < mapX; i++) {
                for(int j = 0; j < mapY; j++) {
                    Utils.getLogger().debug("at x: " + i + " y: " + j + " current: " + map[j][i]);

                    if(map[j][i] == 'W' && hasSymNeghibor(i, j, pondId)) {
                        map[j][i] = pondId;
                        pondSize++;
                        doesMarked = true;
                    }
                }
            }

        } while(doesMarked);

        Utils.getLogger().debug("current pond size: " + pondSize);
        return pondSize;
    }

    private static boolean hasSymNeghibor(int x, int y, int symbol, boolean mode) {
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};

        for(int i = 0; i < dx.length; i++) {
            if(x + dx[i] < 0 || x + dx[i] >= mapX ||
               y + dy[i] < 0 || y + dy[i] >= mapY) {
                   continue;
            }

            if(mode &&) {
                
            } else if(map[y + dy[i]][x + dx[i]] == symbol) {
                Utils.getLogger().debug("detected neghibor: " + (x + dx[i]) + ", " + (y + dy[i]));
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
