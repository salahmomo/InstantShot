package com.fort.saos.instantshot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saldemachki on 20/10/2016.
 */

public class User implements Parcelable{
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User(){
    }

    public User(String phoneNumber, String firstName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(firstName);
        dest.writeString(lastName);
    }

    public static final Creator<User> CREATOR =
            new Creator<User>() {
                @Override
                public User createFromParcel(Parcel source) {
                    return new User(source);
                }

                @Override
                public User[] newArray(int size) {
                    return new User[size];
                }
            };

    public User(Parcel in) {
        this.phoneNumber = in.readString();
        this.firstName = in.readParcelable(getClass().getClassLoader());
        this.lastName = in.readString();
    }
}
