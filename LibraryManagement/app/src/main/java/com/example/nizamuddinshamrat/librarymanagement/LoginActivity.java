package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEt,passwordET;
    private Button loginBtn;
    private String adminEamil = "admin";
    private String adminPassword = "admin";
    private DataSourceUser sourceUser;
    private UserInformation userInformation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.emailEt);
        passwordET = findViewById(R.id.passwordEt);
        loginBtn = findViewById(R.id.loginBtn);
        sourceUser = new DataSourceUser(this);

    }

    public void logIn(View view) {

        String email = emailEt.getText().toString();
        String password = passwordET.getText().toString();
        String pas = sourceUser.getPasswordByEmail(email);
        if (email.equals(adminEamil) && password.equals(adminPassword)){
            startActivity(new Intent(this,AdminActivity.class));
            Toast.makeText(LoginActivity.this,"You are logged in as Admin",Toast.LENGTH_SHORT).show();
        }
        else if (password.equals(pas)){
            Intent intent = new Intent(LoginActivity.this,ListActivity.class);
             userInformation = sourceUser.getUserByEmail(email);
            intent.putExtra("UserInformation", userInformation);
            startActivity(intent);
        }
        else {
            Toast.makeText(LoginActivity.this,"Not Valid",Toast.LENGTH_SHORT).show();
        }
        emailEt.setText("");
        passwordET.setText("");

    }

    public void signUp(View view) {

        startActivity(new Intent(this,SignUpActivity.class));

    }


}
