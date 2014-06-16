package com.example.Caldroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by eduard on 12.06.14.
 *
 */
public final class HeaderCalendarView extends RelativeLayout implements View.OnClickListener {

    public HeaderCalendarView(Context context) {
        super(context);
    }

    public HeaderCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private final void createItem(Context _context) {
        LayoutInflater mInflater = LayoutInflater.from(_context);
        View layout = mInflater.inflate(R.layout.header_view, (ViewGroup) getParent(), false);
        if (layout != null) {
            addView(layout);
            setOnClick();
        }
    }

    private void setOnClick() {
        this.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {

    }
}
