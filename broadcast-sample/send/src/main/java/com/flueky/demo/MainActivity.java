package com.flueky.demo;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.flueky.app.R;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private TestReceiver testReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testReceiver = new TestReceiver();

        registerReceiver(testReceiver, new IntentFilter("com.flueky.demo.TEST_RECEIVER1"));
        registerReceiver(testReceiver, new IntentFilter("com.flueky.app.Demo_Receiver"));

        LocalBroadcastManager.getInstance(this).registerReceiver(testReceiver, new IntentFilter("com.flueky.demo.TEST_RECEIVER2"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(testReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(testReceiver);
    }

    public void sendOrderBroadCast(View v) {
        Intent intent = new Intent("com.flueky.app.Demo_Receiver");
        sendOrderedBroadcast(intent, "com.flueky.demo.permission.Demo_Receiver");
    }

    public void sendBroadCast(View v) {
        Intent intent = new Intent("com.flueky.app.Demo_Receiver");
//        intent.setPackage(getPackageName());
        sendBroadcast(intent, "com.flueky.demo.permission.Demo_Receiver");
        sendBroadcast(new Intent("com.flueky.demo.TEST_RECEIVER1"),"com.flueky.demo.permission.Demo_Receiver");
        sendBroadcast(new Intent("com.flueky.demo.TEST_RECEIVER2"));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.flueky.demo.TEST_RECEIVER2"));
        Toast.makeText(this, "广播已发送", Toast.LENGTH_SHORT).show();
    }

    class TestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "TestReceiver  onReceive: " + intent.getAction());
        }
    }

}