package com.mechalikh.intents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.mechalikh.broadcast")) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String message = extras.getString("message");
                Toast.makeText(context, "Broadcast message received: " + message,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
