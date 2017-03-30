package edu.derp.esillen.toiletapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int color = sharedPref.getInt(getString(R.string.preferences_color), -1);

        SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);

        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor(progress, -1, -1);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor(-1, progress, -1);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor(-1, -1,progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.color_indicator).setBackgroundColor(color);
    }

    private void updateColor(int r, int g, int b){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int color = sharedPref.getInt(getString(R.string.preferences_color), -1);
        r = r < 0 ? Color.red(color) : r;
        g = g < 0 ? Color.green(color) : g;
        b = b < 0 ? Color.blue(color) : b;
        color = (255 & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.preferences_color), color);
        editor.commit();
        findViewById(R.id.color_indicator).setBackgroundColor(color);
    }

}
