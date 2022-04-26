package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class collegeAveCampus extends AppCompatActivity {
    private Button capSC;
    private Button capAB;
    private Button capBrow;
    private Button capCAGym;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_ave_campus);

        capSC = (Button) findViewById(R.id.sc);
        capSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentCenter();
            }
        });

        capAB = (Button) findViewById(R.id.ab);
        capAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAcademicBuilding();
            }
        });
        capBrow = (Button) findViewById(R.id.brower);
        capBrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrower();
            }
        });
        capCAGym = (Button) findViewById(R.id.gym);
        capCAGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCAGym();
            }
        });
    }
    public void openStudentCenter(){
        Intent intent = new Intent(this, CollegeAveScCapacity.class);
        startActivity(intent);

    }
    public void openAcademicBuilding(){
        Intent intent = new Intent(this, academicbuilding.class);
        startActivity(intent);
    }
    public void openBrower(){
        Intent intent = new Intent(this, brower.class);
        startActivity(intent);
    }
    public void openCAGym(){
        Intent intent = new Intent(this, CAGym.class);
        startActivity(intent);
    }
}