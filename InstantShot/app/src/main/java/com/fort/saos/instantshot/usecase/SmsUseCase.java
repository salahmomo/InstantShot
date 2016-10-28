package com.fort.saos.instantshot.usecase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saldemachki on 24/10/2016.
 */

public class SmsUseCase {
    public void sendSMS(Context context, String strPhone, String strMessage){

        SmsManager sms = SmsManager.getDefault();

        ArrayList<String> messageParts = sms.divideMessage(strMessage);

        sms.sendMultipartTextMessage(strPhone, null, messageParts, null, null);

        Toast.makeText(context, "Sent.", Toast.LENGTH_SHORT).show();
    }

    public class SmsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle intentExtras = intent.getExtras();

            if (intentExtras != null) {
            /* Get Messages */
                Object[] sms = (Object[]) intentExtras.get("pdus");

                for (int i = 0; i < sms.length; ++i) {
                /* Parse Each Message */
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    String phone = smsMessage.getOriginatingAddress();
                    String message = smsMessage.getMessageBody().toString();

                    Toast.makeText(context, phone + ": " + message, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
