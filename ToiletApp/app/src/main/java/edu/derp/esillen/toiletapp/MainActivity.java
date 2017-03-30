package edu.derp.esillen.toiletapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.util.Date;

import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class MainActivity extends AppCompatActivity {

    private final int POOP_REQUEST_CODE = 112345123;
    public static final String REQUEST_DATE_KEY = "REQUEST_DATE_KEY";
    public static final String REQUEST_COLOR_KEY = "REQUEST_COLOR_KEY";
    public static final String REQUEST_AMOUNT_KEY = "REQUEST_AMOUNT_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
        SugarContext.init(getApplicationContext());
        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());

    }


    public void checkin(View view){
        Intent intent = new Intent(this, PoopingActivity.class);
        startActivityForResult(intent, POOP_REQUEST_CODE);
        // TODO: startActivityForResult
    }

    public void openLogs(View view){
        startActivity(new Intent(this, PoopLogActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (POOP_REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    long time = data.getLongExtra(REQUEST_DATE_KEY, -1);
                    int amount = data.getIntExtra(REQUEST_AMOUNT_KEY, -1);
                    int color = data.getIntExtra(REQUEST_COLOR_KEY, -1);
                    logResult(time, amount, color);
                }
                break;
            }
        }
    }

    private void logResult(long time, int amount, int color){
        ToiletCheckin tc = new ToiletCheckin();
        tc.date = new Date(time);
        if (amount > 0) {
            tc.amount = amount;
            tc.color = color;
            tc.save();
            Toast.makeText(getApplicationContext(), "poop logged!", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(), "toilet visit logged!", Toast.LENGTH_SHORT).show();

    }
}
