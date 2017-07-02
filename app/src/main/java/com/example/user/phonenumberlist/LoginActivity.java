package com.example.user.phonenumberlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static EditText passwordEt;
    public static EditText emailEt;
    Button logIn;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = (EditText)findViewById(R.id.emailEditTextId);
        passwordEt = (EditText)findViewById(R.id.passEditTextId);
        logIn = (Button)findViewById(R.id.logInId);


        //LogIn click
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = emailEt.getText().toString();
                String passStr =  passwordEt.getText().toString();

                preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                editor = preferences.edit();

                editor.putString("emailKey", emailStr);
                editor.putString("passKey", passStr);
                editor.commit();


                if(emailStr.isEmpty() || passStr.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Fill Fields", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }


            }
        });

        preferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String firstField = preferences.getString("emailKey","");
        String secondField = preferences.getString("passKey","");

        if(firstField.isEmpty() || secondField.isEmpty()){
            Toast.makeText(LoginActivity.this, "Input email / password", Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(LoginActivity.this,firstField+" "+secondField, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }
}
