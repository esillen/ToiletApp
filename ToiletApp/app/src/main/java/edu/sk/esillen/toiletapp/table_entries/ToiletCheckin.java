package edu.sk.esillen.toiletapp.table_entries;

import com.orm.SugarApp;
import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by esillen on 2017-03-29.
 */

public class ToiletCheckin extends SugarRecord {
    public Date date;

    public ToiletCheckin(){}

    public ToiletCheckin(Date d){
        date = d;
    }

}
