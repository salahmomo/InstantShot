package com.fort.saos.instantshot.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fort.saos.instantshot.R;
import com.fort.saos.instantshot.presenter.SplashScreenPresenter;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (splashScreenPresenter == null){
            splashScreenPresenter = new SplashScreenPresenter(this);
        }

        splashScreenPresenter.checkAppPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull final int[] grantResults) {
        splashScreenPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
