package com.asterisklab.com.friendinneed;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // Declares variables for Saving into  Shared Prefs

    public static String MY_SHARED_PREF = "mSharedPref";
    public static String mNumber1 = "number1";
    public static String mNumber2 = "number2";
    public static String mNumber3 = "number3";
    public static String mNumber4 = "number4";
    public static String mNumber5 = "number5";

    // Declares Global variables for getting extras from Intent and later use

    public String Number1;
    public String Number2;
    public String Number3;
    public String Number4;
    public String Number5;

    // Declares global variables for loading data from Shared Pref

    public String numLoaded1;
    public String numLoaded2;
    public String numLoaded3;
    public String numLoaded4;
    public String numLoaded5;

    public String msgBody, Lat, Lon;
    final int REQUEST_CODE = 123;
    public String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    LocationManager mLocationManager;
    LocationListener mLocationListner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the numbers from contactsActivity and saved it in variables

        Number1 = getIntent().getStringExtra("Contact1");
        Number2 = getIntent().getStringExtra("Contact2");
        Number3 = getIntent().getStringExtra("Contact3");
        Number4 = getIntent().getStringExtra("Contact4");
        Number5 = getIntent().getStringExtra("Contact5");

        getUserLocation();

        String checkButtonPress = "saveButtonPressed";
        String testValue = getIntent().getStringExtra("activityLogger");

        if (checkButtonPress.equals(testValue)) {

            SaveData();
            LoadData();
        } else {

            LoadData();
        }


        ImageButton ContactsBtn = findViewById(R.id.ContactsBtn);

        ContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contactsActivity = new Intent(MainActivity.this, contactsActivity.class);

                startActivity(contactsActivity);
            }
        });


        Button testBtn = findViewById(R.id.testBtn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("clima","Button Pressed");

                getUserLocation();

                TextView testView = findViewById(R.id.testView);

                testView.setText(msgBody);

                SmsManager mSmsManager = SmsManager.getDefault();
                mSmsManager.sendTextMessage(numLoaded1, null, msgBody, null, null);
                mSmsManager.sendTextMessage(numLoaded2, null, msgBody, null, null);
                mSmsManager.sendTextMessage(numLoaded3, null, msgBody, null, null);
                mSmsManager.sendTextMessage(numLoaded4, null, msgBody, null, null);
                mSmsManager.sendTextMessage(numLoaded5, null, msgBody, null, null);


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserLocation();
    }

    private void getUserLocation() {

        Log.d("Clima", "GetUserLoc called");

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d("clima","locationChanged Called");

                msgBody = "Lat = " + location.getLatitude() + "lon = " + location.getLongitude() ;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, REQUEST_CODE );
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListner);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListner);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE){

            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getUserLocation();
            } else {

                Log.d("Clima", "Permission Denied");
            }
        }
    }

    // Creates a method for saving Numbers in Shared Prefs
    public void SaveData() {

        SharedPreferences mSharedPrefs = getSharedPreferences(MY_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPrefs.edit();

        mEditor.putString(mNumber1, Number1);
        mEditor.putString(mNumber2, Number2);
        mEditor.putString(mNumber3, Number3);
        mEditor.putString(mNumber4, Number4);
        mEditor.putString(mNumber5, Number5);

        mEditor.apply();
    }

    // Creates a method for loading data from Shared Prefs

    public void LoadData() {

        SharedPreferences mSharedPrefs = getSharedPreferences(MY_SHARED_PREF, MODE_PRIVATE);

        numLoaded1 = mSharedPrefs.getString(mNumber1, null);
        numLoaded2 = mSharedPrefs.getString(mNumber2, null);
        numLoaded3 = mSharedPrefs.getString(mNumber3, null);
        numLoaded4 = mSharedPrefs.getString(mNumber4, null);
        numLoaded5 = mSharedPrefs.getString(mNumber5, null);

    }


}

