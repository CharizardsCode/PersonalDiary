package com.softpro.personaldiary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.location.Address;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    EditText nameEditText, addressEditText, phoneEditText, bloodGroupEditText, panEditText, faxEditText;
    static TextView dobTextView;
    String name, address, phone, email, bloodGroup, pan, fax, dob;
    static String date;
    Intent intent;
    SharedPreferences preferences;
    String[] values = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        intent = getIntent();
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        email = preferences.getString("Email", "abc");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        bloodGroupEditText = (EditText) findViewById(R.id.bloodGroupEditText);
        panEditText = (EditText) findViewById(R.id.panEditText);
        faxEditText = (EditText) findViewById(R.id.faxEditText);
        dobTextView = (TextView) findViewById(R.id.dobTextView);
        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setTypeface(typeface);
        nameEditText.setTypeface(typeface);
        addressEditText.setTypeface(typeface);
        phoneEditText.setTypeface(typeface);
        bloodGroupEditText.setTypeface(typeface);
        panEditText.setTypeface(typeface);
        faxEditText.setTypeface(typeface);
        dobTextView.setTypeface(typeface);
        name = nameEditText.getText().toString().trim();
        address = addressEditText.getText().toString().trim();
        phone = phoneEditText.getText().toString().trim();
        bloodGroup = bloodGroupEditText.getText().toString().trim();
        pan = panEditText.getText().toString().trim();
        fax = faxEditText.getText().toString().trim();
        dob = dobTextView.getText().toString().trim();
        DBManager dbManager = new DBManager(this);
        values = dbManager.fetchValues(email);
        nameEditText.setText(values[0]);
        dobTextView.setText(values[1]);
        addressEditText.setText(values[2]);
        phoneEditText.setText(values[3]);
        bloodGroupEditText.setText(values[4]);
        panEditText.setText(values[5]);
        faxEditText.setText(values[6]);
    }

    public void update(View v) {
        name = nameEditText.getText().toString().trim();
        address = addressEditText.getText().toString().trim();
        phone = phoneEditText.getText().toString().trim();
        bloodGroup = bloodGroupEditText.getText().toString().trim();
        pan = panEditText.getText().toString().trim();
        fax = faxEditText.getText().toString().trim();
        dob = dobTextView.getText().toString().trim();
        UserBean userBean = new UserBean();
        userBean.setName(name);
        userBean.setAddress(address);
        userBean.setPhone(phone);
        userBean.setDOB(dob);
        userBean.setBloodGroup(bloodGroup);
        userBean.setPan(pan);
        userBean.setFax(fax);
        DBManager dbManager = new DBManager(this);
        long rowId = dbManager.updateData(userBean, email);
        if (rowId > 0) {
            Toast.makeText(this, "Details Updated Successfully!!!", Toast.LENGTH_LONG).show();
            intent = new Intent(UpdateActivity.this, DetailsActivity.class);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(this, "Details Update failed!!!", Toast.LENGTH_LONG).show();

    }

    public void selectDate(View v) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "DatePicker");
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle SavedInstance) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date = day + "-" + (month + 1) + "-" + year;
            dobTextView.setText("DOB " + date);

        }


    }

    @Override
    public void onBackPressed() {
        intent = new Intent(UpdateActivity.this, DetailsActivity.class);
        startActivity(intent);
        finish();
    }
}
