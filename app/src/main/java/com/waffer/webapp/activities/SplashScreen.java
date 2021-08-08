package com.waffer.webapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.waffer.webapp.R;
import com.waffer.webapp.Utils.Constants;
import com.waffer.webapp.Utils.SharedPreferenceUtil;

import java.util.Locale;

import static android.os.Build.VERSION.SDK_INT;

public class SplashScreen extends AppCompatActivity {

    private static Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        updateLocale("ar");
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        setContentView(R.layout.activity_splash_screen);

        if (SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!SharedPreferenceUtil.getBoolen(SplashScreen.this, Constants.ISUSERLOGGEDIN)) {
                            startActivity(new Intent(SplashScreen.this, WizardActivty.class));
                        }else{
                            startActivity(new Intent(SplashScreen.this, NavigationDrawerActivity.class));
                        }

                        finish();
                    }
                }, 800);
            }
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!SharedPreferenceUtil.getBoolen(SplashScreen.this, Constants.ISUSERLOGGEDIN)) {
                        startActivity(new Intent(SplashScreen.this, WizardActivty.class));
                    }else{
                        startActivity(new Intent(SplashScreen.this, NavigationDrawerActivity.class));
                    }

                    finish();
                }
            }, 800);
        }

//        setLocale("ar",0);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!SharedPreferenceUtil.getBoolen(SplashScreen.this, Constants.ISUSERLOGGEDIN)) {
                            startActivity(new Intent(SplashScreen.this, WizardActivty.class));
                        }else{
                            startActivity(new Intent(SplashScreen.this, NavigationDrawerActivity.class));
                        }

                        finish();
                    }
                }, 800);
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    public void setLocale(String lang,int pos) {
        SharedPreferenceUtil.storeStringValue(this, Constants.LOCALE,lang);
        SharedPreferenceUtil.storeIntValue(this, Constants.POSITION,pos);
        updateLocale(lang);
       // openAcitivty( NavigationDrawerActivity.class);
       // finish();
    }
    protected void updateLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

}
