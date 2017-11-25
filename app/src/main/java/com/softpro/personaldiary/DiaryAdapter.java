package com.softpro.personaldiary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by User on 19-Jul-16.
 */
public class DiaryAdapter extends PagerAdapter {
    static Context context;
    LayoutInflater inflater;
    int i;
    String[] dates = new String[356];
    int day;
    Button editButton, saveButton;
    ImageView calendarImageView;
    String email;
    Typeface typeface;
    android.support.v4.app.Fragment fragment;

    public DiaryAdapter(Context context) {

        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        email = DiaryActivity.preferences.getString("Email", "abc");
        Calendar calendar1 = Calendar.getInstance();
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        int month = calendar1.get(Calendar.MONTH);
        int year = calendar1.get(Calendar.YEAR);
        String defDate = day1 + "-" + (month + 1) + "-" + year;
        String date = DiaryActivity.preferences.getString("Date", defDate);
        typeface = DiaryActivity.typeface;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse(date));
            day = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (Exception ee) {
        }
        if (day == 1)
            dates[0] = date + "  Sunday";
        else if (day == 2)
            dates[0] = date + "  Monday";
        else if (day == 3)
            dates[0] = date + "  Tuesday";
        else if (day == 4)
            dates[0] = date + "  Wednesday";
        else if (day == 5)
            dates[0] = date + "  Thursday";
        else if (day == 6)
            dates[0] = date + "  Friday";
        else
            dates[0] = date + "  Saturday";
        for (i = 1; i < 356; i++) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(simpleDateFormat.parse(date));
                calendar.add(Calendar.DATE, 1);
                date = simpleDateFormat.format(calendar.getTime());
                day = calendar.get(Calendar.DAY_OF_WEEK);

            } catch (Exception ee) {

            }
            if (day == 1)
                dates[i] = date + "   Sun";
            else if (day == 2)
                dates[i] = date + "   Mon";
            else if (day == 3)
                dates[i] = date + "   Tues";
            else if (day == 4)
                dates[i] = date + "   Wed";
            else if (day == 5)
                dates[i] = date + "   Thurs";
            else if (day == 6)
                dates[i] = date + "   Fri";
            else
                dates[i] = date + "   Sat";
        }


    }


    @Override
    public int getCount() {
        return dates.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        View v = inflater.inflate(R.layout.item_layout, null);
        editButton = (Button) v.findViewById(R.id.editButton);
        final TextView showTextView = (TextView) v.findViewById(R.id.showTextView);
        final EditText editText = (EditText) v.findViewById(R.id.editText);
        TextView textView = (TextView) v.findViewById(R.id.currentDateTextView);
        saveButton = (Button) v.findViewById(R.id.saveButton);
        calendarImageView = (ImageView) v.findViewById(R.id.calendarImageView);
        editButton.setTypeface(typeface);
        textView.setTypeface(typeface);
        showTextView.setTypeface(typeface);
        editText.setTypeface(typeface);
        saveButton.setTypeface(typeface);
        textView.setText(dates[position] + "");
        DBManager dbManager = new DBManager(context);
        String content = dbManager.showContent(email, dates[position]);
        showTextView.setText(content);


        calendarImageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                android.support.v4.app.DialogFragment fragment = new DatePickerFragment();
                fragment.show(DiaryActivity.manager, "DatePicker");


            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(context);
                String content = dbManager.showContent(email, dates[position]);
                editText.setVisibility(View.VISIBLE);
                editText.setText(content);
                editText.requestFocus();
                Toast.makeText(context, "Edit the text!!", Toast.LENGTH_SHORT).show();
                showTextView.setVisibility(View.GONE);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString().trim();
                UserBean userBean = new UserBean();
                userBean.setEmail(email);
                userBean.setDate(dates[position]);
                userBean.setContent(content);
                DBManager dbManager = new DBManager(context);
                long row = dbManager.saveContent(userBean);
                if (row > 0) {
                    Toast.makeText(context, "Content Saved!!", Toast.LENGTH_SHORT).show();
                    showTextView.setText(content);
                    showTextView.setVisibility(View.VISIBLE);
                    showTextView.requestFocus();
                    editText.setVisibility(View.GONE);

                } else {
                    long rowId = dbManager.updateContent(userBean, email, dates[position]);
                    if (rowId > 0) {
                        Toast.makeText(context, "Content Saved!!", Toast.LENGTH_SHORT).show();
                        showTextView.setVisibility(View.VISIBLE);
                        showTextView.setText(content);
                        showTextView.requestFocus();
                        editText.setVisibility(View.GONE);
                    } else
                        Toast.makeText(context, "Content Not Saved!!", Toast.LENGTH_SHORT).show();
                }

            }

        });


        container.addView(v);
        return v;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View) object;
        container.removeView(v);
    }

    public static class DatePickerFragment extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener {


        Calendar calendar;
        SharedPreferences.Editor editor;
        String date;

        @Override
        public Dialog onCreateDialog(Bundle SavedInstance) {
            int day = 1, month = 1, year = 2016;
            calendar = Calendar.getInstance();
            String date = DiaryActivity.preferences.getString("Date", "01-01-2016");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(simpleDateFormat.parse(date));
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

            } catch (Exception ee) {
            }
            return new

                    DatePickerDialog(getActivity(),

                    this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date = day + "-" + (month + 1) + "-" + year;
            editor = DiaryActivity.preferences.edit();
            editor.putString("Date", date);
            editor.apply();
            DiaryActivity.viewPager.setAdapter(new DiaryAdapter(context));


        }

    }


}

