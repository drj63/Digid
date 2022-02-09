package com.example.digid;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class register extends AppCompatActivity {

    FirebaseAuth fAuth;
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
        mLoginBtn = findViewById(R.id.bLoginAttempt);
        fAuth = FirebaseAuth.getInstance();


        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        switch (v.getId())
        {
            case R.id.bLoginAttempt:
            {
                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required");
                    return;
                }
                if(!(email.contains("@")))
                {
                    mEmail.setError("Please Enter A Valid Email!");
                    return;
                }
                if(!(email.contains("rutgers.edu")))
                {
                    mEmail.setError("Sorry But This App Is Only For Those Attending Rutgers!");
                    return;
                }
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

            }
        }
        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if(task.isSuccessful())
            {
                Random r = new Random( System.currentTimeMillis() );
                int iVCODE = 10000 + r.nextInt(20000);
                String Vcode = String.valueOf(iVCODE);

                Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                User_Parameters userParameters;
                    DatabaseReference reff;
                    reff = FirebaseDatabase.getInstance().getReference().child("User");
                    userParameters = new User_Parameters();
                    userParameters.setEmail(email);
                    userParameters.setPassword(password);
                    userParameters.setVcode(Vcode);
                    reff.child(uid).setValue(userParameters);

                    startActivity(new Intent(getApplicationContext(), verify_email.class));

            }
            else {
                Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
            protected void sendEmail() {
                Log.i("Send email", "");

                String[] TO = {email};
                String[] CC = {"xyz@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");


                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                    Log.i("Finished sending", "");

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(register.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
    });

    }

}

