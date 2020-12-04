package com.example.statethefacts;

import java.util.Date;

public class SortByDate implements java.util.Comparator<GameResult> {

    public int compare(GameResult a, GameResult b){

        Date da = a.getFinishedOn();
        Date db = b.getFinishedOn();

        if(da == null && db == null)
            return 0;

        if(da != null && db == null)
            return 1;
        if(da == null && db != null)
            return -1;

        if(da.after(db)) {
            return 1;
        }
        if(da.before(db)) {
            return -1;
        }
        return 0;

    }

}
