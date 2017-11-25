package com.softpro.personaldiary;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font2.TTF");
        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button viewButton = (Button) findViewById(R.id.viewButton);
        updateButton.setTypeface(typeface);
        viewButton.setTypeface(typeface);
    }

    public void update(View v) {
        intent = new Intent(DetailsActivity.this, UpdateActivity.class);
        startActivity(intent);
        finish();
    }

    public void view(View v) {
        intent = new Intent(DetailsActivity.this, ViewActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(DetailsActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
