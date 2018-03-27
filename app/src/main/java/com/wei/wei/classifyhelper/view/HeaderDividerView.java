package com.wei.wei.classifyhelper.view;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import com.wei.wei.R;


public class HeaderDividerView extends AbsHeaderView<String> {

    public HeaderDividerView(Activity context) {
        super(context);
    }

    @Override
    protected void getView(String s, ListView listView) {
        View view = mInflate.inflate(R.layout.header_divider_layout, listView, false);
        listView.addHeaderView(view);
    }

}
