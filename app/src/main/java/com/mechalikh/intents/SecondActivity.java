package com.mechalikh.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        // Get the extra data from the Intent
        String message = intent.getStringExtra("message");

        // Display the message
        Toast.makeText(this,message, Toast.LENGTH_SHORT ).show();
    }


}