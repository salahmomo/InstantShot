package com.fort.saos.instantshot.usecase;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by saldemachki on 25/10/2016.
 */

public class PermissionsUseCase {
    public final static int REQUEST_PERMISSIONS = 8;

    public boolean checkAppPermission(Activity activity) {
        int sendSMSPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS);
        int receiveSMSPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
        int readContactPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        int writeContactPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CONTACTS);
        int readPhoneStatePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (sendSMSPermission != PackageManager.PERMISSION_GRANTED || receiveSMSPermission != PackageManager.PERMISSION_GRANTED
                || readContactPermission != PackageManager.PERMISSION_GRANTED || writeContactPermission != PackageManager.PERMISSION_GRANTED
                || readPhoneStatePermission != PackageManager.PERMISSION_GRANTED || readExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            List<String> permissionToAsk = new ArrayList<>();
            permissionToAsk = addPermissionToListIfDenied(permissionToAsk, Manifest.permission.SEND_SMS, sendSMSPermission);
            permissionToAsk = addPermissionToListIfDenied(permissionToAsk, Manifest.permission.RECEIVE_SMS, receiveSMSPermission);
            permissionToAsk = addPermissionToListIfDenied(permissionToAsk, Manifest.permission.READ_CONTACTS, readContactPermission);
            permissionToAsk = addPermissionToListIfDenied(permissionToAsk, Manifest.permission.WRITE_CONTACTS, writeContactPermission);
            permissionToAsk = addPermissionToListIfDenied(permissionToAsk, Manifest.permission.READ_PHONE_STATE, readPhoneStatePermission);
            permissionToAsk = addPermissionToListIfDenied(permissionToAsk, Manifest.permission.READ_EXTERNAL_STORAGE, readExternalStoragePermission);

            Object[] objPermission = permissionToAsk.toArray();
            String[] stringPermission = Arrays.copyOf(objPermission, objPermission.length, String[].class);
            ActivityCompat.requestPermissions(activity, stringPermission, REQUEST_PERMISSIONS);
            return false;
        }

        return true;
    }

    /**
     * Rajoute une permissionà une list de permission si elle refusé
     *
     * @param permissions
     * @param permission
     * @param permissionStatus
     * @return
     */

    private List<String> addPermissionToListIfDenied(List<String> permissions, String permission, Integer permissionStatus) {
        if (permissionStatus == PackageManager.PERMISSION_DENIED)
            permissions.add(permission);

        return permissions;
    }


    /**
     * Vérifie si toutes les persmissions son accésible
     *
     * @param grantResults
     * @return
     */
    public boolean isAllPermissionIsGranted(int[] grantResults) {
        boolean allIsGranted = true;

        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED)
                allIsGranted = false;
        }
        return allIsGranted;
    }
}
