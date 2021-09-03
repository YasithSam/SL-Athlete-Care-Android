package com.example.slathletecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.slathletecare.OTPActivity;
import com.example.slathletecare.R;
import com.example.slathletecare.model.accountRegister;

public class SignUpActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText inputUsername;
    private EditText inputPhone;
    private EditText inputPassword;
    private EditText inputConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputUsername = findViewById(R.id.username);
        inputPhone = findViewById(R.id.phone);
        inputPassword=findViewById(R.id.password);
        inputConfirmPassword=findViewById(R.id.confirmPassword);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = inputUsername.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String confirmPassword= inputConfirmPassword.getText().toString().trim();

                if (validateUser(name,password,phone)) {
                    if(!(password.equals(confirmPassword))){
                        Toast.makeText(getApplicationContext(),"Passwords are not matching",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        accountRegister user=new accountRegister(name,phone,password);
                        Intent intent = new Intent(SignUpActivity.this, OTPActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        finish();
                    }


                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details correctly!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });





    }
    private void registerUser(final String name,final String phone,final String password){



    }

    private boolean validateUserName(String name){

        if (name.isEmpty()) {
            inputUsername.setError("Field cannot be empty");
            return false;
        } else if (name.length() >= 15) {
            inputUsername.setError("Username too long");
            return false;
        } else if (name.contains(" ")) {
            inputUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            inputUsername.setError(null);
            return true;
        }
    }
    private boolean validatePhone(String phone){
        if (phone.isEmpty()) {
            inputPhone.setError("Field cannot be empty");
            return false;
        }else if(phone.length()>10 | phone.length()<10){
            inputPhone.setError("Invalid number");
            return false;
        }
        else {
            inputPhone.setError(null);
            return true;
        }

    }
    private boolean validatePassword(String password){

        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$";

        if (password.isEmpty()) {
            inputPassword.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            inputPassword.setError("Password is too weak");
            return false;
        } else {
            inputPassword.setError(null);
            return true;
        }

    }
    private boolean validateUser(String name, String password, String phone){
        if(validateUserName(name)&&validatePassword(password)&&validatePhone(phone)){
            return true;
        }
        else{
            return false;
        }

    }
}