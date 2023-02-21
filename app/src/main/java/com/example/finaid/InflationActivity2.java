package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class InflationActivity2 extends AppCompatActivity {
    EditText Principal, Tenure, inflationRate, roiRate;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflation2);
        Principal = findViewById(R.id.editTextNumber2);
        Tenure = findViewById(R.id.editTextNumber4);
        inflationRate = findViewById(R.id.editTextNumber3);
        roiRate = findViewById(R.id.editTextNumber5);
        Submit = findViewById(R.id.button5);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Principal.length()==0||Tenure.length()==0||inflationRate.length()==0||roiRate.length()==0){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(InflationActivity2.this);
                    builder.setMessage("Fill all the fields correctly");
                    builder.setTitle("Incomplete Details");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    double principle = Double.parseDouble(Principal.getText().toString().trim());
                    double tenure = Double.parseDouble(Tenure.getText().toString().trim());
                    double inflationR = Double.parseDouble(inflationRate.getText().toString().trim());
                    inflationR = inflationR/100;
                    double roiR = Double.parseDouble(roiRate.getText().toString().trim());
                    roiR = roiR/100;
                    double adjAmount = Math.round( (principle*Math.pow((1+inflationR),tenure)));
                    DecimalFormat f = new DecimalFormat("##.00");
                    String adjAmountresult = f.format(adjAmount);
                    double ta = Math.round((principle*Math.pow((1+roiR),tenure)));
                    String Returns = f.format(ta);
                    double netPL = ta - adjAmount;
                    String net = f.format(netPL);
                    Intent intent = new Intent(InflationActivity2.this,InflationResult.class);
                    intent.putExtra("principle",Principal.getText().toString().trim());
                    intent.putExtra("adj",adjAmountresult);
                    intent.putExtra("ret",Returns);
                    intent.putExtra("net",net);
                    startActivity(intent);
                }
            }
        });

    }
}