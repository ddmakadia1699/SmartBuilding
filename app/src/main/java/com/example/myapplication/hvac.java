package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class hvac extends AppCompatActivity {

    TextView hallT,b1T,b2T,b3T,kitchenT;
    Button setTemp;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hvac);

        reff = FirebaseDatabase.getInstance().getReference();


        reff.child("HVAC").child("hallTemp").setValue("27");
        reff.child("HVAC").child("b1Temp").setValue("27");
        reff.child("HVAC").child("b2Temp").setValue("27");
        reff.child("HVAC").child("b3Temp").setValue("27");
        reff.child("HVAC").child("kitchenTemp").setValue("27");


        hallT = (TextView) findViewById(R.id.gFloor);
        b1T = (TextView) findViewById(R.id.fFloor);
        b2T = (TextView) findViewById(R.id.sFloor);
        b3T = (TextView) findViewById(R.id.tFloor);
        kitchenT = (TextView) findViewById(R.id.Parking);

        setTemp = (Button) findViewById(R.id.setTemperature);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s1=dataSnapshot.child("HVAC").child("hallTemp").getValue().toString();
                String s2=dataSnapshot.child("HVAC").child("b1Temp").getValue().toString();
                String s3=dataSnapshot.child("HVAC").child("b2Temp").getValue().toString();
                String s4=dataSnapshot.child("HVAC").child("b3Temp").getValue().toString();
                String s5=dataSnapshot.child("HVAC").child("kitchenTemp").getValue().toString();

                hallT.setText(s1);
                b1T.setText(s2);
                b2T.setText(s3);
                b3T.setText(s4);
                kitchenT.setText(s5);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        setTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hvac.this, setTemp.class);
                startActivity(intent);
            }
        });
    }
}
