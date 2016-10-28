package com.fort.saos.instantshot.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by saldemachki on 20/10/2016.
 */

public class Photo implements Parcelable{
    private String photoURI;
    private User owner;
    private String comment;

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Photo(){
    }

    public Photo(String photoURI, User owner, String comment) {
        this.photoURI = photoURI;
        this.owner = owner;
        this.comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoURI);
        dest.writeParcelable(owner, flags);
        dest.writeString(comment);
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

    public Photo(Parcel in) {
        this.photoURI = in.readString();
        this.owner = in.readParcelable(getClass().getClassLoader());
        this.comment = in.readString();
    }
}
