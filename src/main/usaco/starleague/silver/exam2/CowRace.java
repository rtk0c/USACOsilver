package usaco.starleague.silver.exam2;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class CowRace {

    private static int[] bessieTimes;
    private static int[] bessitSpeeds;
    private static int[] elsieTimes;
    private static int[] elsieSpeeds;

    private static int totalBessieRaceTime;
    private static int totalElsieRaceTime;

    public static void main(String[] args) {
        Utils.resetLogger("CowRace", "exam2", true);
        Scanner consoleIn = new Scanner(System.in);

        int amountBessieSegs = consoleIn.nextInt();
        int amountElsieSegs = consoleIn.nextInt();
        int leadershipChange = 0;

        bessieTimes = new int[amountBessieSegs];
        bessitSpeeds = new int[amountBessieSegs];
        elsieTimes = new int[amountElsieSegs];
        elsieSpeeds = new int[amountElsieSegs];

        for(int i = 0; i < amountBessieSegs; i++) {
            bessitSpeeds[i] = consoleIn.nextInt();
            bessieTimes[i] = consoleIn.nextInt();
        }
        for(int i = 0; i < amountElsieSegs; i++) {
            elsieSpeeds[i] = consoleIn.nextInt();
            elsieTimes[i] = consoleIn.nextInt();
        }

        Utils.getLogger().info("bessie time: " + Arrays.toString(bessieTimes));
        Utils.getLogger().info("bessie speed: " + Arrays.toString(bessitSpeeds));
        Utils.getLogger().info("elsie time: " + Arrays.toString(elsieTimes));
        Utils.getLogger().info("elsie speed: " + Arrays.toString(elsieSpeeds));

        /*int raceDist = 0;
        int raceTime = 0;
        totalBessieRaceTime = 0;
        totalElsieRaceTime = 0;
        for(int i = 0; i < bessieTimes.length; i++) {
            raceDist += bessieTimes[i] * bessitSpeeds[i];
            totalBessieRaceTime += bessieTimes[i];
        }
        for(int i = 0; i < elsieTimes.length; i++) {
            totalElsieRaceTime += elsieTimes[i];
        }
        raceTime = totalBessieRaceTime > totalElsieRaceTime ?
                     totalBessieRaceTime : totalElsieRaceTime;
        Utils.getLogger().info("bessie time: " + totalBessieRaceTime + "   elsie time: " + totalElsieRaceTime);
        Utils.getLogger().info("max race time: " + raceTime);
        Utils.getLogger().info("total race distance: " + raceDist);*/
        int raceDist = 0;
        int raceTime = 0;
        for(int i = 0; i < bessieTimes.length; i++) {
            raceDist += bessieTimes[i] * bessitSpeeds[i];
            raceTime += bessieTimes[i];
        }
        Utils.getLogger().info("max race time: " + raceTime);
        Utils.getLogger().info("total race distance: " + raceDist);

        final int STAGE_BESSIE_FIRST = 0;
        final int STAGE_ELSIE_FIRST = 1;
        int stage = -1;
        leadershipChange = -1; //at start there is one increase, but we don't want that
        for(int i = 1; i <= raceTime; i++ ) {
            int bessieDist = getDist(i, bessieTimes, bessitSpeeds);
            int elsieDist = getDist(i, elsieTimes, elsieSpeeds);
            int newStage = bessieDist > elsieDist ?
                             STAGE_BESSIE_FIRST :
                             STAGE_ELSIE_FIRST;

            Utils.getLogger().debug("t = " + i + ", bessieDist: " + bessieDist + "    elsieDist: " + elsieDist + "  |  current stage: " + stage);

            if(stage != newStage) {
                stage = newStage;
                leadershipChange++;
            }
        }

        Utils.println(leadershipChange);
        consoleIn.close();
    }

    private static int getDist(int t, int[] segTimes, int[] segSpeed) {
        if(segTimes.length != segSpeed.length) {
            return -2;
        }
        /*if(t > CowRace.totalBessieRaceTime)
            t = CowRace.totalBessieRaceTime;
        if(t > CowRace.totalElsieRaceTime)
            t = CowRace.totalElsieRaceTime;*/

        int dist = 0;
        int time = 0;
        int i = 0;
        while(time < t) {
            time += segTimes[i];
            dist += segTimes[i] * segSpeed[i];
            i++;
        }

        //return -1;
        return dist;
    }

    private static int indexOf(int target, int[] list) {
        if(target > list[list.length - 1])
            return list.length - 1;

        int l = 0;
        int h = list.length - 1;
        int mid; //basicly the index that is checking

        while(l <= h) {
            mid = (l + h) / 2;

            if(list[mid] <= target && target < list[mid+1]) {
                return mid;
            } else if(list[mid] < target) {
                l = mid + 1;
            } else if(list[mid] > target) {
                h = mid - 1;
            }
        }

        return -1;
    }        

}
