package edu.derp.esillen.toiletapp.CustomAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.derp.esillen.toiletapp.Globals.GlobalVars;
import edu.derp.esillen.toiletapp.R;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

/**
 * Created by esillen on 2017-03-30.
 */

public class LogEntryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> colors;
    ArrayList<Integer> consistencies;
    ArrayList<Date> log_times;
    LayoutInflater inflater;

    public LogEntryAdapter(Context applicationContext, ArrayList<Integer> cols, ArrayList<Integer> cons, ArrayList<Date> l_t) {
        context = applicationContext;
        consistencies = cons;
        log_times = l_t;
        colors = cols;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return consistencies.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.log_entry_style, null);
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(log_times.get(i));
        sb.append(calendar.get(Calendar.HOUR_OF_DAY) + ":" + (Integer.toString(calendar.get(Calendar.MINUTE)).length() == 1 ? "0" : "") + calendar.get(Calendar.MINUTE));
        sb.append(" " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR));
        ((TextView) view.findViewById(R.id.label)).setText(sb.toString());

        if (colors.get(i) != -1){ view.findViewById(R.id.color_indicator).setBackgroundColor(colors.get(i));}
        else{view.findViewById(R.id.color_indicator).setVisibility(View.INVISIBLE);}

        ImageView bristol_indicator = (ImageView) view.findViewById(R.id.bristolImageView);
        if (consistencies.get(i) != -1) { bristol_indicator.setImageResource(GlobalVars.consistency_icons[consistencies.get(i)]);}
        else{ bristol_indicator.setVisibility(View.INVISIBLE);}
        return view;
    }
}
