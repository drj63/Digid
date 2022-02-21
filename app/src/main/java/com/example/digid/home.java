package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class home extends AppCompatActivity {
    ImageView img;
    int count = 0;

    //yessir YAYAYAYAYAY
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home);

    }
    public void onButtonClick (View v)
    {
        if(v.getId()== R.id.bDigitalID)
        {
            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {
                    Intent i = new Intent(home.this, GenerateQR.class);
                    startActivity(i);
                    finish();
                }
            }, 2000);

        }
        if(v.getId()== R.id.bLocations)
        {
            Intent i=new Intent(home.this, locations.class );
            startActivity(i);

        }

        //dont worry about this code ;)
        if(v.getId()== R.id.imageView2)
        {
                count++;

                if (count == 5) {
                    img = (ImageView) findViewById(R.id.imageView2);
                    img.setImageResource(R.drawable.java);
                }
        }

    }
}