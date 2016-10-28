package com.fort.saos.instantshot.usecase;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.fort.saos.instantshot.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saldemachki on 25/10/2016.
 */

public class ContactUseCase {
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;

    public User getPhoneUser(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String myPhoneNumber =  tm.getLine1Number();

        User user = new User(myPhoneNumber, "", "");

        return user;
    }

    public List<User> getAllContactFromPhone(Context context){
        List<User> contacts = new ArrayList<>();
        Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext())
        {
            String contactID = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));

            String firstName = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            User user = new User(phoneNumber, firstName, "");
            contacts.add(user);
        }
        phones.close();

        return contacts;
    }
}
