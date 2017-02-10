package com.wang.xml.dao;

import com.wang.xml.bean.SmsBean;

import java.util.ArrayList;

/**
 * Created by wang on 17-2-10.
 */
public class SmsDao {
    public static ArrayList<SmsBean> getAllSms() {
        ArrayList<SmsBean> arrayList = new ArrayList<>();

        SmsBean smsBean = new SmsBean();
        smsBean.id = 1;
        smsBean.num = "122";
        smsBean.msg = "测试信息";
        smsBean.date = "2018-01-14";
        arrayList.add(smsBean);


        SmsBean smsBean1 = new SmsBean();
        smsBean1.id = 2;
        smsBean1.num = "120";
        smsBean1.msg = "测试信息1";
        smsBean1.date = "2018-01-14";
        arrayList.add(smsBean1);


        SmsBean smsBean2 = new SmsBean();
        smsBean2.id = 3;
        smsBean2.num = "123";
        smsBean2.msg = "测试信息2";
        smsBean2.date = "2018-01-14";
        arrayList.add(smsBean2);
        return arrayList;
    }
}
