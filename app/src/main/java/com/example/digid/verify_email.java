package com.example.digid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class verify_email extends AppCompatActivity {

    EditText verificationCode;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifyemail);

        verificationCode = findViewById(R.id.eVerificationNumber);
        fAuth = FirebaseAuth.getInstance();
    }

    public void onButtonClick(View view) {

        String enteredvCode = verificationCode.getText().toString().trim();

        DatabaseReference reff;
        FirebaseUser currentFirebaseUser = fAuth.getCurrentUser();
        reff = FirebaseDatabase.getInstance().getReference("User").child(currentFirebaseUser.getUid());

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Vcode = dataSnapshot.child("vcode").getValue().toString();

                if (Vcode.equals(enteredvCode))
                {
                    Toast.makeText(verify_email.this, "User Verified!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), home.class));
                }
                else
                {
                    Toast.makeText(verify_email.this, "Incorrect Code, Please Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }
}