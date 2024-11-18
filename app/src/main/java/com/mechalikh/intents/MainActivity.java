package com.mechalikh.intents;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonInvokeActivity;
    private Button mButtonBroadcast;
    private Button mButtonCall;
    private Button mButonWeb;
    private Button mButonEmail;
    private Button mButonResult;
    private MyBroadcastReceiver mBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mechalikh.broadcast");
        registerReceiver(mBroadcastReceiver, filter);

        // Find the "Invoke Activity" button and set a click listener
        mButtonInvokeActivity = findViewById(R.id.invokeButton);
        mButtonInvokeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // Add some extra data to the Intent
                intent.putExtra("message", "Hello from MainActivity!");

                // Start the SecondActivity
                startActivity(intent);
            }
        });

        // Find the "Broadcast" button and set a click listener
        mButtonBroadcast = findViewById(R.id.broadcastButton);
        mButtonBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to broadcast a message
                Intent intent = new Intent("com.mechalikh.broadcast");
                intent.putExtra("message", ((EditText)findViewById(R.id.editText)).getText().toString());

                // Send the broadcast
                sendBroadcast(intent);
            }
        });


        mButtonCall = findViewById(R.id.invokeButton2);
        mButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri telnumber = Uri.parse("tel:012345678");
                Intent call = new Intent(Intent.ACTION_DIAL, telnumber);
                startActivity(call);
            }
        });

        mButonWeb = findViewById(R.id.invokeButton3);
        mButonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });

        mButonResult = findViewById(R.id.invokeButton4);
        mButonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivityForResult(intent, 48);
            }
        });


        mButonEmail = findViewById(R.id.mButtonEmail);
        mButonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String[] recipients = new String[]{"my@email.com", "",};
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Message");
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 48)
            Toast.makeText(this, "Request code retrieved (I know where I come from)",
                    Toast.LENGTH_LONG).show();
        if (resultCode == 50)
            Toast.makeText(this, "Result code ok (I received the right code)",
                    Toast.LENGTH_LONG).show();
    }


}
