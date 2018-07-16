package usaco.starleague.silver.binarysearch;

//works
import java.util.*;
import usaco.common.*;
import usaco.common.crates.*;
import usaco.common.pregen.*;
import usaco.common.utils.*;


public class MusicNotes {

    private static int[] notes; //store duration of each note
    private static int[] questions; //the request's index
    private static int amountNotes;
    private static int amountQuestions;

    public static void main(String[] args) {
        Utils.resetLogger("CountingHaybales", "binaryseaerch", true);
        Scanner consoleIn = new Scanner(System.in);

        amountNotes = consoleIn.nextInt();
        amountQuestions = consoleIn.nextInt();

        for(int i = 0; i < amountNotes; i++) {
            notes[i] = consoleIn.nextInt();
        }

        for(int i = 0; i < amountQuestions; i++) {
            int requestedIndex = consoleIn.nextInt();
            questions[i] = requestedIndex;

            Utils.println(indexOf(requestedIndex));
        }

        consoleIn.close();
    }


    private static int indexOf(int target) {
        if(target > notes[amountNotes - 1])
            return amountNotes - 1;

        int l = 0;
        int h = amountNotes - 1;
        int mid; //basicly the index that is checking

        while(l <= h) {
            mid = (l + h) / 2;

            if(notes[mid] <= target && target < notes[mid+1]) {
                return mid;
            } else if(notes[mid] < target) {
                l = mid + 1;
            } else if(notes[mid] > target) {
                h = mid - 1;
            }
        }

        return -1;
    }

}
