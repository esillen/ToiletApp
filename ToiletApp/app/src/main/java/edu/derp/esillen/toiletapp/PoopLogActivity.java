package edu.derp.esillen.toiletapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import edu.derp.esillen.toiletapp.CustomAdapters.LogEntryAdapter;
import edu.derp.esillen.toiletapp.R;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

public class PoopLogActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poop_log);
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateConfiguration(getResources().getConfiguration());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateConfiguration(newConfig);
    }

    private void  updateConfiguration(Configuration newConfig){
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showGraphs();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            showLogs();
        }
    }

    public void showLogs(){
        List<ToiletCheckin> checkins = ToiletCheckin.listAll(ToiletCheckin.class);
        Collections.reverse(checkins);
        LogEntryAdapter adapter = new LogEntryAdapter(this, checkins);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public void showGraphs(){
        viewSpectrogram();
    }

    public void viewSpectrogram(){
        List<ToiletCheckin> checkins = ToiletCheckin.listAll(ToiletCheckin.class);
        GraphView graph = (GraphView) findViewById(R.id.graphView);

        // Get data from database
        int times_per_hour[] = new int[24];
        Calendar calendar = Calendar.getInstance();
        for(ToiletCheckin checkin : checkins){
            calendar.setTime(checkin.date);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            times_per_hour[hour] += 1;
        }

        // Set datapoints
        ArrayList<DataPoint> datapoints = new ArrayList<>();
        for(int i=0;i<24;i++){
            datapoints.add(new DataPoint(i, times_per_hour[i]));
        }

        // Update the graph
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(datapoints.toArray(new DataPoint[datapoints.size()]));

        // Styling
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        graph.addSeries(series);
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, VisitViewActivity.class);
        intent.putExtra(getResources().getString(R.string.request_checkin_id_key), id);
        startActivity(intent);
    }
}
