package usaco.starleague.silver.exam2;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class ElectionTime {

    public static void main(String[] args) {
        Utils.resetLogger("ElectionTime", "exam2", true);
        Scanner consoleIn = new Scanner(System.in);

        int i;
        int amountCows = consoleIn.nextInt();
        int amountSucessRound1 = consoleIn.nextInt();
        CowElecter[] cows = new CowElecter[amountCows];

        for(i = 0; i < amountCows; i++) {
            cows[i] = new CowElecter(i+1, consoleIn.nextInt(), consoleIn.nextInt());
            Utils.getLogger().info(cows[i].getFormattedOutput());
        }

        CowElecter.isRoundTwo = false;
        Arrays.sort(cows);
        Utils.getLogger().info("first round: \n" + Arrays.toString(cows));

        CowElecter[] wonCows = new CowElecter[amountSucessRound1];
        i = 0;
        for(int j = amountCows-1; j >= amountCows - amountSucessRound1; j--) {

            //cows[i].succesed = true;
            wonCows[i] = cows[j];
            i++;
        }

        CowElecter.isRoundTwo = true;
        Arrays.sort(wonCows);
        
        Utils.getLogger().info("second round: \n" + Arrays.toString(wonCows));
        Utils.println(wonCows[wonCows.length-1].index);

        consoleIn.close();
    }

}


class CowElecter extends DummyBase implements Comparable<CowElecter> {

    static boolean isRoundTwo;

    boolean succesed;
    int vote1;
    int vote2;
    int index;

    public CowElecter(int index, int vote1, int vote2) {
        this.index = index;
        this.vote1 = vote1;
        this.vote2 = vote2;
    }



    @Override
    public int compareTo(CowElecter c) {
        int tVote;
        int cVote;

        if(CowElecter.isRoundTwo) {
            //if(!this.succesed) return -1;
            tVote = this.vote2;
            cVote = c.vote2;
        } else {
            tVote = this.vote1;
            cVote = c.vote1;
        }

        return tVote - cVote;
    }


    @Override
    public String toString() {
        return "\n" + this.getFormattedOutput();
    }
    @Override
    public String getFormattedOutput() {
        return "[ " + this.index + " ] vote1: " + this.vote1 + "   vote2: " + this.vote2;
    }

}
