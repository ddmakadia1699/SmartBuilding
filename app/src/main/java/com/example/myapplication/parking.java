package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class parking extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference reff;

    ImageView i1,i2,i3,i4,i5,i6;

    final DatabaseReference P1 = myRef.child("Parking").child("P1");
    final DatabaseReference P2 = myRef.child("Parking").child("P2");
    final DatabaseReference P3 = myRef.child("Parking").child("P3");
    final DatabaseReference P4 = myRef.child("Parking").child("P4");
    final DatabaseReference P5 = myRef.child("Parking").child("P5");
    final DatabaseReference P6 = myRef.child("Parking").child("P6");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);

        /*
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mDatabase.getReference();
        mDatabaseReference = mDatabase.getReference().child("Parking").child("P1");
        mDatabaseReference.setValue("1");
        mDatabaseReference = mDatabase.getReference().child("Parking").child("P2");
        mDatabaseReference.setValue("0");
        mDatabaseReference = mDatabase.getReference().child("Parking").child("P3");
        mDatabaseReference.setValue("1");
        mDatabaseReference = mDatabase.getReference().child("Parking").child("P4");
        mDatabaseReference.setValue("0");
        mDatabaseReference = mDatabase.getReference().child("Parking").child("P5");
        mDatabaseReference.setValue("1");
        mDatabaseReference = mDatabase.getReference().child("Parking").child("P6");
        mDatabaseReference.setValue("0");
         */

        i1 = (ImageView) findViewById(R.id.image1);
        i2 = (ImageView) findViewById(R.id.image2);
        i3 = (ImageView) findViewById(R.id.image3);
        i4 = (ImageView) findViewById(R.id.image4);
        i5 = (ImageView) findViewById(R.id.image5);
        i6 = (ImageView) findViewById(R.id.image6);

        reff= FirebaseDatabase.getInstance().getReference().child("Parking");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String P1=dataSnapshot.child("P1").getValue().toString();
                String P2=dataSnapshot.child("P2").getValue().toString();
                String P3=dataSnapshot.child("P3").getValue().toString();
                String P4=dataSnapshot.child("P4").getValue().toString();
                String P5=dataSnapshot.child("P5").getValue().toString();
                String P6=dataSnapshot.child("P6").getValue().toString();

                if(P1.equals("1")){
                    i1.setVisibility(View.VISIBLE);
                }
                else if(P1.equals("0")){
                    i1.setVisibility(View.INVISIBLE);
                    //Toast.makeText(parking.this, "Slot 1 is full ...", Toast.LENGTH_LONG).show();
                }

                if(P2.equals("1")){
                    i2.setVisibility(View.VISIBLE);
                }
                else if(P2.equals("0")){
                    i2.setVisibility(View.INVISIBLE);
                    //Toast.makeText(parking.this, "Slot 2 is full ...", Toast.LENGTH_LONG).show();
                }

                if(P3.equals("1")){
                    i3.setVisibility(View.VISIBLE);
                }
                else if(P3.equals("0")){
                    i3.setVisibility(View.INVISIBLE);
                    //Toast.makeText(parking.this, "Slot 3 is full ...", Toast.LENGTH_LONG).show();
                }

                if(P4.equals("1")){
                    i4.setVisibility(View.VISIBLE);
                }
                else if(P4.equals("0")){
                    i4.setVisibility(View.INVISIBLE);
                    //Toast.makeText(parking.this, "Slot 4 is full ...", Toast.LENGTH_LONG).show();
                }

                if(P5.equals("1")){
                    i5.setVisibility(View.VISIBLE);
                }
                else if(P5.equals("0")){
                    i5.setVisibility(View.INVISIBLE);
                    //Toast.makeText(parking.this, "Slot 5 is full ...", Toast.LENGTH_LONG).show();
                }

                if(P6.equals("1")){
                    i6.setVisibility(View.VISIBLE);
                }
                else if(P6.equals("0")){
                    i6.setVisibility(View.INVISIBLE);
                    //Toast.makeText(parking.this, "Slot 6 is full ...", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        }
}
