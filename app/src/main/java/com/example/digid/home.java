package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {
//yessir YAYAYAYAYAY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
    public void onButtonClick (View v)
    {
        if(v.getId()== R.id.bDigitalID)
        {
            Intent i=new Intent(home.this, qr_code.class );
            startActivity(i);
        }
        if(v.getId()== R.id.bLocations)
        {
            Intent i=new Intent(home.this, locations.class );
            startActivity(i);

        }
    }
}