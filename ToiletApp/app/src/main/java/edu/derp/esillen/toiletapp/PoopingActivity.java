package edu.derp.esillen.toiletapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Date;

import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class PoopingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pooping);
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
            int color = (255 & 0xff) << 24 | (red & 0xff) << 16 | (green & 0xff) << 16 | (blue & 0xff);
            output.putExtra(getResources().getString(R.string.request_color_key), color);
        }
        setResult(RESULT_OK, output);
        finish();
    }

}
