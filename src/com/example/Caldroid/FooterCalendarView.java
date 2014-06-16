package com.example.Caldroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by eduard on 15.06.14.
 * footer Calendar
 * In have
 * refresh, +new, smile =)
 */
public class FooterCalendarView extends RelativeLayout implements OnClickListener {

    private View layout;

    public FooterCalendarView(Context context) {
        super(context);
        createItem(context);
    }

    public FooterCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createItem(context);
    }

    public FooterCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        createItem(context);
    }


    private final void createItem(Context _context) {
        LayoutInflater mInflater = LayoutInflater.from(_context);
        layout = mInflater.inflate(R.layout.footer_view, (ViewGroup) getParent(), false);
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


    public final void setSize(final int _size) {
        layout.setMinimumWidth((int)(2.8 * _size));
        layout.setMinimumHeight(_size);
    }
}
