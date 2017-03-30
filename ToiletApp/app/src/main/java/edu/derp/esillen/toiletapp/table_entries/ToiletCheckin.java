package edu.derp.esillen.toiletapp.table_entries;

import com.orm.SugarRecord;

import java.util.Date;

public class ToiletCheckin extends SugarRecord {
    public Date date;

    public ToiletCheckin(){}

    public ToiletCheckin(Date d){
        date = d;
    }

}
