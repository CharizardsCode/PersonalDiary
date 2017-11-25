package com.softpro.personaldiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Intent intent;
    boolean isExit;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button diaryButton, detailsButton, logOutButton, contactsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent = getIntent();
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        Button noteButton = (Button) findViewById(R.id.noteButton);
        diaryButton = (Button) findViewById(R.id.diaryButton);
        detailsButton = (Button) findViewById(R.id.detailsButton);
        contactsButton = (Button) findViewById(R.id.contactsButton);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        diaryButton.setTypeface(typeface);
        detailsButton.setTypeface(typeface);
        contactsButton.setTypeface(typeface);
        logOutButton.setTypeface(typeface);
        noteButton.setTypeface(typeface);

    }

    public void details(View v) {
        intent = new Intent(HomeActivity.this, DetailsActivity.class);
        startActivity(intent);
        finish();
    }

    public void diary(View v) {
        intent = new Intent(HomeActivity.this, DiaryActivity.class);
        startActivity(intent);
        finish();
    }

    public void contacts(View v) {
        intent = new Intent(HomeActivity.this, ContactsActivity.class);
        startActivity(intent);
        finish();
    }

    public void history(View v) {
        intent = new Intent(HomeActivity.this, HistoryActivity.class);
        startActivity(intent);
        finish();
    }

    public void logOut(View v) {
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        editor = preferences.edit();
        editor.putBoolean("isLogin", false);
        editor.apply();
        intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (isExit == false) {
            Toast.makeText(HomeActivity.this, "Press Back Again to close the application", Toast.LENGTH_SHORT).show();
            isExit = true;
        } else
            finish();
    }

}
