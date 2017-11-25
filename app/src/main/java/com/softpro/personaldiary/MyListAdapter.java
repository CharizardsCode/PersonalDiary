package com.softpro.personaldiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by User on 24-Jul-16.
 */
public class MyListAdapter extends BaseAdapter {
    String email;
    LayoutInflater inflater;
    Context context;
    String[] contacts;

    public MyListAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        email = ViewContactsActivity.preferences.getString("Email", "abc");
        DBManager dbManager = new DBManager(context);
        contacts = dbManager.viewContacts(email);


    }

    @Override
    public int getCount() {
        return contacts.length;
    }

    @Override
    public Object getItem(int position) {
        return contacts[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_layout, null);
        TextView leftTextView = (TextView) convertView.findViewById(R.id.leftTextView);
        leftTextView.setTypeface(ViewContactsActivity.typeface);
        leftTextView.setText(contacts[position] + "");

        return convertView;
    }
}
