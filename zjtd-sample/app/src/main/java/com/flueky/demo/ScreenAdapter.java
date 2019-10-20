package com.flueky.demo;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

public class ScreenAdapter {

    private static float sNonCompatDensity;
    private static float sNonCompatScaledDensity;

    public static final void setCustomDensity(Activity activity, final Application application) {
        if (activity == null || application == null)
            return;

        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();

        if (sNonCompatDensity == 0) {
            sNonCompatDensity = appDisplayMetrics.density;
            sNonCompatScaledDensity = appDisplayMetrics.scaledDensity;

            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0)
                        sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels / 420;
        final float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics actDisplayMetrics = activity.getResources().getDisplayMetrics();
        actDisplayMetrics.density = targetDensity;
        actDisplayMetrics.scaledDensity = targetScaledDensity;
        actDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
