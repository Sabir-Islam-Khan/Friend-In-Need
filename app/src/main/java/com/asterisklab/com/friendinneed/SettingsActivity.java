package com.asterisklab.com.friendinneed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    public EditText name;
    public EditText msg;
    public ImageButton saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = findViewById(R.id.userName);
        msg = findViewById(R.id.userMsg);
        saveBtn = findViewById(R.id.testBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(SettingsActivity.this, MainActivity.class);
                myIntent.putExtra("name",name.getText().toString());
                myIntent.putExtra("msg",msg.getText().toString());

                Toast myToast = Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_LONG);
                myToast.show();

                startActivity(myIntent);
            }
        });
    }
}
