package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InflationResult extends AppCompatActivity {
    TextView principle, adj, ret, net , headBox;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflation_result);
        Intent intent = getIntent();
        principle = findViewById(R.id.textView21);
        headBox = findViewById(R.id.textView14);
        adj = findViewById(R.id.textView22);
        ret = findViewById(R.id.textView23);
        net = findViewById(R.id.textView25);
        home = findViewById(R.id.button5);
        principle.setText(intent.getStringExtra("principle"));
        adj.setText(intent.getStringExtra("adj"));
        ret.setText(intent.getStringExtra("ret"));
        net.setText(intent.getStringExtra("net"));
        headBox.setText(intent.getStringExtra("net"));
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InflationResult.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}