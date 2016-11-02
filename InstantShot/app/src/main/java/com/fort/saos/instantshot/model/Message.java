package com.fort.saos.instantshot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by saldemachki on 28/10/2016.
 */

public class Message implements Parcelable {
    private String text;
    private User owner;
    private Date date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Message(){
    }

    public Message(String text, User owner, Date date, Boolean send) {
        this.text = text;
        this.owner = owner;
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeParcelable(owner, flags);
        dest.writeLong(date.getTime());
    }

    public static final Creator<Photo> CREATOR =
            new Creator<Photo>() {
                @Override
                public Photo createFromParcel(Parcel source) {
                    return new Photo(source);
                }

                @Override
                public Photo[] newArray(int size) {
                    return new Photo[size];
                }
            };

    public Message(Parcel in) {
        this.text = in.readString();
        this.owner = in.readParcelable(getClass().getClassLoader());
        this.date = new Date(in.readLong());
    }
}
