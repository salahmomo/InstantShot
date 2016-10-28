package com.fort.saos.instantshot.usecase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by saldemachki on 24/10/2016.
 */

public class MmsUseCase {
    public void SendMMS(Context context, String imagePath, String strPhone, String strMessage){
        Uri uri = Uri.parse("file://"+ Environment.getExternalStorageDirectory()+"/test.png");
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra("address",strPhone);
        i.putExtra("sms_body",strMessage);
        i.putExtra(Intent.EXTRA_STREAM,"file:/"+uri);
        i.setType("image/png");
        context.startActivity(i);
    }


    public void ReceiveMMS() {

    }
}
