package com.wang.xml.bean;

/**
 * Created by wang on 17-2-10.
 */
public class SmsBean {
    public String num;
    public String msg;
    public String date;
    public int id;

    @Override
    public String toString() {
        return "SmsBean{" +
                "num='" + num + '\'' +
                ", msg='" + msg + '\'' +
                ", date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
