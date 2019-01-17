package com.example.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class AlignActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bLeft, bCenter, bRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);

        bLeft = findViewById(R.id.btnLeft);
        bCenter = findViewById(R.id.btnCenter);
        bRight = findViewById(R.id.btnRight);

        bLeft.setOnClickListener(this);
        bCenter.setOnClickListener(this);
        bRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.btnLeft:
                intent.putExtra("alignment", Gravity.LEFT);
                break;
            case R.id.btnCenter:
                intent.putExtra("alignment", Gravity.CENTER);
                break;
            case R.id.btnRight:
                intent.putExtra("alignment", Gravity.RIGHT);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
