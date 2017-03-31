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
import android.widget.Spinner;

import java.util.Date;

import edu.derp.esillen.toiletapp.CustomAdapters.BristolAdapter;
import edu.derp.esillen.toiletapp.Globals.GlobalVars;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class PoopingActivity extends AppCompatActivity {

    SeekBar redSeekBar, greenSeekBar, blueSeekBar, amountSeekBar;
    Spinner bristolSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);

        bristolSpinner = (Spinner) findViewById(R.id.bristolSpinner);
        BristolAdapter adapter = new BristolAdapter(this);
        bristolSpinner.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int color = sharedPref.getInt(getString(R.string.preferences_color), -1);

        Log.d("COLOR",""+color);

        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        amountSeekBar = (SeekBar) findViewById(R.id.amountSeekBar);

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

        amountSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0){findViewById(R.id.hasAmountLayout).setVisibility(View.VISIBLE);}
                else{findViewById(R.id.hasAmountLayout).setVisibility(View.INVISIBLE);}
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.color_indicator).setBackgroundColor(color);

        setEntryToMatchIntent();

    }

    private void setEntryToMatchIntent(){
        long id = getIntent().getLongExtra(getResources().getString(R.string.request_checkin_id_key), -1);
        if (id == -1) {return;} // Then there were no intent

        ToiletCheckin t_c_i = ToiletCheckin.findById(ToiletCheckin.class, id);
        int color = t_c_i.color;

        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));

        amountSeekBar.setProgress(t_c_i.amount);
        bristolSpinner.setSelection(t_c_i.consistency);
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
            int green = ((SeekBar) findViewById(R.id.greenSeekBar)).getProgress();
            int blue = ((SeekBar) findViewById(R.id.blueSeekBar)).getProgress();
            int color = (255 & 0xff) << 24 | (red & 0xff) << 16 | (green & 0xff) << 8 | (blue & 0xff);
            output.putExtra(getResources().getString(R.string.request_color_key), color);
            output.putExtra(getResources().getString(R.string.request_consistency_key), ((Spinner) findViewById(R.id.bristolSpinner)).getSelectedItemPosition());
        }
        setResult(RESULT_OK, output);
        finish();
    }

}
