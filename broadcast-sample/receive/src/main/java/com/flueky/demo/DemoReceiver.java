package com.flueky.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DemoReceiver extends BroadcastReceiver {
    private static final String TAG = "DemoReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + "广播接收：" + intent.getAction());
        Toast.makeText(context, "广播接收：" + intent.getAction(), Toast.LENGTH_SHORT).show();


        //非有序广播，不不做下面的操作
        if (!isOrderedBroadcast())
            return;

        Bundle bundle = getResultExtras(true);
        String received = bundle.getString("received");

        Log.d(TAG, "onReceive: 广播接收 " + (received == null ? "null" : received));

        bundle.putString("received", "已被 reveive 接收");
        setResultExtras(bundle);
    }
}
