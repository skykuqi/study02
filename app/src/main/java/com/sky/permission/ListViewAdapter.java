package com.sky.permission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class ListViewAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;
    private LayoutInflater layoutInflater;
    public ListViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(list.get(position));
        return view;
    }
}
