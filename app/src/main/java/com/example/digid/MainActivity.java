package com.example.digid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/* import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder; */

public class MainActivity extends AppCompatActivity {
    ImageView img2;
    int count2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

        public void onButtonClick (View v)
        {
            if(v.getId()== R.id.bLogin)
            {
                Intent i=new Intent(MainActivity.this, login.class );
                startActivity(i);
            }
            if(v.getId()== R.id.bRegister)
            {
                Intent i=new Intent(MainActivity.this, register.class );
                startActivity(i);

            }
            if(v.getId()== R.id.imageView6)
            {
                count2++;

                if (count2 == 5) {
                    img2 = (ImageView) findViewById(R.id.imageView6);
                    img2.setImageResource(R.drawable.java);
                }
            }
        }
    }
