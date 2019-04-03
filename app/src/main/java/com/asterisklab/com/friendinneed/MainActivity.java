package com.asterisklab.com.friendinneed;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.*;
import java.lang.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {


    // Declares variables for Saving into  Shared Prefs

    public static String MY_SHARED_PREF = "mSharedPref";
    public static String mNumber1 = "number1";
    public static String mNumber2 = "number2";
    public static String mNumber3 = "number3";
    public static String mNumber4 = "number4";
    public static String mNumber5 = "number5";
    public static String mName = "Name";
    public static String mMsg = "msg";

    // Declares Global variables for getting extras from Intent and later use

    public String Number1;
    public String Number2;
    public String Number3;
    public String Number4;
    public String Number5;
    public String userName;
    public String customMsg;

    // Declares global variables for loading data from Shared Pref

    public String numLoaded1;
    public String numLoaded2;
    public String numLoaded3;
    public String numLoaded4;
    public String numLoaded5;
    public String loadedUserName;
    public String loadedCustomMsg;

    public String Lat, Lon, msgBody;
    final int REQUEST_CODE = 123;
    LocationManager mLocationManager;
    LocationListener mLocationListner;

    public float distance=0;
    public double distanceFlag = 1000000000;
    public double latFlag = 0;
    public String station = "NotWorking... chill man";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUserLocation();

        // Gets the numbers from contactsActivity and saved it in variables

        Number1 = getIntent().getStringExtra("Contact1");
        Number2 = getIntent().getStringExtra("Contact2");
        Number3 = getIntent().getStringExtra("Contact3");
        Number4 = getIntent().getStringExtra("Contact4");
        Number5 = getIntent().getStringExtra("Contact5");

        userName = getIntent().getStringExtra("name");
        customMsg = getIntent().getStringExtra("msg");

        ImageButton settingsBtn = findViewById(R.id.settingsBtn);

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(myIntent);

            }
        });
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


        ImageButton testBtn = findViewById(R.id.testBtn);

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

                Location newLocation=new Location("newlocation");

                Double latArray[] = {25.9160053, 25.9240504, 25.9688841, 26.1241434 , 25.7722621, 25.7476338,
                        25.812177, 25.6755823, 25.8529648, 25.5733804, 25.5352382, 25.8067002, 25.5413839, 25.964394,
                        26.1096252,25.6545445, 25.5882255, 25.577295, 26.332492, 26.1945975, 26.498407, 26.1187353,
                        26.0348237,26.0911915, 25.9015366, 25.8586636, 25.8344054, 25.9312534, 25.7847056, 26.0222387,
                        25.8987185, 26.1077924, 26.1539026, 25.3277546, 25.3913506, 25.5693544, 25.2806906, 25.1371294,
                        25.110119, 25.6361185, 25.6439975, 25.6326684, 25.6642177, 25.8602952, 25.8037607, 25.7874324,
                        25.9276161, 25.5413839, 25.3859332, 25.4196454, 25.284957, 24.8504995};


                Double lonArray[] = {89.4470094, 89.3434971, 89.2028088, 89.1388761, 89.4129509, 89.2479213,
                        89.0921118, 89.0533787,89.2164825, 89.2714476, 89.3012621, 89.6347404, 89.8412904, 89.6874508,
                        89.6690624,89.6156556,89.6603213,89.8306695, 88.5521248, 88.5547346, 88.33592,88.7538782,
                        88.4600761,88.2752511, 88.2505506, 88.3575224, 88.1201577, 88.8548148, 88.8928377, 89.014323,
                        89.0180647, 88.8157069, 88.9192904, 89.5385688, 89.4638046, 89.5189565, 89.3518039, 89.3836046,
                        89.5821028, 88.6365913, 88.7743994, 88.5470133, 88.9039233, 88.6533192, 88.4558267, 88.5999352,
                        88.7256134, 89.8412904, 88.9919616, 89.0749438, 89.0146452, 89.3706005};


                int length = latArray.length;

                for(int l = 0; l < length; l++){

                    newLocation.setLatitude(latArray[l]);
                    newLocation.setLongitude(lonArray[l]);


                    Location crntLocation=new Location("crntlocation");
                    crntLocation.setLongitude(location.getLongitude());
                    crntLocation.setLatitude(location.getLatitude());

                    distance = crntLocation.distanceTo(newLocation);

                    if(distance <= distanceFlag){

                        latFlag = latArray[l];

                        distanceFlag = distance;

                    }

                }

                if(latFlag == 25.9160053 ){

                    station = "Sadar, Lalmonirhat";

                } else if(latFlag == 25.9240504 ){

                    station = "Aditmari";

                } else if ( latFlag == 25.9688841){

                    station = "Kaliganj";

                } else if (latFlag == 26.1241434) {

                    station = "Hatibandha";

                } else if( latFlag == 25.7722621){

                    station = "Kauniya";

                } else if( latFlag == 25.7476338 ) {

                    station = "Kotwali, Rangpur";

                } else if( latFlag == 25.812177) {

                    station = "Taraganj";

                } else if (latFlag == 25.6755823 ){

                    station = "Bodorganj";

                } else if(latFlag == 25.8529648) {

                    station = "Gangachora";

                } else if(latFlag == 25.5733804) {

                    station = "Mithapukur";

                } else if (latFlag == 25.5352382) {

                    station = "Bamondanga";

                } else if (latFlag == 25.8067002){

                    station = "Kurigram Sadar";

                } else if (latFlag == 25.5413839) {

                    station = "Fulbari";

                } else if (latFlag == 25.964394) {

                    station = "Nageshwari";

                } else if (latFlag == 26.1096252) {

                    station = "Bhurungamari";

                } else if (latFlag == 25.6545445) {

                    station = "Ulipur";

                } else if(latFlag == 25.5882255) {

                    station = "Cilmari";

                } else if(latFlag == 25.577295) {

                    station = "Roumari";

                }else if (latFlag == 26.332492) {

                    station = "Panchagarh Sadar";

                } else if(latFlag == 26.1945975) {

                    station = "Boda";

                } else if (latFlag == 26.498407) {

                    station = "Tetulia";

                } else if(latFlag == 26.1187353) {

                    station = "Debiganj";

                }else if(latFlag == 26.0348237) {

                    station = "Thakurgaon";

                } else if (latFlag == 26.0911915) {

                    station = "Baliadangi";

                } else if(latFlag == 25.9015366) {

                    station = "Ranisankail";

                } else if (latFlag == 25.8586636) {

                    station = "Pirganj";

                } else if (latFlag == 25.8344054) {

                    station = "Haripur";

                } else if(latFlag == 25.9312534) {

                    station = "Nilphamari Sadar";

                } else if (latFlag == 25.7847056) {

                    station = "Saidpur";

                } else if ( latFlag == 26.0222387) {

                    station = "Jaldhaka";

                } else if(latFlag == 25.8987185) {

                    station = "Kishoreganj";

                } else if (latFlag == 26.1077924) {

                    station = "Domar";

                } else if(latFlag == 26.1539026 ) {

                    station = "Dimla";
                }else if(latFlag == 25.3277546) {

                    station = "Gaibandha Sadar";

                } else if(latFlag == 25.3913506) {

                    station = "Sadullahpur";

                } else if(latFlag == 25.5693544 ){

                    station = "Sundarganj";

                } else if(latFlag == 25.2806906) {

                    station = "Polashbari";

                } else if(latFlag == 25.1371294) {

                    station = "Gabindoganj";

                } else if(latFlag == 25.110119) {

                    station = "Shaghata";

                } else if (latFlag == 25.6361185) {

                    station = "Dinajpur Sadar";

                } else if(latFlag == 25.6439975) {

                    station = "Chirrirbandar";

                } else if (latFlag == 25.6326684) {

                    station = "Birol";

                } else if(latFlag == 25.6642177) {

                    station = "Parbotipur";

                } else if(latFlag == 25.8602952) {

                    station = "Birganj";

                } else if(latFlag == 25.8037607) {

                    station = "Bochaganj";

                } else if(latFlag == 25.7874324) {

                    station = "Kaharole";

                } else if(latFlag == 25.9276161) {

                    station = "Khansama";

                } else if(latFlag == 25.5413839) {

                    station = "Fulbari";

                } else if(latFlag == 25.3859332) {

                    station = "Birampur";

                } else if(latFlag == 25.4196454) {

                    station = "Nawabganj";

                } else if(latFlag == 25.284957) {

                    station = "Hakimpur";

                } else if(latFlag == 24.8504995) {

                    station = "Bogra Sadar";
                    
                }




                Lat = String.valueOf(location.getLatitude());
                Lon = String.valueOf(location.getLongitude());

                msgBody = "I am in Danger. Help me !! My Current Location is " + "\n" + "https://www.google.com/maps/search/?api=1&query="+Lat+","+Lon + "  Closest police station = " + station ;
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
        mEditor.putString(mName,userName);
        mEditor.putString(mMsg,customMsg);

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

        loadedUserName = mSharedPrefs.getString(mName,null);
        loadedCustomMsg = mSharedPrefs.getString(mMsg,null);

    }


}

