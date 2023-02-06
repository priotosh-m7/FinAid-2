package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    Button salary , interest, inflation, loan;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        display = findViewById(R.id.textView10);

        Intent intent = getIntent();
        //intent.getExtras();
        String Name = intent.getStringExtra("User");
        display.setText("Welcome "+ Name);

        salary = findViewById(R.id.button);
        interest = findViewById(R.id.button2);
        inflation = findViewById(R.id.button3);
        loan = findViewById(R.id.button4);

        salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,SalaryActivity.class);

                startActivity(intent);
            }
        });

        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,InterestActivity.class);

                startActivity(intent);
            }
        });

        loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,LoanActivity.class);

                startActivity(intent);
            }
        });
        inflation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,InflationActivity2.class);

                startActivity(intent);
            }
        });
    }
}