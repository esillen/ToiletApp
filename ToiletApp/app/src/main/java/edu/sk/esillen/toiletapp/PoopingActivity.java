package edu.sk.esillen.toiletapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PoopingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);
    }





    public void commit(View view){

        Log.d("POOPING ACTIVITY", "commited");

    }


}
