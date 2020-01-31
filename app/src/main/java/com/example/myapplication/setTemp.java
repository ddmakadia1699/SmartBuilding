package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class setTemp extends AppCompatActivity {

    static String MQTTHOST = "tcp://soldier.cloudmqtt.com:18166";
    static String USERNAME = "dtsghhrn";
    static String PASSWORD = "ycDpcGIFTmK4";

    MqttAndroidClient client;

    String pubString="esp/test",subString="esp/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_temp);

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
    }

    public void gtp(View v){
        String topic = pubString;
        String message = "#gtp";
        try {
            Toast.makeText(setTemp.this,"Ground Floor Temp One Plus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void gtm(View v){
        String topic = pubString;
        String message = "#gtm";
        try {
            Toast.makeText(setTemp.this,"Ground Floor Temp One Minus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void ffp(View v){
        String topic = pubString;
        String message = "#ffp";
        try {
            Toast.makeText(setTemp.this,"First Floor Temp One Plus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void ffm(View v){
        String topic = pubString;
        String message = "#ffm";
        try {
            Toast.makeText(setTemp.this,"First Floor Temp One Minus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void sfp(View v){
        String topic = pubString;
        String message = "#sfp";
        try {
            Toast.makeText(setTemp.this,"Second Floor Temp One Plus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void sfm(View v){
        String topic = pubString;
        String message = "#sfm";
        try {
            Toast.makeText(setTemp.this,"Second Floor Temp One Minus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void tfp(View v){
        String topic = pubString;
        String message = "#tfp";
        try {
            Toast.makeText(setTemp.this,"Third Floor Temp One Plus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void tfm(View v){
        String topic = pubString;
        String message = "#tfm";
        try {
            Toast.makeText(setTemp.this,"Third Floor Temp One Minus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pp(View v){
        String topic = pubString;
        String message = "#pp";
        try {
            Toast.makeText(setTemp.this,"Parking Temp One Plus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void pm(View v){
        String topic = pubString;
        String message = "#pm";
        try {
            Toast.makeText(setTemp.this,"Parking Temp One Minus",Toast.LENGTH_LONG).show();
            client.publish(topic, message.getBytes(),0,false);
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
