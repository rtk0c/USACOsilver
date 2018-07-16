package usaco.starleague.silver.binarysearch;

//works
import java.util.*;
import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class CountingHaybales2 {

    private static int[] hays;

    public static void main(String[] args) {
        Utils.resetLogger("CountingHaybales", "binaryseaerch", true);
        Scanner consoleIn = new Scanner(System.in);

        int amountExistingHays = consoleIn.nextInt();
        int amountSearches = consoleIn.nextInt();
        hays = new int[amountExistingHays];

        for(int i = 0; i < amountExistingHays; i++) {
            hays[i] = consoleIn.nextInt();
        }
        Utils.getLogger().info(Arrays.toString(hays));

        Arrays.sort(hays);
        Utils.getLogger().info(Arrays.toString(hays));

        for(int i = 0; i < amountSearches; i++) {
            int searchesFrom = consoleIn.nextInt();
            int searchesTo = consoleIn.nextInt();

            int fromIndex = indexOfHays(searchesFrom );
            int toIndex = indexOfHays(searchesTo+1);
            int cAnswer = toIndex - fromIndex;

            Utils.getLogger().info("searching from " + searchesFrom + " to " + searchesTo);
            Utils.getLogger().info(toIndex + " - " + fromIndex + ", got result of " + cAnswer);
            System.out.println(cAnswer);
        }

        consoleIn.close();
    }

    /** migaical implementation, fake binary search indexOf()  */
    private static int indexOfHays(int target) {
        int l = 0;
        int h = hays.length-1;

        while(l <= h) {
            int mid = (l + h) / 2;

            if(hays[mid] < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return l;
    }

}
