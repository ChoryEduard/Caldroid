package com.example.Caldroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by eduard on 12.06.14.
 * calendar adapter
 */

public final class CalendroidAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private int mColumnWidth;


    private class ItemHolder {
        public ImageView imgItem;
        public TextView txtItem;
    }



    public CalendroidAdapter(final Context _context, int _columnWidth) {
        mContext = _context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColumnWidth = _columnWidth;
    }

    @Override
    public final int getCount() {
        return getSize();
    }

    @Override
    public final Object getItem(int i) {
        return null;
    }

    @Override
    public final long getItemId(int i) {
        return 0;
    }

    @Override
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View layout = view;
        ItemHolder item;
        if (view == null) {
            layout = inflater.inflate(R.layout.calendar_item, viewGroup,false);
            layout.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));
            item = new ItemHolder();
            item.imgItem = (ImageView)layout.findViewById(R.id.imgCalItem);
            item.txtItem = (TextView)layout.findViewById(R.id.tvDateCalItem);
            layout.setTag(item);
        } else {
            item = (ItemHolder)layout.getTag();
        }
        item.txtItem.setText(String.valueOf(i));
        return layout;
    }


    private final int getSize() {
        return 100;
    }
}

