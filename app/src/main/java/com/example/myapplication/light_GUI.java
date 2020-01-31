package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

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

public class light_GUI extends AppCompatActivity {


    static String MQTTHOST = "tcp://soldier.cloudmqtt.com:18166";
    //static String MQTTHOST = "tcp://broker.hivemq.com:1883";
    static String USERNAME = "dtsghhrn";
    static String PASSWORD = "ycDpcGIFTmK4";

    String Status,pubString="esp/test",subString="esp/test";

    MqttAndroidClient client;
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

    DatabaseReference reff;
    ToggleButton T1button,T2button,T3button,T4button,T5button,T6button,T7button,T8button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light__gui);

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
                    //Toast.makeText(light_GUI.this,"Connected",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    //Toast.makeText(light_GUI.this,"Not Connected",Toast.LENGTH_LONG).show();
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
                    T1button.setChecked(true);
                }
                if(Status.equals("#relay1off")){
                    T1button.setChecked(false);
                }
                if(Status.equals("#relay2on")){
                    T2button.setChecked(true);
                }
                if(Status.equals("#relay2off")){
                    T2button.setChecked(false);
                }
                if(Status.equals("#relay3on")){
                    T3button.setChecked(true);
                }
                if(Status.equals("#relay3off")){
                    T3button.setChecked(false);
                }
                if(Status.equals("#relay4on")){
                    T4button.setChecked(true);
                }
                if(Status.equals("#relay4off")){
                    T4button.setChecked(false);
                }
                if(Status.equals("#relay5on")){
                    T5button.setChecked(true);
                }
                if(Status.equals("#relay5off")){
                    T5button.setChecked(false);
                }
                if(Status.equals("#relay6on")){
                    T6button.setChecked(true);
                }
                if(Status.equals("#relay6off")){
                    T6button.setChecked(false);
                }
                if(Status.equals("#relay7on")){
                    T7button.setChecked(true);
                }
                if(Status.equals("#relay7off")){
                    T7button.setChecked(false);
                }
                if(Status.equals("#relay8on")){
                    T8button.setChecked(true);
                }
                if(Status.equals("#relay8off")){
                    T8button.setChecked(false);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        T1button = (ToggleButton)findViewById(R.id.toggleButton2);
        T2button = (ToggleButton)findViewById(R.id.toggleButton3);
        T3button = (ToggleButton)findViewById(R.id.toggleButton4);
        T4button = (ToggleButton)findViewById(R.id.toggleButton5);
        T5button = (ToggleButton)findViewById(R.id.toggleButton6);
        T6button = (ToggleButton)findViewById(R.id.toggleButton7);
        T7button = (ToggleButton)findViewById(R.id.toggleButton8);
        T8button = (ToggleButton)findViewById(R.id.toggleButton9);

        T1button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T1button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay1on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D1Status.setValue("D1 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay1off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D1Status.setValue("D1 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T2button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T2button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay2on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D2Status.setValue("D2 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay2off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D2Status.setValue("D2 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T3button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T3button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay3on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D3Status.setValue("D3 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay3off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D3Status.setValue("D3 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T4button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T4button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay4on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D4Status.setValue("D4 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay4off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D4Status.setValue("D4 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T5button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T5button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay5on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D5Status.setValue("D5 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay5off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D5Status.setValue("D5 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T6button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T6button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay6on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D6Status.setValue("D6 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay6off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D6Status.setValue("D6 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T7button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T7button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay7on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D7Status.setValue("D7 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay7off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D7Status.setValue("D7 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

        T8button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(T8button.isChecked())
                {
                    String topic = pubString;
                    String message = "#relay8on";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D8Status.setValue("D8 On");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is On", Toast.LENGTH_LONG).show();
                }
                else {
                    String topic = pubString;
                    String message = "#relay8off";
                    try {
                        client.publish(topic, message.getBytes(),0,false);
                        D8Status.setValue("D8 Off");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(GUI.this, "Toggle button is Off", Toast.LENGTH_LONG).show();
                }
            }
        });

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

                if(name1.equals("D1 On")){
                    T1button.setChecked(true);
                    //Toast.makeText(GUI.this, "D1 On", Toast.LENGTH_LONG).show();
                }
                if (name1.equals("D1 Off")){
                    T1button.setChecked(false);
                    //Toast.makeText(GUI.this, "D1 Off", Toast.LENGTH_LONG).show();
                }

                if (name2.equals("D2 On")){
                    T2button.setChecked(true);
                    //Toast.makeText(GUI.this, "D2 On", Toast.LENGTH_LONG).show();
                }
                if (name2.equals("D2 Off")){
                    T2button.setChecked(false);
                    //Toast.makeText(GUI.this, "D2 Off", Toast.LENGTH_LONG).show();
                }

                if(name3.equals("D3 On")){
                    T3button.setChecked(true);
                    //Toast.makeText(GUI.this, "D3 On", Toast.LENGTH_LONG).show();
                }
                if (name3.equals("D3 Off")){
                    T3button.setChecked(false);
                    //Toast.makeText(GUI.this, "D3 Off", Toast.LENGTH_LONG).show();
                }

                if (name4.equals("D4 On")){
                    T4button.setChecked(true);
                    //Toast.makeText(GUI.this, "D4 On", Toast.LENGTH_LONG).show();
                }
                if (name4.equals("D4 Off")){
                    T4button.setChecked(false);
                    //Toast.makeText(GUI.this, "D4 Off", Toast.LENGTH_LONG).show();
                }
                if(name5.equals("D5 On")){
                    T5button.setChecked(true);
                    //Toast.makeText(GUI.this, "D5 On", Toast.LENGTH_LONG).show();
                }
                if (name5.equals("D5 Off")){
                    T5button.setChecked(false);
                    //Toast.makeText(GUI.this, "D5 Off", Toast.LENGTH_LONG).show();
                }

                if (name6.equals("D6 On")){
                    T6button.setChecked(true);
                    //Toast.makeText(GUI.this, "D6 On", Toast.LENGTH_LONG).show();
                }
                if (name6.equals("D6 Off")){
                    T6button.setChecked(false);
                    //Toast.makeText(GUI.this, "D6 Off", Toast.LENGTH_LONG).show();
                }
                if(name7.equals("D7 On")){
                    T7button.setChecked(true);
                    //Toast.makeText(GUI.this, "D7 On", Toast.LENGTH_LONG).show();
                }
                if (name7.equals("D7 Off")){
                    T7button.setChecked(false);
                    //Toast.makeText(GUI.this, "D7 Off", Toast.LENGTH_LONG).show();
                }

                if (name8.equals("D8 On")){
                    T8button.setChecked(true);
                    //Toast.makeText(GUI.this, "D8 On", Toast.LENGTH_LONG).show();
                }
                if (name8.equals("D8 Off")){
                    T8button.setChecked(false);
                    //Toast.makeText(GUI.this, "D8 Off", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setSubscription(){
        try{
            client.subscribe(subString,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}