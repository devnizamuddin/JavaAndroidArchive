package com.example.nizamuddinshamrat.librarymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    UserInformation userInformation;
    TextView userNameTV,userIdTV,userGenderTV,userBirthDateTV,userEmailTV ;
    DataSourceUser sourceUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userNameTV = findViewById(R.id.userNameTV);
        userIdTV = findViewById(R.id.userIdTV);
        userGenderTV = findViewById(R.id.userGenderTV);
        userBirthDateTV = findViewById(R.id.userBirthDateTV);
        userEmailTV = findViewById(R.id.userEmailTV);
        sourceUser = new DataSourceUser(this);

        userInformation = (UserInformation) getIntent().getSerializableExtra("UserInfo");
        userNameTV.setText(userInformation.getUserName());
        userIdTV.setText(String.valueOf(userInformation.getUserId()));
        userGenderTV.setText(userInformation.getUserGender());
        userBirthDateTV.setText(userInformation.getUserBirthdayDate());
        userEmailTV.setText(userInformation.getUserEmail());

    }

    public void editProfile(View view) {
        Intent intent = new Intent(this,SignUpActivity.class);
        intent.putExtra("UserInfo",userInformation);
        intent.putExtra("update",true);
        startActivity(intent);
    }

    public void deleteProfile(View view) {
        boolean status =  sourceUser.deleteUser(userInformation.getUserId());

        if (status){
            Toast.makeText(UserProfileActivity.this,"Your account is deleted",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserProfileActivity.this,LoginActivity.class));
        }
        else {
            Toast.makeText(UserProfileActivity.this,"Your account is not deleted",Toast.LENGTH_SHORT).show();

        }
    }
}
