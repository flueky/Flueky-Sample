package com.flueky.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void jump(View v){
        Intent intent  = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}