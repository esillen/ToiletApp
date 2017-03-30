package edu.derp.esillen.toiletapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Date;

import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class PoopingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);
    }


    public void commit(View view){

        ToiletCheckin tc = new ToiletCheckin(new Date());
        tc.save();

        Log.d("POOPING ACTIVITY", "commited");

    }


}
