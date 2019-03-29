package com.asterisklab.com.friendinneed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    public EditText name;
    public EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = findViewById(R.id.userName);
        msg = findViewById(R.id.userMsg);

        ImageButton saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
                myIntent.putExtra("userName",name.getText().toString());
                myIntent.putExtra("customMsg",msg.getText().toString());

                startActivity(myIntent);
            }
        });
    }
}
