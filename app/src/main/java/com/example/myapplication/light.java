package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class light extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    final DatabaseReference D1Status = myRef.child("Member").child("D1Status");
    final DatabaseReference D2Status = myRef.child("Member").child("D2Status");
    final DatabaseReference D3Status = myRef.child("Member").child("D3Status");
    final DatabaseReference D4Status = myRef.child("Member").child("D4Status");
    final DatabaseReference D5Status = myRef.child("Member").child("D5Status");
    final DatabaseReference D6Status = myRef.child("Member").child("D6Status");
    final DatabaseReference D7Status = myRef.child("Member").child("D7Status");
    final DatabaseReference D8Status = myRef.child("Member").child("D8Status");

    static String MQTTHOST = "tcp://soldier.cloudmqtt.com:18166";
    static String USERNAME = "dtsghhrn";
    static String PASSWORD = "ycDpcGIFTmK4";

    MqttAndroidClient client;

    TextView subTextD1,subTextD2,subTextD3,subTextD4,subTextD5,subTextD6,subTextD7,subTextD8;
    DatabaseReference reff;
    String Status,pubString="esp/test",subString="esp/test";

    Button GUI,D1on,D1off,D2on,D2off,D3on,D3off,D4on,D4off,D5on,D5off,D6on,D6off,D7on,D7off,D8on,D8off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        D1on = (Button) findViewById(R.id.button);
        D1off = (Button) findViewById(R.id.button2);
        D2on = (Button) findViewById(R.id.button3);
        D2off = (Button) findViewById(R.id.button4);
        D3on = (Button) findViewById(R.id.button5);
        D3off = (Button) findViewById(R.id.button6);
        D4on = (Button) findViewById(R.id.button7);
        D4off = (Button) findViewById(R.id.button8);
        D5on = (Button) findViewById(R.id.button9);
        D5off = (Button) findViewById(R.id.button10);
        D6on = (Button) findViewById(R.id.button11);
        D6off = (Button) findViewById(R.id.button12);
        D7on = (Button) findViewById(R.id.button13);
        D7off = (Button) findViewById(R.id.button14);
        D8on = (Button) findViewById(R.id.button15);
        D8off = (Button) findViewById(R.id.button16);

        /*
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabaseReference = mDatabase.getReference();
        mDatabaseReference = mDatabase.getReference().child("Member").child("D1Status");
        mDatabaseReference.setValue("D1 On");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D2Status");
        mDatabaseReference.setValue("D2 Off");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D3Status");
        mDatabaseReference.setValue("D3 On");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D4Status");
        mDatabaseReference.setValue("D4 Off");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D5Status");
        mDatabaseReference.setValue("D5 On");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D6Status");
        mDatabaseReference.setValue("D6 Off");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D7Status");
        mDatabaseReference.setValue("D7 On");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D8Status");
        mDatabaseReference.setValue("D8 Off");
        mDatabaseReference = mDatabase.getReference().child("Member").child("D9Status");
        mDatabaseReference.setValue("D9 On");
        */

        GUI = (Button)findViewById(R.id.guibutton);

        GUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), light_GUI.class);
                startActivity(intent);
            }
        });

        subTextD1 = (TextView)findViewById(R.id.statusD1);
        subTextD2 = (TextView)findViewById(R.id.statusD2);
        subTextD3 = (TextView)findViewById(R.id.statusD3);
        subTextD4 = (TextView)findViewById(R.id.statusD4);
        subTextD5 = (TextView)findViewById(R.id.statusD5);
        subTextD6 = (TextView)findViewById(R.id.statusD6);
        subTextD7 = (TextView)findViewById(R.id.statusD7);
        subTextD8 = (TextView)findViewById(R.id.statusD8);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        MqttConnectOptions options = new MqttConnectOptions();

        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //Toast.makeText(light.this,"Connected",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //Toast.makeText(light.this,"Not Connected",Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {

                //subTextD1.setText(new String(message.getPayload()));
                Status = new String(message.getPayload());
                if(Status.equals("#relay1on")){
                    subTextD1.setText(" D1 On ");
                    //subTextD1.setBackgroundColor(Color.GREEN);
                    //D1off.setBackgroundColor(Color.WHITE);
                }
                if(Status.equals("#relay1off")){
                    subTextD1.setText("D1 Off");
                    //D1on.setBackgroundColor(Color.WHITE);
                    //D1off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay2on")){
                    subTextD2.setText("D2 On");
                    //D2on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay2off")){
                    subTextD2.setText("D2 Off");
                    //D2off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay3on")){
                    subTextD3.setText("D3 On");
                    //D3on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay3off")){
                    subTextD3.setText("D3 Off");
                    //D3off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay4on")){
                    subTextD4.setText("D4 On");
                    //D4on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay4off")){
                    subTextD4.setText("D4 Off");
                    //D4off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay5on")){
                    subTextD5.setText("D5 On");
                    //D5on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay5off")){
                    subTextD5.setText("D5 Off");
                    //D5off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay6on")){
                    subTextD6.setText("D6 On");
                    //D6on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay6off")){
                    subTextD6.setText("D6 Off");
                    //D6off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay7on")){
                    subTextD7.setText("D7 On");
                    //D7on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay7off")){
                    subTextD7.setText("D7 Off");
                    //D7off.setBackgroundColor(Color.RED);
                }
                if(Status.equals("#relay8on")){
                    subTextD8.setText("D8 On");
                    //D8on.setBackgroundColor(Color.GREEN);
                }
                if(Status.equals("#relay8off")){
                    subTextD8.setText("D8 Off");
                    //D8off.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        /*
        subText = (TextView) findViewById(R.id.status);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subText.setText("D1 On");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subText.setText("D1 Off");
            }
        });
        */
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name1=dataSnapshot.child("D1Status").getValue().toString();
                String name2=dataSnapshot.child("D2Status").getValue().toString();
                String name3=dataSnapshot.child("D3Status").getValue().toString();
                String name4=dataSnapshot.child("D4Status").getValue().toString();
                String name5=dataSnapshot.child("D5Status").getValue().toString();
                String name6=dataSnapshot.child("D6Status").getValue().toString();
                String name7=dataSnapshot.child("D7Status").getValue().toString();
                String name8=dataSnapshot.child("D8Status").getValue().toString();

                subTextD1.setText(name1);
                subTextD2.setText(name2);
                subTextD3.setText(name3);
                subTextD4.setText(name4);
                subTextD5.setText(name5);
                subTextD6.setText(name6);
                subTextD7.setText(name7);
                subTextD8.setText(name8);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*Tbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(Tbutton.isChecked()) {
                    subTextD1.setText("D1 On");
                    //Toast.makeText(MainActivity.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
                else {
                    subTextD1.setText("D1 Off");
                    //Toast.makeText(MainActivity.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }

    public void d1_on(View v){
        String topic = pubString;
        String message = "#relay1on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD1.setText("D1 On");
            D1Status.setValue("D1 On");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d1_off(View v){
        String topic = pubString;
        String message = "#relay1off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD1.setText("D1 Off");
            D1Status.setValue("D1 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d2_on(View v){
        String topic = pubString;
        String message = "#relay2on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD2.setText("D2 On");
            D2Status.setValue("D2 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d2_off(View v){
        String topic = pubString;
        String message = "#relay2off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD2.setText("D2 Off");
            D2Status.setValue("D2 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d3_on(View v){
        String topic = pubString;
        String message = "#relay3on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD3.setText("D3 On");
            D3Status.setValue("D3 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d3_off(View v){
        String topic = pubString;
        String message = "#relay3off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD3.setText("D3 Off");
            D3Status.setValue("D3 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d4_on(View v){
        String topic = pubString;
        String message = "#relay4on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD4.setText("D4 On");
            D4Status.setValue("D4 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d4_off(View v){
        String topic = pubString;
        String message = "#relay4off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD4.setText("D4 Off");
            D4Status.setValue("D4 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d5_on(View v){
        String topic = pubString;
        String message = "#relay5on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD5.setText("D5 On");
            D5Status.setValue("D5 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d5_off(View v){
        String topic = pubString;
        String message = "#relay5off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD5.setText("D5 Off");
            D5Status.setValue("D5 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d6_on(View v){
        String topic = pubString;
        String message = "#relay6on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD6.setText("D6 On");
            D6Status.setValue("D6 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d6_off(View v){
        String topic = pubString;
        String message = "#relay6off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD6.setText("D6 Off");
            D6Status.setValue("D6 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d7_on(View v){
        String topic = pubString;
        String message = "#relay7on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD7.setText("D7 On");
            D7Status.setValue("D7 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d7_off(View v){
        String topic = pubString;
        String message = "#relay7off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD7.setText("D7 Off");
            D7Status.setValue("D7 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d8_on(View v){
        String topic = pubString;
        String message = "#relay8on";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD8.setText("D8 On");
            D8Status.setValue("D8 On");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void d8_off(View v){
        String topic = pubString;
        String message = "#relay8off";
        try {
            client.publish(topic, message.getBytes(),0,false);
            subTextD8.setText("D8 Off");
            D8Status.setValue("D8 Off");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void setSubscription(){
        try{
            client.subscribe(subString,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}