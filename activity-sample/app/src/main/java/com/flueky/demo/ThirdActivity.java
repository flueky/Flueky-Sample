package com.flueky.demo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ThirdActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void jump(View v){
        if(v.getTag().toString().equals("second")) {
            //
            Intent intent = new Intent(this,SecondActivity.class);
            startActivity(intent);
        }else if(v.getTag().toString().equals("third")){
            Intent intent = new Intent(this,ThirdActivity.class);
            startActivity(intent);
        }else if(v.getTag().toString().equals("fourth")){
            Intent intent = new Intent(this,FourthActivity.class);
            startActivity(intent);
        }
    }
    public void back(View v){
        finish();
    }

    /**
     * 示例代码，通常不建议这种写法
     */
    @Override
    public void finish() {

        Intent intent = getIntent();
        Log.d("intent", "finish: "+intent+" "+intent.hashCode());
        // 可以通过 intent 携带需要回传的数据
        // 以及可以通过 resultcode 传回标识
        setResult(111,intent);
        super.finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
        }

    }
}
