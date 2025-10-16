package com.example.echoandlistapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CitiesAdapter extends BaseAdapter {
    private final Context ctx;
    private final String[] names;
    private final TypedArray icons;
    private final LayoutInflater inflater;
    public CitiesAdapter(Context ctx, String[] names, TypedArray icons) {
        this.ctx = ctx;
        this.names = names;
        this.icons = icons;
        this.inflater = LayoutInflater.from(ctx);
    }
    @Override public int getCount() { return names.length; }
    @Override public Object getItem(int position) { return names[position]; }
    @Override public long getItemId(int position) { return position; }
    private View bind(View convertView, int position, ViewGroup parent) {
        View v = convertView;
        if (v == null) v = inflater.inflate(R.layout.row_city_spinner, parent, false);
        ImageView img = v.findViewById(R.id.imgCity);
        TextView tv = v.findViewById(R.id.tvCity);
        tv.setText(names[position]);
        img.setImageResource(icons.getResourceId(position, 0));
        return v;
    }
    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return bind(convertView, position, parent);
    }
    @Override public View getView(int position, View convertView, ViewGroup parent) {
        return bind(convertView, position, parent);
    }
}
