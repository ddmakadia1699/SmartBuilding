package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tank extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference reff;

    ImageView imageV1;

    String level20 = "@drawable/ic_battery_20_black_24dp";
    String level30 = "@drawable/ic_battery_30_black_24dp";
    String level50 = "@drawable/ic_battery_50_black_24dp";
    String level60 = "@drawable/ic_battery_60_black_24dp";
    String level80 = "@drawable/ic_battery_80_black_24dp";
    String level90 = "@drawable/ic_battery_90_black_24dp";
    String level100 = "@drawable/ic_battery_full_black_24dp";
    String levelC50 = "@drawable/ic_battery_charging_50_black_24dp";
    String levelE = "@drawable/ic_battery_alert_black_24dp";

    final DatabaseReference Status = myRef.child("Tank Status").child("Status");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank);

        /*
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mDatabase.getReference();
        mDatabaseReference = mDatabase.getReference().child("Tank Status").child("Status");
        mDatabaseReference.setValue("20");
         */

        imageV1 = (ImageView) findViewById(R.id.imageView2);

        reff= FirebaseDatabase.getInstance().getReference().child("Tank Status");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String P1=dataSnapshot.child("Status").getValue().toString();

                if(P1.equals("20")){
                    int imageResource = getResources().getIdentifier(level20, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("30")){
                    int imageResource = getResources().getIdentifier(level30, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("50")){
                    int imageResource = getResources().getIdentifier(level50, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("60")){
                    int imageResource = getResources().getIdentifier(level60, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("80")){
                    int imageResource = getResources().getIdentifier(level80, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("90")){
                    int imageResource = getResources().getIdentifier(level90, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("100")){
                    int imageResource = getResources().getIdentifier(level100, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("C")){
                    int imageResource = getResources().getIdentifier(levelC50, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }

                if(P1.equals("E")){
                    int imageResource = getResources().getIdentifier(levelE, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    imageV1.setImageDrawable(res);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
