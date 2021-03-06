package com.flueky.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle options = getIntent().getExtras();
        if (options != null)
            Toast.makeText(this, options.getString("key"), Toast.LENGTH_LONG).show();
    }

    public void jump(View v) {
        if (v.getTag().toString().equals("second")) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }else if (v.getTag().toString().equals("third")) {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        } else if (v.getTag().toString().equals("fourth")) {
            Intent intent = new Intent(this, FourthActivity.class);
            startActivity(intent);
        }
    }
}
