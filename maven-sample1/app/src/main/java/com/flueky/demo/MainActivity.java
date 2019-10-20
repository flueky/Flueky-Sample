package com.flueky.demo;

import android.app.Activity;
import android.os.Bundle;

import com.flueky.lib.MavenTest;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MavenTest.test();

    }
}