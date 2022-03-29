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
    private Button capGym;

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
    }
    public void openStudentCenter(){
        Intent intent = new Intent(this, CollegeAveScCapacity.class);
        startActivity(intent);

    }
}