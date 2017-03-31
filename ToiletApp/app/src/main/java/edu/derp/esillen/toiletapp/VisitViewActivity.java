package edu.derp.esillen.toiletapp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.derp.esillen.toiletapp.Globals.GlobalVars;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class VisitViewActivity extends AppCompatActivity {

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
        int checkinId = intent.getIntExtra(getResources().getString(R.string.request_checkin_id_key),-1);
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


    public void editVisit(View view){
        // TODO: go to edit and pass some intents
    }

    public void deleteVisit(View view){
        currently_viewed_checkin.delete();
        finish();
    }

}
