package edu.derp.esillen.toiletapp.CustomAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.derp.esillen.toiletapp.Globals.GlobalVars;
import edu.derp.esillen.toiletapp.R;
import edu.derp.esillen.toiletapp.table_entries.ToiletCheckin;

/**
 * Created by esillen on 2017-03-30.
 */

public class LogEntryAdapter extends BaseAdapter {
    Context context;
    List<ToiletCheckin> toiletCheckins;
    LayoutInflater inflater;

    public LogEntryAdapter(Context applicationContext, List<ToiletCheckin> t_c_i){
        context = applicationContext;
        toiletCheckins = t_c_i;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return toiletCheckins.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return toiletCheckins.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.log_entry_style, null);
        ToiletCheckin t_c_i = toiletCheckins.get(i);

        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(t_c_i.date);
        sb.append(calendar.get(Calendar.HOUR_OF_DAY) + ":" + (Integer.toString(calendar.get(Calendar.MINUTE)).length() == 1 ? "0" : "") + calendar.get(Calendar.MINUTE));
        sb.append(" " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR));
        ((TextView) view.findViewById(R.id.label)).setText(sb.toString());

        if (t_c_i.color != -1){ view.findViewById(R.id.color_indicator).setBackgroundColor(t_c_i.color);}
        else{view.findViewById(R.id.color_indicator).setVisibility(View.INVISIBLE);}

        ImageView bristol_indicator = (ImageView) view.findViewById(R.id.bristolImageView);
        if (t_c_i.consistency != -1) { bristol_indicator.setImageResource(GlobalVars.consistency_icons[t_c_i.consistency]);}
        else{ bristol_indicator.setVisibility(View.INVISIBLE);}

        return view;
    }
}
