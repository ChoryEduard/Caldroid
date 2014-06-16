package com.example.Caldroid.event;

/**
 * Created by eduard on 16.06.14.
 *
 */
public interface OnScrolling {

    public void OnScrollUp();
    public void OnScrollDown();
    public int getSizeCalendar();
    public int getOffsetPixel();
}
