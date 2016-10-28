package com.fort.saos.instantshot.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.fort.saos.instantshot.usecase.PermissionsUseCase;
import com.fort.saos.instantshot.view.MainActivity;
import com.fort.saos.instantshot.view.NewAlbumActivity;
import com.fort.saos.instantshot.view.SplashScreenActivity;

/**
 * Created by saldemachki on 26/10/2016.
 */

public class SplashScreenPresenter {
    SplashScreenActivity splashScreenActivity;

    public SplashScreenPresenter(SplashScreenActivity splashScreenActivity) {
        this.splashScreenActivity = splashScreenActivity;
    }

    public void goToMainActivity(){
        Intent intent = new Intent(splashScreenActivity.getBaseContext(), MainActivity.class);
        splashScreenActivity.startActivity(intent);
        splashScreenActivity.finish();
    }

    public void checkAppPermission(){
        PermissionsUseCase permissionsUseCase = new PermissionsUseCase();
        if (permissionsUseCase.checkAppPermission(splashScreenActivity)) {
            goToMainActivity();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull final int[] grantResults) {
        PermissionsUseCase permissionsUseCase = new PermissionsUseCase();
        switch (requestCode) {
            case PermissionsUseCase.REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && permissionsUseCase.isAllPermissionIsGranted(grantResults)) {
                    goToMainActivity();
                } else {
                    permissionsUseCase.checkAppPermission(splashScreenActivity);
                }
                return;
            }
        }
    }
}
