package edu.derp.esillen.toiletapp.table_entries;

import com.orm.SugarRecord;

import java.util.Date;

public class ToiletCheckin extends SugarRecord {
    public Date date;
    public int amount;
    public int color;
    public int consistency;

    public ToiletCheckin(){}

    public ToiletCheckin(Date d){
        date = d;
    }
    // Happens only if there is some amount at all
    public ToiletCheckin(Date d, int a, int c, int cons){
        date = d;
        amount = a;
        color = c;
        consistency = cons;
    }

}
