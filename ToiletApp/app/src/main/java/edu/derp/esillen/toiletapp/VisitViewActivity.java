package edu.derp.esillen.toiletapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import edu.derp.esillen.toiletapp.Globals.GlobalVars;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class VisitViewActivity extends AppCompatActivity {

    public static final int ID_REQUEST_CODE = 1337;

    // TODO: should really really be renamed ToiletCheckOut......
    ToiletCheckin currently_viewed_checkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_view);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        long checkinId = intent.getLongExtra(getResources().getString(R.string.request_checkin_id_key),-1);
        currently_viewed_checkin = ToiletCheckin.findById(ToiletCheckin.class, checkinId);

        ((TextView) findViewById(R.id.amountText)).setText("Amount: " + currently_viewed_checkin.amount);

        ImageView bristol_image_view = (ImageView) findViewById(R.id.bristolImageView);
        ConstraintLayout colorLayout = (ConstraintLayout) findViewById(R.id.colorLayout);
        View colorIndicator = findViewById(R.id.color_indicator);

        if (currently_viewed_checkin.amount < 1) {
            bristol_image_view.setImageResource(R.drawable.shit_icon_nopoo);
            colorLayout.setVisibility(View.INVISIBLE);
        }
        else{
            bristol_image_view.setImageResource(GlobalVars.consistency_icons[currently_viewed_checkin.consistency]);
            colorIndicator.setBackgroundColor(currently_viewed_checkin.color);
        }
    }


    public void onEditButtonPressed(View view){
        Intent intent = new Intent(this, PoopingActivity.class);
        intent.putExtra(getResources().getString(R.string.request_checkin_id_key), currently_viewed_checkin.getId());
        startActivityForResult(intent, ID_REQUEST_CODE);
    }

    public void onDeleteButtonPreessed(View view){
        new AlertDialog.Builder(this)
                .setMessage("Really delete entry?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        currently_viewed_checkin.delete();
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    private void updateEntry(long date, int amount, int color, int consistency){
        currently_viewed_checkin.date = new Date(date);
        currently_viewed_checkin.amount = amount;
        currently_viewed_checkin.color = color;
        currently_viewed_checkin.consistency = consistency;
        currently_viewed_checkin.save();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("SHSAD", ""+requestCode);
        Log.d("SHSAD", ""+resultCode);
        switch(requestCode) {
            case (ID_REQUEST_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    long date = data.getLongExtra(getResources().getString(R.string.request_date_key), -1);
                    int amount = data.getIntExtra(getResources().getString(R.string.request_amount_key), -1);
                    int color = data.getIntExtra(getResources().getString(R.string.request_color_key), -1);
                    int consistency = data.getIntExtra(getResources().getString(R.string.request_consistency_key), -1);
                    updateEntry(date, amount, color, consistency);
                }
                break;
            }
        }
    }

}
