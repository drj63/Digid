package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class locations extends AppCompatActivity {

    private Button refresh;
    FirebaseAuth fAuth;
    private ImageView dh1;
    private ImageView dh2;
    private TextView c1;
    private TextView c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
    }
}