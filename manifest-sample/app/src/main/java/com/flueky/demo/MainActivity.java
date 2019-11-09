package com.flueky.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.flueky.lib.TestActivity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, TestActivity.class));

    }
}