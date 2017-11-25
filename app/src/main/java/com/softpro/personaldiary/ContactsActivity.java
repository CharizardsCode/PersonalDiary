package com.softpro.personaldiary;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactsActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        Button addContactButton = (Button) findViewById(R.id.addContactButton);
        Button viewContactButton = (Button) findViewById(R.id.viewContactButton);
        addContactButton.setTypeface(typeface);
        viewContactButton.setTypeface(typeface);
    }

    public void actionAdd(View v) {
        intent = new Intent(ContactsActivity.this, AddContactsActivity.class);
        startActivity(intent);
        finish();
    }

    public void actionView(View v) {
        intent = new Intent(ContactsActivity.this, ViewContactsActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(ContactsActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
