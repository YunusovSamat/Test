package com.example.intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etFName, etLName;
    private Button bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFName = findViewById(R.id.etFName);
        etLName = findViewById(R.id.etLName);
        bSubmit = findViewById(R.id.btnSubmit);

        bSubmit.setOnClickListener(this);
    }


    @Override
     public void onClick(View v) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("fName", etFName.getText().toString());
        intent.putExtra("lName", etLName.getText().toString());
        startActivity(intent);
    }
}
