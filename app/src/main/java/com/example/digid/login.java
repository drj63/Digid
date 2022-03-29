package com.example.digid;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mEmail = findViewById(R.id.inputEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mLoginBtn = findViewById(R.id.bLoginAttempt);
        fAuth = FirebaseAuth.getInstance();
    }

    public void onButtonClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bLoginAttempt:
            {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

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
                if(!(email.contains("@scarletmail.rutgers.edu")))
                {
                    mEmail.setError("Please Enter A scarletmail.rutgers.edu email!");
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

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User_Parameters userParameters;
                            DatabaseReference reff;
                            userParameters = new User_Parameters();
                            if (userParameters.getType().equals("undergraduate"))
                            {
                                if(fAuth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(login.this, "Undergraduate Student Signed In", Toast.LENGTH_SHORT).show();
                                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    reff = FirebaseDatabase.getInstance().getReference("User").child(uid);

                                    reff.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("uid", uid);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            System.out.println("The read failed: " + databaseError.getCode());
                                        }
                                    });
                                    startActivity(new Intent(getApplicationContext(), home.class));
                                }
                            }
                            else
                            {
                                Toast.makeText(login.this, "Please Verify Your Email!", Toast.LENGTH_SHORT).show();

                            }

                        }

                        else {
                            Toast.makeText(login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }


    }

}