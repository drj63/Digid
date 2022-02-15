package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class home extends AppCompatActivity {
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
                    Intent i = new Intent(home.this,qr_code.class);
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
    }
}