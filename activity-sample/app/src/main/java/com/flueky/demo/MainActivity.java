package com.flueky.demo;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AbstractActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent,requestCode);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
        }
    }

    public void jump(View v) {
        // 跳转一个 Activity
        if (v.getTag().toString().equals("single")) {
            Intent intent = new Intent(this, SecondActivity.class);
            Bundle data = new Bundle();
            data.putString("key","flueky");
            intent.putExtras(data);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                // 指定 Activity 跳转动画选项
                Bundle options = ActivityOptions.makeCustomAnimation(this, R.anim.activity_enter, R.anim.activity_exit).toBundle();
                startActivity(intent, options);
            }

        } else if(v.getTag().toString().equals("multi2")){
            // 跳转多个 Activity
            Intent[] intent = new Intent[2];
            intent[0] = new Intent(this, ThirdActivity.class);
            intent[1] = new Intent(this, SecondActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // 同时打开两个 Activity ，但是只会显示 ThirdActivity 。但是 SecondActivity 在 Activity 栈中。
                // finish ThirdActivity 会显示 SecondActivity 。
                startActivities(intent);
            }
        } else if(v.getTag().toString().equals("multi")){
            // 跳转多个 Activity
            Intent[] intent = new Intent[2];
            intent[0] = new Intent(this, SecondActivity.class);
            intent[1] = new Intent(this, ThirdActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // 同时打开两个 Activity ，但是只会显示 ThirdActivity 。但是 SecondActivity 在 Activity 栈中。
                // finish ThirdActivity 会显示 SecondActivity 。
                startActivities(intent);
            }
        } else if(v.getTag().toString().equals("result")){
            Intent intent = new Intent(this, ThirdActivity.class);
            Log.d("intent", "jump: "+intent+" "+intent.hashCode());
            startActivityForResult(intent,123);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("intent", "onActivityResult: "+data+" "+data.hashCode());
        Log.e("intent", "onActivityResult: "+requestCode+" "+resultCode);

    }
}