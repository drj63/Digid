package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//unused right now
public class qr_code extends AppCompatActivity {
    private Button generateQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        generateQR = findViewById(R.id.generate);
        generateQR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(qr_code.this,GenerateQR.class);
                startActivity(i);
            }
        });
    }
}