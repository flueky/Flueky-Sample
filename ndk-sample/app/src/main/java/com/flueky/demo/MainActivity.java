package com.flueky.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    static {
        System.loadLibrary("hello-jni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvHello = findViewById(R.id.hello);
        tvHello.setText(hello());

        Toast.makeText(this,"zkf",Toast.LENGTH_SHORT).show();
    }

    private native String hello();
}