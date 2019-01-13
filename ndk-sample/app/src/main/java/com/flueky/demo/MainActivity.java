package com.flueky.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    static {
        // 加载 JNI 库
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

    // 声明 Native 方法
    private native String hello();
}