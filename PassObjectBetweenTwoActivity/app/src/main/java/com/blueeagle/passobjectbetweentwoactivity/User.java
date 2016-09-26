package com.blueeagle.passobjectbetweentwoactivity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by apismantis on 23/09/2016.
 *
 */

public class User implements Serializable {

    private String name;
    private String email;
    private Date birthday;
    private String city;

    public User(String name, String email, Date birthday, String city) {
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
}
