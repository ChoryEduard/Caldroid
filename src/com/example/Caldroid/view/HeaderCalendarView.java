package com.example.Caldroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.Caldroid.R;
import com.example.Caldroid.event.OnSetCurrent;

/**
 * Created by eduard on 12.06.14.
 *
 */
public final class HeaderCalendarView extends RelativeLayout implements OnClickListener {
    private TextView txtMonth;
    private TextView txtYear;
    private View layout;
    private OnSetCurrent onSetCurrent;

    public HeaderCalendarView(Context context) {
        super(context);
        createItem(context);
    }

    public HeaderCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createItem(context);
    }

    public HeaderCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        createItem(context);
    }


    private final void createItem(Context _context) {
        LayoutInflater mInflater = LayoutInflater.from(_context);
        layout = mInflater.inflate(R.layout.header_view, (ViewGroup) getParent(), false);
        if (layout != null) {
            txtMonth = (TextView)layout.findViewById(R.id.txtHeaderMonth);
            txtYear = (TextView)layout.findViewById(R.id.txtHeaderYear);
            addView(layout);
            setOnClick();
        }
    }

    private void setOnClick() {
        this.setOnClickListener(this);
    }

    @Override
    public final void onClick(View v) {
        onSetCurrent.onSetCurrent();
    }


    public final void setSize(final int _size) {
        layout.setMinimumWidth((int)(2.8 * _size));
        layout.setMinimumHeight((int)(0.8 * _size));
    }


    public final void setDateHeader(final String month, final String year) {
        txtMonth.setText(month);
        txtYear.setText(year);
    }

    public void setOnSetCurrent(OnSetCurrent onSetCurrent) {
        this.onSetCurrent = onSetCurrent;
    }
}
