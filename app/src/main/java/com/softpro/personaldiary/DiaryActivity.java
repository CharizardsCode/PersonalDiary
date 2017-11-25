package com.softpro.personaldiary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

public class DiaryActivity extends AppCompatActivity {
    static TextView dateTextView;
    static String date;
    Intent intent;
    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;
    static Typeface typeface;
    String email;
    static ViewPager viewPager;
    static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        setContentView(R.layout.activity_diary);
        typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        Calendar calendar;
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        date = day + "-" + (month + 1) + "-" + year;
        editor = preferences.edit();
        editor.putString("Date", date);
        editor.apply();
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new DiaryAdapter(this));
        manager = getSupportFragmentManager();


    }

    @Override
    public void onBackPressed() {
        intent = new Intent(DiaryActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}



