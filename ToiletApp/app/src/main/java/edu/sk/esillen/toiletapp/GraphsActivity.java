package edu.sk.esillen.toiletapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import edu.sk.esillen.toiletapp.table_entries.ToiletCheckin;

public class GraphsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
    }

    @Override
    protected void onResume(){
        super.onResume();
        List<ToiletCheckin> checkins = ToiletCheckin.listAll(ToiletCheckin.class);
        for(ToiletCheckin checkin : checkins){
            Log.d("POOPED", checkin.date.toString());
        }
    }

}
