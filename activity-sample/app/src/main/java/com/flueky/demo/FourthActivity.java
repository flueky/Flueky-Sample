package com.flueky.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FourthActivity extends AbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }

    public void jump(View v) {
        if (v.getTag().toString().equals("third")) {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        } else if (v.getTag().toString().equals("second")) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        } else if (v.getTag().toString().equals("fourth")) {
            Intent intent = new Intent(this, FourthActivity.class);
            startActivity(intent);
        }

    }
}
