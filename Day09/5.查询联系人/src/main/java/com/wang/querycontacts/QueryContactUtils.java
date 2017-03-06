package com.wang.querycontacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 17-3-6.
 */

public class QueryContactUtils {
    public static List<Contact> queryContacts(Context context) {

        ArrayList<Contact> contactLists = new ArrayList<>();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"contact_id"}, null,
                null,
                null);
        while (cursor.moveToNext()) {
            String contact_id = cursor.getString(0);
            System.out.println("contact_id:" + contact_id);
//            if (contact_id != null) {


            Contact contact = new Contact();
            contact.id = contact_id;

            Cursor dataCursor = context.getContentResolver().query(dataUri, new
                    String[]{"data1",
                    "mimetype"}, "raw_contact_id=?", new String[]{contact_id}, null);
            while (dataCursor.moveToNext()) {
                String data1 = dataCursor.getString(0);
                String mimeType = dataCursor.getString(1);
                switch (mimeType) {
                    case "vnd.android.cursor.item/name":
                        contact.name = data1;
                        break;
                    case "vnd.android.cursor.item/phone_v2":
                        contact.phone = data1;
                        break;
                    case "vnd.android.cursor.item/email_v2":
                        contact.email = data1;
                        break;
                }
            }
            contactLists.add(contact);
        }
//        }
        return contactLists;
    }
}
