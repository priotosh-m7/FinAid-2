package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InterestResult extends AppCompatActivity {
    TextView headBox, principle, period, roi, interest, total;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_result);

        Intent intent = getIntent();
        headBox = findViewById(R.id.textView14);
        principle = findViewById(R.id.textView21);
        period = findViewById(R.id.textView22);
        roi = findViewById(R.id.textView23);
        interest = findViewById(R.id.textView24);
        total = findViewById(R.id.textView25);
        home = findViewById(R.id.button5);

        headBox.setText(intent.getStringExtra("total"));
        principle.setText(intent.getStringExtra("principle"));
        period.setText(intent.getStringExtra("period"));
        roi.setText(intent.getStringExtra("rate"));
        interest.setText(intent.getStringExtra("interest"));
        total.setText(intent.getStringExtra("total"));

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InterestResult.this,HomePage.class);
                startActivity(intent1);
            }
        });
    }
}