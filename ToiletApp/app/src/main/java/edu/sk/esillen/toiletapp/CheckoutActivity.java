package edu.sk.esillen.toiletapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void checkin(View view){
        Intent intent = new Intent(this, PoopingActivity.class);
        startActivity(intent);
    }

    public void showGraphs(View view){
        Intent intent = new Intent(this, GraphsActivity.class);
        startActivity(intent);
    }


}
