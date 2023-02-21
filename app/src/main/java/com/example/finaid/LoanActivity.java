package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class LoanActivity extends AppCompatActivity {
    EditText headBox, principal, period, roi, interest, total;
    Button Submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        principal = findViewById(R.id.editTextNumber1);
        period = findViewById(R.id.editTextNumber2);
        roi = findViewById(R.id.editTextNumber3);

        Submit = findViewById(R.id.button5);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(principal.length()==0||period.length()==0||roi.length()==0){
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(LoanActivity.this);
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
                    double p = Double.parseDouble(principal.getText().toString().trim());
                    double n = Double.parseDouble(period.getText().toString().trim());
                    double r = Double.parseDouble(roi.getText().toString().trim());
                    r = r/12;
                    r = r/100;


                    double emi = p*r*(Math.pow((1+r),n))/(Math.pow((1+r),n)-1);
                    double payable = emi*n;
                    double intAmt = payable-p;
                    DecimalFormat f = new DecimalFormat("##.00");
                    String pay = f.format(payable);
                    //double ta = Math.round((principle*Math.pow((1+roiR),tenure)));
                    String interestAmt = f.format(intAmt);
                    //double netPL = ta - adjAmount;
                    String EMI = f.format(emi);
                    Intent intent = new Intent(LoanActivity.this,LoanResultActivity.class);
                    intent.putExtra("principle",principal.getText().toString().trim());
                    intent.putExtra("period",period.getText().toString().trim());
                    intent.putExtra("rate",roi.getText().toString().trim());
                    intent.putExtra("emi",EMI);
                    intent.putExtra("payable",pay);
                    intent.putExtra("interest",interestAmt);
                    startActivity(intent);
                }
            }
        });
    }
}