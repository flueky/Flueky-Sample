package com.flueky.demo;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContent = findViewById(R.id.activity_main_tv_content);

        PackageManager pm = getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);

            StringBuffer buffer = new StringBuffer("应用 id：");
            buffer.append(info.packageName);
            buffer.append("\n版本名称：");
            buffer.append(info.versionName);
            buffer.append("\n版本号：");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                buffer.append(info.getLongVersionCode());
            else
                buffer.append(info.versionCode);

            tvContent.setText(buffer.toString());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }
}