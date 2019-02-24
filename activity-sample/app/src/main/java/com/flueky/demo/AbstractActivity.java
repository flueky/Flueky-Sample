package com.flueky.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public abstract class AbstractActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "heheda onCreate");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "heheda Resume");
        TextView tvTaskId = (TextView) findViewById(R.id.tv_task_id);
        tvTaskId.setText("task id: " + getTaskId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "heheda Start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "heheda Restart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "heheda Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "heheda Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "heheda Destroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "heheda onNewIntent");
    }
}
