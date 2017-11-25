package com.softpro.personaldiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by User on 30-Jul-16.
 */
public class HistoryActivity extends AppCompatActivity {
    static SharedPreferences preferences;
    static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        TextView contactsTextView = (TextView) findViewById(R.id.historyTextView);
        contactsTextView.setTypeface(typeface);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyHistoryAdapter(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HistoryActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}