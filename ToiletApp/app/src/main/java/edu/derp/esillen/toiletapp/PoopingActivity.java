package edu.derp.esillen.toiletapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Date;

public class PoopingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int color = sharedPref.getInt(getString(R.string.preferences_color), -1);

        Log.d("COLOR",""+color);

        SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);

        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateColor();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.color_indicator).setBackgroundColor(color);
    }

    private void updateColor(){
        int r = ((SeekBar) findViewById(R.id.redSeekBar)).getProgress();
        int g = ((SeekBar) findViewById(R.id.greenSeekBar)).getProgress();
        int b = ((SeekBar) findViewById(R.id.blueSeekBar)).getProgress();
        int color = (255 & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
        findViewById(R.id.color_indicator).setBackgroundColor(color);
    }

    public void commit(View view){

        int amount = ((SeekBar) findViewById(R.id.amountSeekBar)).getProgress();
        Intent output = new Intent();
        output.putExtra(getResources().getString(R.string.request_date_key), new Date().getTime());
        output.putExtra(getResources().getString(R.string.request_amount_key), amount);
        if (amount > 0){
            int red = ((SeekBar) findViewById(R.id.redSeekBar)).getProgress();
            int green = ((SeekBar) findViewById(R.id.redSeekBar)).getProgress();
            int blue = ((SeekBar) findViewById(R.id.redSeekBar)).getProgress();
            int color = (255 & 0xff) << 24 | (red & 0xff) << 16 | (green & 0xff) << 8 | (blue & 0xff);
            output.putExtra(getResources().getString(R.string.request_color_key), color);
        }
        setResult(RESULT_OK, output);
        finish();
    }

}
