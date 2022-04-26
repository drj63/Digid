package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class buschCampus extends AppCompatActivity {
    private Button capSC;
    private Button capEB;
    private Button capBDH;
    private Button capWGym;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busch_campus);
        capSC = (Button) findViewById(R.id.sc);
        capSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentCenter();
            }
        });

        capEB = (Button) findViewById(R.id.eb);
        capEB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEngBuilding();
            }
        });
        capBDH = (Button) findViewById(R.id.bdh);
        capBDH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBDH();
            }
        });
        capWGym = (Button) findViewById(R.id.gym);
        capWGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWGym();
            }
        });
    }
    public void openStudentCenter(){
        Intent intent = new Intent(this, buschsc.class);
        startActivity(intent);

    }
    public void openEngBuilding(){
        Intent intent = new Intent(this, engBuilding.class);
        startActivity(intent);
    }
    public void openBDH(){
        Intent intent = new Intent(this, bdh.class);
        startActivity(intent);
    }
    public void openWGym(){
        Intent intent = new Intent(this, Werblin.class);
        startActivity(intent);
    }
}
