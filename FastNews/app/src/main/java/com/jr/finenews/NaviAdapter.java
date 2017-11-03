package com.jr.finenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alfo6-6 on 2017-07-14.
 */

public class NaviAdapter extends BaseAdapter {

    ArrayList<NaviItem> items;
    Context context;

    public NaviAdapter(ArrayList<NaviItem> items, Context context) {
        this.items = items;
        this.context = context;


    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) convertView= LayoutInflater.from(context).inflate(R.layout.navi_listview,parent,false);

        ImageView icon=(ImageView)convertView.findViewById(R.id.icon);
        TextView tv=(TextView)convertView.findViewById(R.id.tv_navi);

        icon.setImageResource(items.get(position).getNaviIcon());
        tv.setText(items.get(position).getNaviName());
        return convertView;
    }
}
