package usaco.starleague.silver.floodfillloop;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class AvoidTheLakes {

    private static char[][] map;
    private static int mapX;
    private static int mapY;

    public static void main(String[] args) {
        Utils.resetLogger("AvoidTheLakes", "floodfillloop", true);
        Scanner consoleIn = new Scanner(System.in);

        int answer = 0;
        mapY = consoleIn.nextInt();
        mapX = consoleIn.nextInt();
        //vetical (first index) is y, second index is x
        //[[0, 0, 0],
        // [0, 0, 0],
        // [0, 0, 0]]
        map = new char[mapY][mapX];
        int amountFlooded = consoleIn.nextInt();
        int[] ponds = new int[mapY * mapX / 2];
        int pondsNextInsertion = 0;

        Utils.getLogger().info("mapX: " + mapX + "   mapY: " + mapY);

        for(int i = 0; i < amountFlooded; i++) {
            map[ consoleIn.nextInt() - 1 ][ consoleIn.nextInt() - 1 ] = 'W';
        }
        Utils.getLogger().info("map: " + printArray(map));

        int pondId = 1;
        for(int i = 0; i < mapX; i++) {
            for(int j = 0; j < mapY; j++) {
                if(map[j][i] == 'W') {
                    int size = floodfillWs(i, j, String.valueOf(pondId).charAt(0));
                    pondsNextInsertion++;
                    pondId++;

                    answer = MathUtils.max(answer, size);
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

    private static boolean hasSymNeghibor(int x, int y, int symbol) {
        /*for(int i = -1; i <= 1; i++) {      //x
            if(x + i < 0 || x + i >= mapX) continue;

            for(int j = -1; j <= 1; j++) {  //y
                if(y + j < 0 || y + j >= mapY) continue;
                if(i == 0 && j == 0) continue;

                if(map[y + j][x + i] == 'X') {
                    return true;
                }
            }
        }*/
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, -1, 0, 1};

        for(int i = 0; i < dx.length; i++) {
            if(x + dx[i] < 0 || x + dx[i] >= mapX ||
               y + dy[i] < 0 || y + dy[i] >= mapY) {
                   continue;
            }

            if(map[y + dy[i]][x + dx[i]] == symbol) {
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
