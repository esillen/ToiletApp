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

    private final int POOP_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
        SugarContext.init(getApplicationContext());
        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());
        */
    }


    public void checkin(View view){
        Intent intent = new Intent(this, PoopingActivity.class);
        startActivityForResult(intent, POOP_REQUEST_CODE);
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
                    long date = data.getLongExtra(getResources().getString(R.string.request_date_key), -1);
                    int amount = data.getIntExtra(getResources().getString(R.string.request_amount_key), -1);
                    int color = data.getIntExtra(getResources().getString(R.string.request_color_key), -1);
                    int consistency = data.getIntExtra(getResources().getString(R.string.request_consistency_key), -1);
                    logResult(date, amount, color, consistency);
                }
                break;
            }
        }
    }

    private void logResult(long date, int amount, int color, int consistency){
        ToiletCheckin tc = new ToiletCheckin();
        tc.date = new Date(date);
        // Log -1 if they don't exist. Handle somewhere else :)
        tc.amount = amount;
        tc.color = color;
        tc.consistency = consistency;
        tc.save();

        if (amount > 0) {
            Toast.makeText(getApplicationContext(), "poop logged!", Toast.LENGTH_SHORT).show();
    }
        else {
            Toast.makeText(getApplicationContext(), "toilet visit logged!", Toast.LENGTH_SHORT).show();
        }
    }

    public void openProfile(View view){
        startActivity(new Intent(this, ProfileActivity.class));
    }

}
