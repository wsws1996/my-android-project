package com.wang.createdatabase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wang.createdatabase.R;
import com.wang.createdatabase.bean.InfoBean;

import java.util.List;

/**
 * Created by wang on 17-2-13.
 */
public class QueryAdapter extends BaseAdapter {
    private Context mContext;
    private List<InfoBean> arrayList;

    public QueryAdapter(Context mContext, List<InfoBean> list) {
        this.mContext = mContext;
        this.arrayList = list;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = View.inflate(mContext, R.layout.item_database_layout, null);
        }
        TextView item_tv_id = (TextView) view.findViewById(R.id.item_tv_id);
        TextView item_tv_name = (TextView) view.findViewById(R.id.item_tv_name);
        TextView item_tv_phone = (TextView) view.findViewById(R.id.item_tv_phone);

        InfoBean infoBean = arrayList.get(position);

        item_tv_id.setText(infoBean.id);
        item_tv_name.setText(infoBean.name);
        item_tv_phone.setText(infoBean.phone);

        return view;
    }
}
