package com.wang.xml.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Xml;

import com.wang.xml.bean.SmsBean;
import com.wang.xml.dao.SmsDao;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by wang on 17-2-10.
 */
public class SmsUtils {
    public static boolean backupSms(Context context) {
        ArrayList<SmsBean> allSms = SmsDao.getAllSms();
        try {
            XmlSerializer xmlSerializer = Xml.newSerializer();
            xmlSerializer.setOutput(context.openFileOutput("backupsms2.xml", Context
                    .MODE_PRIVATE), "utf-8");
            xmlSerializer.startDocument("utf-8", true);
            xmlSerializer.startTag(null, "Smss");
            for (SmsBean smsBean :
                    allSms) {
                xmlSerializer.startTag(null, "Sms");
                xmlSerializer.attribute(null, "id", smsBean.id + "");
                xmlSerializer.startTag(null, "num");
                xmlSerializer.text(smsBean.num);
                xmlSerializer.endTag(null, "num");
                xmlSerializer.startTag(null, "msg");
                xmlSerializer.text(smsBean.msg);
                xmlSerializer.endTag(null, "msg");
                xmlSerializer.startTag(null, "date");
                xmlSerializer.text(smsBean.date);
                xmlSerializer.endTag(null, "date");
                xmlSerializer.endTag(null, "Sms");
            }
            xmlSerializer.endTag(null, "Smss");
            xmlSerializer.endDocument();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int restoreSms(Context context) {
        ArrayList<SmsBean> arrayList = null;
        SmsBean smsBean = null;
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            AssetManager assets = context.getAssets();
            InputStream inputStream = assets.open("backupsms.xml");
//            xmlPullParser.setInput(context.openFileInput("backupsms2.xml"), "utf-8");
            xmlPullParser.setInput(inputStream, "utf-8");
            int type = xmlPullParser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                String currentTagName = xmlPullParser.getName();
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if (currentTagName.equals("Smss")) {
                            arrayList = new ArrayList<>();
                        } else if (currentTagName.equals("Sms")) {
                            smsBean = new SmsBean();
                            smsBean.id
                                    = Integer.valueOf(xmlPullParser.getAttributeValue(null, "id"));
                        } else if (currentTagName.equals("num")) {
                            smsBean.num = xmlPullParser.nextText();
                        } else if (currentTagName.equals("msg")) {
                            smsBean.msg = xmlPullParser.nextText();
                        } else if (currentTagName.equals("date")) {
                            smsBean.date = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (currentTagName.equals("Sms")) {
                            arrayList.add(smsBean);
                        }
                        break;
                    default:
                        break;
                }
                type = xmlPullParser.next();
            }
            for (SmsBean s :
                    arrayList) {
                System.out.println(s);
            }
            return arrayList.size();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
