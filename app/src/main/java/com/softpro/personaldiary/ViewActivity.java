package com.softpro.personaldiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    TextView nameTextView, dobTextView, addressTextView, phoneTextView, emailTextView, bloodGroupTextView, panTextView, faxTextView;
    String email;
    SharedPreferences preferences;
    Intent intent;
    String[] values = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        intent = getIntent();
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        email = preferences.getString("Email", "abc");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        nameTextView.setTypeface(typeface);
        dobTextView = (TextView) findViewById(R.id.dobTextView);
        dobTextView.setTypeface(typeface);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        addressTextView.setTypeface(typeface);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        phoneTextView.setTypeface(typeface);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        emailTextView.setTypeface(typeface);
        bloodGroupTextView = (TextView) findViewById(R.id.bloodGroupTextView);
        bloodGroupTextView.setTypeface(typeface);
        panTextView = (TextView) findViewById(R.id.panTextView);
        panTextView.setTypeface(typeface);
        faxTextView = (TextView) findViewById(R.id.faxTextView);
        faxTextView.setTypeface(typeface);
        DBManager dbManager = new DBManager(this);
        values = dbManager.fetchValues(email);
        nameTextView.setText("Name: " + values[0]);
        dobTextView.setText(values[1]);
        addressTextView.setText("Address: " + values[2]);
        phoneTextView.setText("Phone no: " + values[3]);
        emailTextView.setText("Email: " + email);
        bloodGroupTextView.setText("Blood Group: " + values[4]);
        panTextView.setText("PAN: " + values[5]);
        faxTextView.setText("Fax no: " + values[6]);
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(ViewActivity.this, DetailsActivity.class);
        startActivity(intent);
        finish();
    }


}
