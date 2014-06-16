package com.example.Caldroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by eduard on 12.06.14.
 * calendar adapter
 */

public final class CalendroidAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private int mColumnWidth;
    private ArrayList<String> mPrewMonth = new ArrayList<String>();
    private ArrayList<String> mCorentMonth = new ArrayList<String>();
    private ArrayList<String> mNextMonth = new ArrayList<String>();



    private class ItemHolder {
        public ImageView imgItem;
        public TextView txtItem;
    }



    public CalendroidAdapter(final Context _context, int _columnWidth) {
        mContext = _context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mColumnWidth = _columnWidth;
        init();
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
    public final View getView(final int i, View view, ViewGroup viewGroup) {
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
        item.txtItem.setText(getDateItem(i));
        isTransperent(item, i);
        return layout;
    }


    private final int getSize() {
        return mPrewMonth.size() + mCorentMonth.size() + mNextMonth.size();
    }


    private final void isTransperent(ItemHolder item, final int pos) {
        if (mPrewMonth.size() > pos || (mCorentMonth.size() + mPrewMonth.size()) < pos)
            setTransparent(item);

    }


    private final void setTransparent(ItemHolder item) {
        item.imgItem.setAlpha(100);
    }

    /*
    * test init function
    *
     */

    private final void init() {
        for (int i = 1; i <= 31; i++) {
            mPrewMonth.add(String.valueOf(i));
            mCorentMonth.add(String.valueOf(i));
            mNextMonth.add(String.valueOf(i));
        }
    }

    private final String getDateItem(final int pos) {
        if (mPrewMonth.size() > pos)
            return mPrewMonth.get(pos);
        else if ((mCorentMonth.size() + mPrewMonth.size()) > pos)
            return mCorentMonth.get(pos - mPrewMonth.size());
        else
            return mNextMonth.get(pos - mPrewMonth.size() - mCorentMonth.size());

    }

}

