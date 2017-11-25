package com.softpro.personaldiary;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText, confirmPasswordEditText;
    String email, password, confirmPassword;
    Intent intent;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        TextView signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        emailEditText.setTypeface(typeface);
        passwordEditText.setTypeface(typeface);
        signUpTextView.setTypeface(typeface);
        intent = getIntent();
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
        confirmPasswordEditText.setTypeface(typeface);

    }

    public void register(View v) {
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        confirmPassword = confirmPasswordEditText.getText().toString().trim();
        if (email.equals(""))
            Toast.makeText(SignupActivity.this, "Enter the email", Toast.LENGTH_SHORT).show();
        else if (password.equals(""))
            Toast.makeText(SignupActivity.this, "Enter the password", Toast.LENGTH_SHORT).show();
        else if (!password.equals(confirmPassword))
            Toast.makeText(SignupActivity.this, "Passwords did not match", Toast.LENGTH_SHORT).show();
        else {
            UserBean userBean = new UserBean();
            DBManager dbManager = new DBManager(this);
            boolean exist = dbManager.checkEmail(email);
            if (exist == true)
                Toast.makeText(SignupActivity.this, "Email Already exists!!!", Toast.LENGTH_SHORT).show();
            else {
                userBean.setEmail(email);
                userBean.setPassword(password);
                long rowId = dbManager.saveData(userBean);
                if (rowId > 0) {
                    Toast.makeText(this, "Sign up successful!!!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SignupActivity.this, "Sign up not successful!!!", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }

    @Override
    public void onBackPressed() {
        intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
