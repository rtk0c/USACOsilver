package usaco.starleague.silver.binarysearch;

//works
import java.util.*;
import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class CountingHaybales {

    private static int[] hays;

    public static void main(String[] args) {
        Utils.resetLogger("CountingHaybales", "binaryseaerch", true);
        Scanner consoleIn = new Scanner(System.in);

        hays = new int[]{0, 1, 2, 4, 9, 10, 11, 23};
        Utils.getLogger().debug("indexOf: " + indexOfHaysF(1));
        Utils.getLogger().debug("indexOf: " + indexOfHaysF(3));
        Utils.getLogger().debug("indexOf: " + indexOfHaysF(14));
        Utils.getLogger().debug("indexOf: " + indexOfHaysF(43));

        int amountExistingHays = consoleIn.nextInt();
        int amountSearches = consoleIn.nextInt();
        hays = new int[amountExistingHays];
        //hays = new int[amountExistingHays + 1];
        //searches = new int[amountSearches][2];
        //answers = new int[amountSearches];

        for(int i = 0; i < amountExistingHays; i++) {
            hays[i] = consoleIn.nextInt();
        }
        //hays[amountExistingHays] = Integer.MAX_VALUE; //bound fail-safe
        Utils.getLogger().info(Arrays.toString(hays));

        Arrays.sort(hays);
        Utils.getLogger().info(Arrays.toString(hays));

        for(int i = 0; i < amountSearches; i++) {
            int searchesFrom = consoleIn.nextInt();
            int searchesTo = consoleIn.nextInt();
            Utils.getLogger().info("searching from " + searchesFrom + " to " + searchesTo);

            int fromIndex = indexOfHaysF(searchesFrom);
            int toIndex = indexOfHaysT(searchesTo);
            int cAnswer = toIndex - fromIndex;// + 1;
            Utils.getLogger().info(toIndex + " - " + fromIndex + ", got result of " + cAnswer);

            System.out.println(cAnswer);
        }

        consoleIn.close();
    }

    private static int indexOfHaysF(int target) {
        if(target > hays[hays.length-1])
            return hays.length-1;

        int l = 0;
        int h = hays.length-2;
        int mid = -1; //basicly the index that is checking

        while(l <= h) {
            mid = (l + h) / 2;

            //if(hays[mid] == target) {
            if(hays[mid] <= target && hays[mid+1] > target) {
                return mid;
            } else if(hays[mid] < target) {
                l = mid + 1;
            } else if(hays[mid] > target) {
                h = mid - 1;
            }
        }

        //return -1;
        return mid;
    }

    private static int indexOfHaysT(int target) {
        int l = 0;
        int h = hays.length-2;
        int mid = -1; //basicly the index that is checking

        while(l <= h) {
            mid = (l + h) / 2;

            if(hays[mid] <= target && hays[mid+1] > target) {
                return mid;
            } else if(hays[mid] < target) {
                l = mid + 1;
            } else if(hays[mid] > target) {
                h = mid - 1;
            }
        }

        return -1;
    }

}
