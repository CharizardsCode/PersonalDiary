package com.softpro.personaldiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    String email, password;
    TextView loginTextView;
    Intent intent;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Typeface typeface;
    boolean isExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        preferences = getSharedPreferences(DBConstants.MY_PREF, MODE_PRIVATE);
        boolean islogin = preferences.getBoolean("isLogin", false);
        if (islogin == true) {
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        emailEditText.setTypeface(typeface);
        passwordEditText.setTypeface(typeface);
        loginTextView.setTypeface(typeface);
    }

    public void actionLogin(View v) {
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        DBManager dbManager = new DBManager(this);
        String password1 = dbManager.checkLogin(email);
        if (password1.equals(password)) {
            editor = preferences.edit();
            editor.putString("Email", email);
            editor.putBoolean("isLogin", true);
            editor.apply();
            intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            emailEditText.setText("");
            passwordEditText.setText("");
            emailEditText.requestFocus();
        }

    }

    public void actionSignUp(View v) {
        intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        if (isExit == false) {
            Toast.makeText(LoginActivity.this, "Press Back Again to close the application", Toast.LENGTH_SHORT).show();
            isExit = true;
        } else
            finish();
    }

}
