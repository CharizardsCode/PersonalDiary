package com.softpro.personaldiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class ViewContactsActivity extends AppCompatActivity {
    static SharedPreferences preferences;
    static Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        TextView contactsTextView = (TextView) findViewById(R.id.contactsTextView);
        contactsTextView.setTypeface(typeface);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyListAdapter(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewContactsActivity.this, ContactsActivity.class);
        startActivity(intent);
        finish();
    }
}
