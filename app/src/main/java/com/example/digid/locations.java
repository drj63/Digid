package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class locations extends AppCompatActivity {

    private Button busch;
    private Button livi;
    private Button cook;
    private Button ave;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        ave = (Button) findViewById(R.id.c1);
        ave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCollegeAve();
            }
        });
        busch = (Button) findViewById(R.id.c2);
        livi = (Button) findViewById(R.id.c3);
        cook = (Button) findViewById(R.id.c4);


    }


        public void openCollegeAve(){
            Intent intent = new Intent(this, collegeAveCampus.class);
            startActivity(intent);

        }



    }

//    public void onButtonClick(View v) {
//        if(v.getId()== R.id.c1){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent i = new Intent(locations.this, collegeAveCampus.class);
//                    startActivity(i);
//                    finish();
//                }
//            }, 2000);
//        }
//        if(v.getId()== R.id.c2)
//        {
//            Intent i=new Intent(locations.this, buschCampus.class );
//            startActivity(i);
//
//        }
//        if(v.getId()== R.id.c3)
//        {
//            Intent i=new Intent(locations.this, liviCampus.class );
//            startActivity(i);
//
//        }
//        if(v.getId()== R.id.c4)
//        {
//            Intent i=new Intent(locations.this, cookDougCampus.class );
//            startActivity(i);
//
//        }

    //}
