package com.example.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    private ArrayList<Integer> resIds;

    public SpinnerAdapter(ArrayList<Integer> resIds) {
        this.resIds = resIds;
    }

    @Override
    public int getCount() {
        return resIds.size();
    }

    @Override
    public Object getItem(int position) {
        return resIds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spinner, parent, false);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(view.getContext().getResources().getResourceEntryName(resIds.get(position)));

        return view;
    }
}

