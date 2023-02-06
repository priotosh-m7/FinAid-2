package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    Button signUp, pickdate;
    Button login;
    EditText firstName, lastName, email, phone, dob , password, cpassword;
    TextView passwordMatch;
    CheckBox checkBox;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp = findViewById(R.id.signupButton);
        login = findViewById(R.id.loginButton);
        firstName = findViewById(R.id.editTextFName);
        lastName = findViewById(R.id.editTextLName);
        email = findViewById(R.id.editTextTextEmailAddress);
        phone = findViewById(R.id.editTextPhone);
        pickdate = findViewById(R.id.button6);
        dob = findViewById(R.id.editDateofBirth);
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datepicker = new DatePickerDialog(SignUp.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String str = dayOfMonth + "-" + (month + 1) + "-" + year;
                                    dob.setText(str);
                                }
                            },year, month, day);

            }
        });

        password = findViewById(R.id.editTextTextPassword);
        cpassword = findViewById(R.id.editTextConfirmPassword);
        passwordMatch = findViewById(R.id.passwordmatch);
        checkBox = findViewById(R.id.checkBox2);
        String name = firstName.getText().toString()+" "+lastName.getText().toString();
        db=openOrCreateDatabase("FinAidDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Users(email VARCHAR,name VARCHAR,phone NUMERIC , dob VARCHAR, password VARCHAR);");



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!checkBox.isChecked()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);

                        // Set the message show for the Alert time
                        builder.setMessage("Please tick the checkbox");

                        // Set Alert Title
                        builder.setTitle("Alert !");

                        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                        builder.setCancelable(true);

                        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                        builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                            // When the user click yes button then app will close
                            dialog.cancel();
                        });

                        // Create the Alert dialog
                        AlertDialog alertDialog = builder.create();
                        // Show the Alert Dialog box
                        alertDialog.show();
                    }else{
                        if(!password.getText().toString().equals(cpassword.getText().toString())){
                            passwordMatch.setText("Passwords don't Match. Try Again");
                        }else{
                            if(password.getText().toString().length()<9){
                                passwordMatch.setText("Password should be atleast of 8 characters");
                                return;
                            }
                            if(firstName.getText().toString().length()==0||lastName.getText().toString().length()==0||email.getText().toString().length()==0||phone.getText().toString().length()==0||dob.getText().toString().length()==0){
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                builder.setMessage("Please fill all the fields");
                                builder.setTitle("Incomplete Credentials");
                                builder.setCancelable(true);
                                builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                                    dialog.cancel();
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }else {
                                try{db.execSQL("INSERT into Users VALUES('"+email.getText()+"','"+firstName.getText()+" "+lastName.getText()+"','"+phone.getText()+"','"+dob.getText()+"','"+password.getText()+"');");
                                    Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp.this,LoginActivity.class);
                                    startActivity(intent);

                                }catch (SQLException s){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                                    builder.setMessage(s.toString());
                                    builder.setTitle("Error");
                                    builder.setCancelable(true);
                                    builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                                        dialog.cancel();
                                    });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }




                            }
                        }

                    }


            }
        });

    }
}