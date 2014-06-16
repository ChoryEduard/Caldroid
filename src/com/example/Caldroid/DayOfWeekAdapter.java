package com.example.Caldroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by eduard on 12.06.14.
 *
 */
public final class DayOfWeekAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;

    private final String[] mData = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat"};


    public DayOfWeekAdapter(final Context _context, int _columnWidth) {
        mContext = _context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public final int getCount() {
        return mData.length;
    }

    @Override
    public final Object getItem(int position) {
        return null;
    }

    @Override
    public final long getItemId(int position) {
        return 0;
    }

    @Override
    public final View getView(final int pos, View view, ViewGroup parent) {
        View layout = view;
        layout = inflater.inflate(R.layout.custom_item_day_of_week, parent,false);
        //layout.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
        TextView txtDay = (TextView) layout.findViewById(R.id.txtDayOfWeekItem);
        txtDay.setText(mData[pos]);

        return layout;
    }
}
