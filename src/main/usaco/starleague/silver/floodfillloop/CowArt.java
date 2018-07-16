package usaco.starleague.silver.floodfillloop;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class CowArt {

    private static char[][] paint;
    private static int paintLength;

    // (int) 'R' 'G' 'B' < 91
    // (int) 'x' 'y' 'z' > 96
    public static void main(String[] args) {
        Utils.resetLogger("CowArt", "floodfillloop", true);
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

        int humanRegions = count(false);

        // reset 'r' to 'R', 'g' to 'G', 'b' to 'B'
        for(int i = 0; i < paintLength; i++) {
            for(int j = 0; j < paintLength; j++) {
                paint[j][i] = paint[j][i] == 'r' ? 'R' : paint[j][i] == 'g' ? 'G' : paint[j][i] == 'b' ? 'B' : '0';
            }
        }
        Utils.getLogger().info("reset paint: " + printArray(paint));

        int cowRegions = count(true);

        Utils.getLogger().info("marked paint: " + printArray(paint));
        Utils.println(humanRegions + " " + cowRegions);

        consoleIn.close();
    }

    private static int count(boolean mode) {
        int regions = 0;
        for(int i = 0; i < paintLength; i++) {
            for(int j = 0; j < paintLength; j++) {
                //Utils.getLogger().debug("at x: " + i + " y: " + j + " current: " + paint[j][i]);

                // paint[j][i] is a capital letter
                if((int) paint[j][i] < 91) {
                    floodfillWs(i, j, paint[j][i] == 'R' ? 'r' : paint[j][i] == 'G' ? 'g' : paint[j][i] == 'B' ? 'b' : '0', mode);
                    regions++;
                }
            }
        }

        return regions;
    }

    private static void floodfillWs(int x, int y, char replaceWith, boolean mode) {
        paint[y][x] = replaceWith;

        boolean doesMarked;
        do {
            doesMarked = false;

            for(int i = 0; i < paintLength; i++) {
                for(int j = 0; j < paintLength; j++) {
                    // paint[j][i] is a capital letter
                    if((int) paint[j][i] < 91 && hasSameNeghibor(i, j, replaceWith, mode)) {
                        Utils.getLogger().info("at x: " + i + " y: " + j + " current: " + paint[j][i] + "   and set to: " + replaceWith);

                        paint[j][i] = replaceWith;
                        doesMarked = true;
                    }
                }
            }

        } while(doesMarked);
    }

    private static boolean hasSameNeghibor(int x, int y, char sameAs, boolean isCow) {
        final int[] DX = new int[]{-1, 0, 1, 0};
        final int[] DY = new int[]{0, -1, 0, 1};

        for(int i = 0; i < DX.length; i++) {
            if(x + DX[i] < 0 || x + DX[i] >= paintLength ||
               y + DY[i] < 0 || y + DY[i] >= paintLength) {
                   continue;
            }

            if(isCow) {
                if((sameAs == 'r' || sameAs == 'g') && (paint[y + DY[i]][x + DX[i]] == 'r' || paint[y + DY[i]][x + DX[i]] == 'g')) {
                    Utils.getLogger().debug("detected neghibor: " + (x + DX[i]) + ", " + (y + DY[i]) + "   ");
                    return true;
                } else if(paint[y + DY[i]][x + DX[i]] == sameAs) {
                    Utils.getLogger().debug("detected neghibor: " + (x + DX[i]) + ", " + (y + DY[i]) + "   ");
                    return true;
                }
            } else if(paint[y + DY[i]][x + DX[i]] == sameAs) {
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
