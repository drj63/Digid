package com.example.digid;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

    }

    public void onButtonClick(View v)
    {
        mEmail = findViewById(R.id.inputEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mLoginBtn = findViewById(R.id.bRegisterAttempt);

        switch (v.getId())
        {
            case R.id.bRegisterAttempt:
            {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required");
                    return;
                }
                //add @ check
                //add scarletmail check

                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required");
                    return;
                }
                if(password.length() < 6)
                {
                    mPassword.setError("Password must be greater than 5 characters!");
                    return;
                }
                else
                {
                    Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                    Bundle bundle=new Bundle();
                    Intent intent = new Intent(getApplicationContext(),home.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                };

            }
        }


    }

}

