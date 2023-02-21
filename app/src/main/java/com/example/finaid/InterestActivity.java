package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.text.DecimalFormat;

public class InterestActivity extends AppCompatActivity {
    EditText principal, period, rate;
    ToggleButton intType;
    Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        principal = findViewById(R.id.editTextNumber1);
        period = findViewById(R.id.editTextNumber2);
        rate = findViewById(R.id.editTextNumber3);

        intType = findViewById(R.id.toggleButton);

        Submit = findViewById(R.id.button5);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(principal.length()==0||period.length()==0||rate.length()==0){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(InterestActivity.this);
                    builder.setMessage("Fill all the fields correctly");
                    builder.setTitle("Incomplete Details");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    double p = Double.parseDouble(principal.getText().toString().trim());
                    double n = Double.parseDouble(period.getText().toString().trim());
                    double r = Double.parseDouble(rate.getText().toString().trim());
                    n = n/12;
                    double interest= 0;
                    double total = 0;
                    if(intType.getText().toString().equals("Simple Interest")){

                         interest = (p*n*r)/100;
                         total = interest+p;
                    }else {
                        r = r/100;
                        total = p* Math.pow((1 + r/1),(1*n));
                        interest = total - p;
                    }

                    DecimalFormat f = new DecimalFormat("##.00");
                    String tot = f.format(total);
                    //double ta = Math.round((principle*Math.pow((1+roiR),tenure)));
                    String interestAmt = f.format(interest);
                    //double netPL = ta - adjAmount;
                    //String EMI = f.format(emi);
                    Intent intent = new Intent(InterestActivity.this,InterestResult.class);
                    intent.putExtra("principle",principal.getText().toString().trim());
                    intent.putExtra("period",period.getText().toString().trim());
                    intent.putExtra("rate",rate.getText().toString().trim());
                    //intent.putExtra("emi",EMI);
                    intent.putExtra("total",tot);
                    intent.putExtra("interest",interestAmt);
                    startActivity(intent);
                }
            }
        });
    }
}