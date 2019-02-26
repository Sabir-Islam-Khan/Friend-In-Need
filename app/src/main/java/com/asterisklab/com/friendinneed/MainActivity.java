package com.asterisklab.com.friendinneed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ContactsBtn = findViewById(R.id.ContactsBtn);

        ContactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent contactsActivity = new Intent(MainActivity.this, contactsActivity.class);

                startActivity(contactsActivity);
            }
        });

    }
}
