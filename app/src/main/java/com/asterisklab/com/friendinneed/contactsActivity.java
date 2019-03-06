package com.asterisklab.com.friendinneed;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class contactsActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPreferences";
    public static final String contact1 = "Contact1";
    public static final String contact2 = "contact2";
    public static final String contact3 = "contact3";
    public static final String contact4 = "contact4";
    public static final String contact5 = "contact5";

    public EditText first;
    public EditText second;
    public EditText third;
    public EditText fourth;
    public EditText fifth;
    public TextView testView;

    public String conLoaded1;
    public String conLoaded2;
    public String conLoaded3;
    public String conLoaded4;
    public String conLoaded5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ImageButton saveBtn = findViewById(R.id.saveBtn);


        first = findViewById(R.id.firstContact);
        second = findViewById(R.id.secondContact);
        third = findViewById(R.id.thirdContact);
        fourth = findViewById(R.id.fourthContact);
        fifth = findViewById(R.id.fifthContact);



        loadData();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();

                loadData();

                Toast myToast = Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_LONG);
                myToast.show();

                Intent mainActivity = new Intent(contactsActivity.this, MainActivity.class);

                mainActivity.putExtra("Contact1",conLoaded1);
                mainActivity.putExtra("Contact2",conLoaded2);
                mainActivity.putExtra("Contact3",conLoaded3);
                mainActivity.putExtra("Contact4",conLoaded4);
                mainActivity.putExtra("Contact5",conLoaded5);

                startActivity(mainActivity);
            }
        });
    }

    public void saveData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(contact1,first.getText().toString());
        editor.putString(contact2, second.getText().toString());
        editor.putString(contact3, third.getText().toString());
        editor.putString(contact4, fourth.getText().toString());
        editor.putString(contact5, fifth.getText().toString());

        editor.apply();
    }

    public void loadData(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        conLoaded1 = sharedPreferences.getString(contact1,"valueNotPassing");
        conLoaded2 = sharedPreferences.getString(contact2,"valueNotPassing");
        conLoaded3 = sharedPreferences.getString(contact3,"valueNotPassing");
        conLoaded4 = sharedPreferences.getString(contact4,"valueNotPassing");
        conLoaded5 = sharedPreferences.getString(contact5,"valueNotPassing");

        first.setText(conLoaded1);
        second.setText(conLoaded2);
        third.setText(conLoaded3);
        fourth.setText(conLoaded4);
        fifth.setText(conLoaded5);

    }

}
