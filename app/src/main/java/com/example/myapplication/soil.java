package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class soil extends AppCompatActivity {

    TextView blockA,blockB,blockC,blockD,sActionA,sActionB,sActionC,sActionD;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil);

        reff = FirebaseDatabase.getInstance().getReference();

        /*
        reff.child("Soil").child("Block_A").setValue("89");
        reff.child("Soil").child("Block_B").setValue("90");
        reff.child("Soil").child("Block_C").setValue("50");
        reff.child("Soil").child("Block_D").setValue("10");
        */

        blockA = (TextView) findViewById(R.id.gFloor);
        blockB = (TextView) findViewById(R.id.fFloor);
        blockC = (TextView) findViewById(R.id.sFloor);
        blockD = (TextView) findViewById(R.id.tFloor);

        sActionA = (TextView) findViewById(R.id.a);
        sActionB = (TextView) findViewById(R.id.b);
        sActionC = (TextView) findViewById(R.id.c);
        sActionD = (TextView) findViewById(R.id.d);

        sActionA.setVisibility(View.INVISIBLE);
        sActionB.setVisibility(View.INVISIBLE);
        sActionC.setVisibility(View.INVISIBLE);
        sActionD.setVisibility(View.INVISIBLE);


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s1=dataSnapshot.child("Soil").child("Block_A").getValue().toString();
                String s2=dataSnapshot.child("Soil").child("Block_B").getValue().toString();
                String s3=dataSnapshot.child("Soil").child("Block_C").getValue().toString();
                String s4=dataSnapshot.child("Soil").child("Block_D").getValue().toString();

                blockA.setText(s1);
                blockB.setText(s2);
                blockC.setText(s3);
                blockD.setText(s4);

                int d1 = Integer.parseInt(s1);
                int d2 = Integer.parseInt(s2);
                int d3 = Integer.parseInt(s3);
                int d4 = Integer.parseInt(s4);


                if(d1<40){
                    sActionA.setText("A");
                    sActionA.setVisibility(View.VISIBLE);
                }
                if(d2<40){
                    sActionB.setText("B");
                    sActionB.setVisibility(View.VISIBLE);
                }
                if(d3<40){
                    sActionC.setText("C");
                    sActionC.setVisibility(View.VISIBLE);
                }
                if(d4<40){
                    sActionD.setText("D");
                    sActionD.setVisibility(View.VISIBLE);
                }

                if(d1>40){
                    sActionA.setVisibility(View.INVISIBLE);
                }
                if(d2>40){
                    sActionB.setVisibility(View.INVISIBLE);
                }
                if(d3>40){
                    sActionC.setVisibility(View.INVISIBLE);
                }
                if(d4>40){
                    sActionD.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
