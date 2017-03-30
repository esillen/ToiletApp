package edu.derp.esillen.toiletapp.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.derp.esillen.toiletapp.Globals.GlobalVars;
import edu.derp.esillen.toiletapp.R;

/**
 * Created by esillen on 2017-03-30.
 */

public class BristolAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflter;

    public BristolAdapter(Context applicationContext) {
        context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return GlobalVars.consistencies.length;
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
        view = inflter.inflate(R.layout.bristol_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.bristolImageView);
        icon.setImageResource(GlobalVars.consistencies[i]);
        return view;
    }
}
