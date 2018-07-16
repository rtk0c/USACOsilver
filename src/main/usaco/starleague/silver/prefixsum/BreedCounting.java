package usaco.starleague.silver.prefixsum;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class BreedCounting {

    private static int[] breedTypes;
    private static int[][] prefixSum;
    private static int[][] queries;

    public static void main(String[] args) {
        Utils.resetLogger("BreedCounting", "prefixsum", true);
        Scanner consoleIn = new Scanner(System.in);

        int amountBreeds = consoleIn.nextInt();
        int amountQueries = consoleIn.nextInt();
        //breedTypes = new int[amountBreeds + 1];
        prefixSum = new int[amountBreeds + 1][3];
        queries = new int[amountQueries][2];

        prefixSum[0][0] = 0;
        prefixSum[0][1] = 0;
        prefixSum[0][2] = 0;
        for(int i = 1; i <= amountBreeds; i++) {
            /*p[i][0] = p[i-1][0] + a[i];
            p[i][1] = p[i-1][1] + a[i];
            p[i][2] = p[i-1][2] + a[i];*/

            /*prefixSum[i] = prefixSum[i - 1];
            prefixSum[i][ consoleIn.nextInt() - 1 ]++;
            Utils.getLogger().debug("");*/

            int type = consoleIn.nextInt();
            Utils.getLogger().debug("cow type: " + type);
            switch(type) {
                case 1:
                    prefixSum[i][0] = prefixSum[i - 1][0] + 1;
                    prefixSum[i][1] = prefixSum[i - 1][1];
                    prefixSum[i][2] = prefixSum[i - 1][2];
                    break;
                case 2:
                    prefixSum[i][0] = prefixSum[i - 1][0];
                    prefixSum[i][1] = prefixSum[i - 1][1] + 1;
                    prefixSum[i][2] = prefixSum[i - 1][2];
                    break;
                case 3:
                    prefixSum[i][0] = prefixSum[i - 1][0];
                    prefixSum[i][1] = prefixSum[i - 1][1];
                    prefixSum[i][2] = prefixSum[i - 1][2] + 1;
                    break;
            }
        }
        Utils.getLogger().info("prefix sum for breed types: \n" + printArray(prefixSum));

        for(int i = 0; i < amountQueries; i++) {
            queries[i][0] = consoleIn.nextInt();
            queries[i][1] = consoleIn.nextInt();
        }
        Utils.getLogger().info("breed types: \n" + Arrays.toString(breedTypes));
        Utils.getLogger().info("queries: \n" + printArray(queries));

        for(int i = 0; i < amountQueries; i++) {
            String output = "";

            Utils.getLogger().debug("i: " + i);
            Utils.getLogger().debug("query[0]: " + queries[i][0] + "   query[1]: " + queries[i][1]);
            Utils.getLogger().debug("query j[0] " + prefixSum[ queries[i][1] ][0] + "   j[1] " + prefixSum[ queries[i][1] ][1] + "   j[2] " + prefixSum[ queries[i][1] ][2]);
            Utils.getLogger().debug("query i[0] " + prefixSum[ queries[i][0] - 1 ][0] + "   i[1] " + prefixSum[ queries[i][0] - 1 ][1] + "   i[2] " + prefixSum[ queries[i][0] - 1 ][2]);

            output += prefixSum[ queries[i][1] ][0] - prefixSum[ queries[i][0] - 1 ][0] + " ";
            output += prefixSum[ queries[i][1] ][1] - prefixSum[ queries[i][0] - 1 ][1] + " ";
            output += prefixSum[ queries[i][1] ][2] - prefixSum[ queries[i][0] - 1 ][2] + " ";

            Utils.println(output);
        }
        
        Utils.println("/* answer */");
    }

    private static String printArray(int[][] a) {
        StringBuilder output = new StringBuilder(a.length);

        for(int i = 0; i < a.length; i++) {
            output.append(Arrays.toString(a[i]));
        }

        return output.toString();
    }

}
