package edu.derp.esillen.toiletapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int color = sharedPref.getInt(getResources().getString(R.string.preferences_color), -1);
    }
}
