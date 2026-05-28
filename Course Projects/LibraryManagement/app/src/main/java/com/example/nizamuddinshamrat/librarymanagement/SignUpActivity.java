package com.example.nizamuddinshamrat.librarymanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameET,birthdayEt,emailET,passwordET,cofirmPasswordET;
    private RadioGroup genderRG;
    private Button signUpBtn;
    private int year,month,day;
    private Calendar calendar;
    private UserInformation userInformation;
    private String gender;
    private DataSourceUser sourceUser;
    private boolean update;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameET = findViewById(R.id.nameEt);
        genderRG = findViewById(R.id.genderRG);
        birthdayEt = findViewById(R.id.birthdayDateET);
        emailET = findViewById(R.id.emailEtS);
        passwordET = findViewById(R.id.passwordEtS);
        signUpBtn = findViewById(R.id.signUpBtn);
        cofirmPasswordET=findViewById(R.id.confirmPasswordEtS);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        sourceUser = new DataSourceUser(this);

        update = getIntent().getBooleanExtra("update",false);
        UserInformation userInformation = (UserInformation) getIntent().getSerializableExtra("UserInfo");


        if (update == true){
            userId = userInformation.getUserId();
            nameET.setText(userInformation.getUserName());

            birthdayEt.setText(userInformation.getUserBirthdayDate());
            emailET.setText(userInformation.getUserEmail());
            passwordET.setText(userInformation.getUserPassword());
            cofirmPasswordET.setVisibility(View.INVISIBLE);
            signUpBtn.setText("Update");
        }

        //get Gender
        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton Id = findViewById(checkedId);
                gender = Id.getText().toString();

            }
        });
    }

    //method for get brithday Date

    public void birthdayDate(View view) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,datelistener,year,month,day);
        datePickerDialog.show();

    }
    DatePickerDialog.OnDateSetListener datelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd, MMM, yyyy");
            calendar.set(year,month,dayOfMonth);
            String finalDate = sdf.format(calendar.getTime());
            birthdayEt.setText(finalDate);


        }
    };


    public void signUp(View view) {
        if (update == true){
            String userName = nameET.getText().toString();
            String userGender = gender;
            String userBirthday = birthdayEt.getText().toString();
            String userEmail = emailET.getText().toString();
            String userPassword = passwordET.getText().toString();
            UserInformation userInformation = new UserInformation(userId,userName, userGender, userBirthday, userEmail, userPassword);
            boolean status =sourceUser.updateUser(userInformation);
            if (status){
                Toast.makeText(this,"Data Updated",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"Data not Updated",Toast.LENGTH_SHORT).show();

            }
        }
        else { String userName = nameET.getText().toString();
            String userGender = gender;
            String userBirthday = birthdayEt.getText().toString();
            String userEmail = emailET.getText().toString();
            String userPassword = passwordET.getText().toString();
            String confirmPassword = cofirmPasswordET.getText().toString();

            if (confirmPassword.equals(userPassword)) {

                userInformation = new UserInformation(userName, userGender, userBirthday, userEmail, userPassword);
                boolean status = sourceUser.incertUserData(userInformation);

                if (status) {
                    Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                } else {
                    Toast.makeText(this, "Your sign up is not successful", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(this, "Passwords did not match", Toast.LENGTH_SHORT).show();
            }}


    }
}
