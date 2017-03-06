package com.wang.querycontacts;

/**
 * Created by wang on 17-3-6.
 */

public class Contact {
    public String id;
    public String name;
    public String phone;
    public String email;

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
