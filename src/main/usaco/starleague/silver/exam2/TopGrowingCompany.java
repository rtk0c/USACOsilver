package usaco.starleague.silver.exam2;

import java.util.*;

import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class TopGrowingCompany {

    static int amountCompanies;

    public static void main(String[] args) {
        Utils.resetLogger("TopGrowingCompany", "exam2", true);
        Scanner consoleIn = new Scanner(System.in);

        amountCompanies = consoleIn.nextInt();

        for(int i = 0; i < amountCompanies; i++) {
            // set posThis next round
            CompanyData.putInstance(new CompanyData(consoleIn.next(), consoleIn.nextInt(), -1));
        }
        for(int l = 1; l <= amountCompanies; l++) {
            //CompanyData.getByCIN(consoleIn.next()).posThis = l;
        }
        Utils.getLogger().info("all companies exists: \n" + CompanyData.getInstances());

        CompanyData.deleteAllGones();
        Utils.getLogger().info("deleted all non-present ones: \n" + CompanyData.getInstances());

        CompanyData.sortBy(CompanyData.BY_DIFF);
        Utils.getLogger().info("sort by growth: \n" + CompanyData.getInstances());

        CompanyData.deleteAllLower();
        Utils.getLogger().info("deleted all non-highest ones: \n" + CompanyData.getInstances());

        CompanyData.compareMode = CompanyData.BY_POS_T;
        Utils.println(CompanyData.min().CIN);

        consoleIn.close();
    }

}



class CompanyData extends DummyBase implements Comparable<CompanyData> {

    
    static final int BY_POS_L = 0;
    static final int BY_POS_T = 1;
    static final int BY_DIFF = 2;

    static int compareMode = -1;
    

    //static boolean ;

    String CIN;
    int posLast;
    int posThis;

    public CompanyData(String CIN, int pl, int pt) {
        this.CIN = CIN;
        this.posLast = pl;
        this.posThis = pt;
    }

    public int diff() {
        return this.posLast - this.posThis;
    }



    @Override
    public int compareTo(CompanyData c) {
        switch(compareMode) {
            case BY_POS_L:
                return c.posLast - this.posLast;
            case BY_POS_T:
                return c.posThis - this.posThis;
            case BY_DIFF:
                return this.diff() - c.diff();
        }

        return 0;
    }


    @Override
    public String toString() {
        return this.getFormattedOutput() + "\n" ;
    }
    @Override
    public String getFormattedOutput() {
        return this.CIN + " |    position of last year: " + this.posLast + "   position of this year: " + this.posThis + "  |  diff: " + this.diff();
    }


    static ListPP<CompanyData> instances = new ArrayListPP<CompanyData>();

    static void putInstance(CompanyData instance) {
        instances.add(instance);
    }

    static CompanyData getByCIN(String filter) {
        /*for(int i = 0; i < instances.size(); i++) {
            if(instances.get(i).CIN.equals(filter)) {
                return instances.get(i);
            }
        }*/
        int l = 0;
        int h = instances.size() - 1;
        int mid;

        while(l <= h) {
            mid = (l + h) / 2;
            int compare = instances.get(mid).CIN.compareTo(filter);

            if(compare == 0) {
                return instances.get(mid);
            } else if(compare == -1) {
                l = mid + 1;
            } else if(compare == 1) {
                h = mid - 1;
            }
        }

        // if program goes here, it means that the CIN didn't exist
        // in last year's list, so put (amountCompanies + 1) as posLast.
        putInstance(new CompanyData(filter, TopGrowingCompany.amountCompanies + 1, -1));
        return instances.get(instances.size()-1);
    }

    // returns the amount of companies deleted
    static int deleteAllGones() {
        int count = 0;

        for(int i = 0; i < instances.size(); i++) {
            if(instances.get(i).posLast == -1 || instances.get(i).posThis == -1) {
                instances.remove(i);
                count++;
            }
        }

        return count;
    }

    // this method ASSUMES that the array is sorted by difference, increasingly
    // returns the amount of companies deleted
    static int deleteAllLower() {
        int count = 0;
        int growthFilter = instances.get(instances.size()-1).diff();

        for(int i = instances.size()-1; i >= 0; i--) {
            if(instances.get(i).diff() < growthFilter) {
                instances.remove(i);
            }
        }

        return count;
    }

    static void sortBy(int mode) {
        compareMode = mode;
        instances.sort();
    }

    static CompanyData min() {
        return instances.min();
    }
    static CompanyData max() {
        return instances.max();
    }



    // this is just for debugging, NEVER use this for other purposes
    static ListPP<CompanyData> getInstances() {
        return instances;
    }

}
