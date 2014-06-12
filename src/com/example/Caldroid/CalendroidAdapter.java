package com.example.Caldroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by eduard on 12.06.14.
 * calendar adapter
 */

public final class CalendroidAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private int mColumnWidth;

    public CalendroidAdapter(final Context _context, int _columnWidth) {
        mContext = _context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColumnWidth = _columnWidth;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layout = inflater.inflate(R.layout.calendar_item, viewGroup,false);
        if (layout != null) {
            layout.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));
          /*  layout.setMinimumHeight(mColumnWidth);
            layout.setMinimumWidth(mColumnWidth);*/
        }
        return layout;
    }
}

