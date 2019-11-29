package com.flueky.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.flueky.sample.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * Toast 使用演示
     *
     * @param v
     */
    public void showToast(View v) {
        Toast.makeText(this, "弹出 Toast", Toast.LENGTH_SHORT).show();
    }
}