package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void onButtonClick (View v)
        {
            switch (v.getId()) {
                case R.id.bRegister: {

                    Intent i = new Intent(MainActivity.this, register.class);
                    startActivity(i);
                }
            }
        }
    }
