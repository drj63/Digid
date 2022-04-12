package com.example.digid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.WriterException;

import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQR extends AppCompatActivity {

    private TextView qrCodeTV;
    private ImageView qrCodeIV;
    //private TextInputEditText dataEDT;
    private Button generateQRbtn;
    private QRGEncoder qrgEncoder;
    private Bitmap bitmap;

    FirebaseAuth fAuth;
    private Button generateNewQRbtn;
    int Guest = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);
        qrCodeTV = findViewById(R.id.idTVGenerateQR);
        qrCodeIV = findViewById(R.id.idIVQRCode);
        //dataEDT = findViewById(R.id.idEdtData);
        generateQRbtn = findViewById(R.id.idGenButton);
        generateNewQRbtn = findViewById(R.id.bNewButton);
        fAuth = FirebaseAuth.getInstance();


    }

    public void onClick(View v) {
        DatabaseReference reff;
        FirebaseUser currentFirebaseUser = fAuth.getCurrentUser();

        if (v.getId() == R.id.bNewButton) {

            reff = FirebaseDatabase.getInstance().getReference().child("User").child(currentFirebaseUser.getUid());
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Vcode;

                    Random r = new Random(System.currentTimeMillis());
                    int iVCODE = 10000 + r.nextInt(20000);
                    Vcode = String.valueOf(iVCODE);

                    dataSnapshot.child("vcode").getRef().setValue(Vcode);


                    Toast.makeText(GenerateQR.this, "New ID Generated!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }

        if (v.getId() == R.id.idGenButton) {
            reff = FirebaseDatabase.getInstance().getReference("User").child(currentFirebaseUser.getUid());
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Vcode = dataSnapshot.child("vcode").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    if(Guest == 1)
                    {
                        Vcode = email.concat("_").concat(Vcode).concat("_guest"); //drj63@scarletmail.rutgers.edu_10824_guest
                    }
                    else
                    {
                        Vcode = email.concat("_").concat(Vcode); //drj63@scarletmail.rutgers.edu_10824
                    }
                    Guest = 0;


                    if (Vcode.isEmpty()) {
                        Toast.makeText(GenerateQR.this, "Enter email in scarletmail format to get digital RUID", Toast.LENGTH_SHORT).show();
                    } else {
                        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                        Display display = manager.getDefaultDisplay();
                        Point point = new Point();
                        display.getSize(point);
                        int width = point.x;
                        int height = point.y;
                        int dimen = width < height ? width : height;
                        dimen = dimen * 3 / 4;

                        qrgEncoder = new QRGEncoder(Vcode, null, QRGContents.Type.TEXT, dimen);
                        try {
                            bitmap = qrgEncoder.encodeAsBitmap();
                            qrCodeTV.setVisibility(View.GONE);
                            qrCodeIV.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
        }

        if (v.getId() == R.id.bNewGuestButton) {

                    Guest = 1;
                    Toast.makeText(GenerateQR.this, "Guest ID Generated!", Toast.LENGTH_SHORT).show();
        }
    }
}