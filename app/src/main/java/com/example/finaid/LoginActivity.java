package com.example.finaid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button signupButton;
    Button login;
    SQLiteDatabase db;
    TextView output ;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupButton = findViewById(R.id.signupButton);
        login = findViewById(R.id.loginButton);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        db=openOrCreateDatabase("FinAidDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Users(email VARCHAR,name VARCHAR,phone NUMERIC , dob VARCHAR, password VARCHAR);");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = db.rawQuery("SELECT * from Users where email='" + email.getText()+ "';",null);
                if(result.getCount()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Account does not exists. Sign Up to continue");
                    builder.setTitle("No Account Found");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                        dialog.cancel();
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else {
                    result.moveToFirst();
                    if(!password.getText().toString().equals(result.getString(4))){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Incorrect Password");
                        builder.setTitle("Incorrect Credentials");
                        builder.setCancelable(true);
                        builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {
                            dialog.cancel();
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else {
                        String Name = result.getString(1);
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomePage.class);
                        intent.putExtra("User", Name);
                        startActivity(intent);
                    }
                }


            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}