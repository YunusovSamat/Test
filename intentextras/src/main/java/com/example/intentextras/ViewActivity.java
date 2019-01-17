package com.example.intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    private TextView tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tvView = findViewById(R.id.tvView);

        Intent intent = getIntent();
        String fName = intent.getStringExtra("fName");
        String lName = intent.getStringExtra("lName");

        tvView.setText(fName + " " + lName);
    }
}
