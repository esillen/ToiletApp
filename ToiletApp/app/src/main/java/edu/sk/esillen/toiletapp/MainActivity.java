package edu.sk.esillen.toiletapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

public class MainActivity extends AppCompatActivity {

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
        startActivity(intent);
    }

    public void showGraphs(View view){
        Intent intent = new Intent(this, GraphsActivity.class);
        startActivity(intent);
    }
}
