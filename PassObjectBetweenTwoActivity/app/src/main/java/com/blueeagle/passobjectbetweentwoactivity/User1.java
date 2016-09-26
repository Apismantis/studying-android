package com.blueeagle.passobjectbetweentwoactivity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User1 implements Parcelable {
    private String name;
    private String email;
    private Date birthday;
    private String city;

    public User1(String name, String email, Date birthday, String city) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeLong(birthday.getTime());
        dest.writeString(city);
    }

    private User1(Parcel in) {
        name = in.readString();
        email = in.readString();
        birthday = new Date(in.readLong());
        city = in.readString();
    }

    public static final Parcelable.Creator<User1> CREATOR = new Parcelable.Creator<User1>() {

        @Override
        public User1 createFromParcel(Parcel source) {
            return new User1(source);
        }

        @Override
        public User1[] newArray(int size) {
            return new User1[size];
        }
    };
}
