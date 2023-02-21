package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InflationResult extends AppCompatActivity {
    TextView principle, adj, ret, net , headBox;
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

        principle.setText(intent.getStringExtra("principle"));
        adj.setText(intent.getStringExtra("adj"));
        ret.setText(intent.getStringExtra("ret"));
        net.setText(intent.getStringExtra("net"));

    }
}