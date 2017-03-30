package edu.derp.esillen.toiletapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.derp.esillen.toiletapp.R;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class PoopLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poop_log);
    }

    @Override
    protected void onResume(){
        super.onResume();
        List<ToiletCheckin> checkins = ToiletCheckin.listAll(ToiletCheckin.class);
        ArrayList<String> strings = new ArrayList<>();
        for(ToiletCheckin tc:checkins){
            strings.add(tc.date.toString());
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.log_entry_style,strings);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    public void showGraphs(View view){
        startActivity(new Intent(this, GraphsActivity.class));
    }

}
