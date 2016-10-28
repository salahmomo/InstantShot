package com.fort.saos.instantshot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by saldemachki on 20/10/2016.
 */

public class Album implements Parcelable{
    private String name;
    private String coverURI;
    private String comment;
    private User owner;
    private List<Photo> photos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverURI() {
        return coverURI;
    }

    public void setCoverURI(String coverURI) {
        this.coverURI = coverURI;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Album(){
    }

    public Album(String name, String coverURI, String comment, User owner, List<Photo> photos) {
        this.name = name;
        this.coverURI = coverURI;
        this.owner = owner;
        this.photos = photos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(coverURI);
        dest.writeString(comment);
        dest.writeParcelable(owner, flags);
        dest.writeList(photos);
    }

    public static final Creator<Album> CREATOR =
            new Creator<Album>() {
                @Override
                public Album createFromParcel(Parcel source) {
                    return new Album(source);
                }

                @Override
                public Album[] newArray(int size) {
                    return new Album[size];
                }
            };

    public Album(Parcel in) {
        this.name = in.readString();
        this.coverURI = in.readString();
        this.comment = in.readString();
        this.owner = in.readParcelable(getClass().getClassLoader());
        this.photos = in.readParcelable(getClass().getClassLoader());
    }
}
