package com.softpro.personaldiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by User on 30-Jul-16.
 */
public class MyHistoryAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    String email;
    String[] values;

    public MyHistoryAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        email = HistoryActivity.preferences.getString("Email", "abc");
        DBManager dbManager = new DBManager(context);
        values = dbManager.showDiaryContent(email);


    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.diary_layout, null);
        TextView leftTextView = (TextView) convertView.findViewById(R.id.leftTextView);
        leftTextView.setTypeface(ViewContactsActivity.typeface);
        leftTextView.setText(values[position] + "");

        return convertView;
    }
}
