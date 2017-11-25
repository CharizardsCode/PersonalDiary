package com.softpro.personaldiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContactsActivity extends AppCompatActivity {
    EditText nameEditText, numberEditText;
    String name, number, email;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        numberEditText = (EditText) findViewById(R.id.numberEditText);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setTypeface(typeface);
        nameEditText.setTypeface(typeface);
        numberEditText.setTypeface(typeface);
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        email = preferences.getString("Email", "abc");
    }

    public void actionSave(View v) {
        name = nameEditText.getText().toString().trim();
        number = numberEditText.getText().toString().trim();
        UserBean userBean = new UserBean();
        userBean.setEmail(email);
        userBean.setContactName(name);
        userBean.setContactNumber(number);
        DBManager dbManager = new DBManager(this);
        long rowId = dbManager.saveContact(userBean);
        if (rowId > 0) {
            Toast.makeText(AddContactsActivity.this, "Contact added Successfully!!", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");
            numberEditText.setText("");
            nameEditText.requestFocus();
        } else
            Toast.makeText(AddContactsActivity.this, "Contact added Successfully!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddContactsActivity.this, ContactsActivity.class);
        startActivity(intent);
        finish();
    }
}
