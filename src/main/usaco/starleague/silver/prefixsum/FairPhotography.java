package usaco.starleague.silver.prefixsum;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class FairPhotography {

    public static void main(String[] args) {
        Utils.resetLogger("FairPhotography", "prefixsum", true);
        Scanner consoleIn = new Scanner(System.in);

        int amountCows = consoleIn.nextInt();
        BreedCow[] cows = new BreedCow[amountCows + 1];

        cows[0] = new BreedCow(0, 'N');
        for(int i = 1; i <= amountCows; i++) {
            cows[i] = new BreedCow(consoleIn.nextInt(), consoleIn.next().charAt(0));
        }
        Utils.getLogger().info("cows: " + Arrays.toString(cows));

        Arrays.sort(cows);
        Utils.getLogger().info("sorted cows: " + Arrays.toString(cows));

        int[] pSum = new int[amountCows + 1];
        pSum[0] = 0;
        for(int i = 1; i <= amountCows; i++) {
            pSum[i] = pSum[i - 1] + (cows[i].type == 'G' ? 1 : -1);
            
            Utils.getLogger().debug("pSum[" + i + "]: " + pSum[i] + "  |  pSum of i-1: " + pSum[i - 1] + "   cow[i] type: " + cows[i].type);
        }
        Utils.getLogger().info("prefix sum: " + Arrays.toString(pSum));

        int[] mins = new int[pSum.length];
        int[] maxs = new int[pSum.length];
        int offset = Math.abs(MathUtils.min(pSum));
        Arrays.fill(mins, -1);
        Arrays.fill(maxs, -1);

        Utils.getLogger().info("abs() of min() of pSum: " + offset);

        // m[index] = i;
        // index is the value in pSum
        // i is the index in pSum coorsponding to index
        mins[0] = 0;
        //maxs[0] = 0;
        for(int i = 1; i <= pSum.length - 1; i++) {
            if(mins[ pSum[i] + offset ] == -1) {
                mins[ pSum[i] + offset ] = i;
            }
        }
        for(int i = pSum.length - 1; i >= 1; i--) {
            if(maxs[ pSum[i] + offset ] == -1) {
                maxs[ pSum[i] + offset ] = i;
            }
        }

        Utils.getLogger().info("mins[]: " + Arrays.toString(mins));
        Utils.getLogger().info("maxs[]: " + Arrays.toString(maxs));

        int answer = 0;
        for(int i = 0; i < pSum.length; i++) {
            if(mins[i] == -1 || maxs[i] == -1) continue;

            int size = cows[ maxs[i] ].pos - cows[ mins[i] + 1 ].pos;

            answer = MathUtils.max(answer, size);
        }
        
        Utils.println(answer);

        consoleIn.close();
    }

    private static String printArray(int[][] a) {
        StringBuilder output = new StringBuilder(a.length);

        for(int i = 0; i < a.length; i++) {
            output.append(Arrays.toString(a[i]));
        }

        return output.toString();
    }

}

class BreedCow extends DummyBase implements Comparable<BreedCow> {

    int pos;
    char type;

    public BreedCow(int pos, char type) {
        this.pos = pos;
        this.type = type;
    }


    @Override
    public String toString() {
        return "\n" + this.getFormattedOutput();
    }

    @Override
    public String getFormattedOutput() {
        return "(at " + this.pos + " | type: " + String.valueOf(type) + ")";
    }


    @Override
    public int compareTo(BreedCow c) {
        return this.pos - c.pos;
    }

}
