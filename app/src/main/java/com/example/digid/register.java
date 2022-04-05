package com.example.digid;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class register extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseAuth fAuth;
    EditText mEmail, mPassword;
    Button mLoginBtn;
    String selectedstype;

    ActionCodeSettings actionCodeSettings =
            ActionCodeSettings.newBuilder()
                    // URL you want to redirect back to. The domain (www.example.com) for this
                    // URL must be whitelisted in the Firebase Console.
                    .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                    // This must be true
                    .setHandleCodeInApp(true)
                    .setIOSBundleId("com.example.ios")
                    .setAndroidPackageName(
                            "com.example.android",
                            true, /* installIfNotAvailable */
                            "12"    /* minimumVersion */)
                    .build();
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Spinner spinner_stype=findViewById(R.id.spinner_stype);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.stype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_stype.setAdapter(adapter);
        spinner_stype.setOnItemSelectedListener(this);

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
                if(!(email.contains("@scarletmail.rutgers.edu")))
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

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                User_Parameters userParameters;
                    DatabaseReference reff;
                    reff = FirebaseDatabase.getInstance().getReference().child("User");
                    userParameters = new User_Parameters();
                    userParameters.setEmail(email);

                    String type = "unknown";

                    if (selectedstype.equals("Graduate Student"))
                    {
                        type = "graduate";
                    }
                    if (selectedstype.equals("Undergraduate Student"))
                    {
                        type = "undergraduate";
                    }
                    if (selectedstype.equals("Professor"))
                    {
                        type = "professor";
                    }

                    userParameters.setType(type);

                    userParameters.setVcode(Vcode);

                    reff.child(uid).setValue(userParameters);

                fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(register.this, "Registered successfully! Check Email For Verification Code", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else {
                Toast.makeText(register.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
            /* protected void sendEmail() {

                DatabaseReference reff;
                FirebaseUser currentFirebaseUser = fAuth.getCurrentUser();
                reff = FirebaseDatabase.getInstance().getReference("User").child(currentFirebaseUser.getUid());

                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Vcode = dataSnapshot.child("vcode").getValue().toString();
                        Log.i("Send email", Vcode);
                        String[] TO = {email};
                        String[] CC = {"xyz@spambog.com"};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");


                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_CC, CC);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Verification code for Digid Is Shown Below");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, Vcode);

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending", "");

                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(register.this,
                                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });


            }

             */
    });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedstype = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

