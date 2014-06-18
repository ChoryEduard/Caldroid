package com.example.Caldroid.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.Caldroid.R;
import com.example.Caldroid.dateHelper.Day;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BruSD on 6/16/2014.
 */
public class AdapterCalebdroid extends BaseAdapter {
    private Context context;
    private ArrayList<Day>  data;
    private int mColumnWidth;
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     *
     */
    public AdapterCalebdroid(Context context, ArrayList<Day> data,  int _columnWidth) {

        this.context = context;
        this.data = data;
        this.mColumnWidth = _columnWidth;


    }

    @Override
    public int getCount() {
        return data.size() + data.get(0).getWeekDay();
    }

    @Override
    public Object getItem(int position) {
        return getDateItem(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Day day;
        View layout;
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.calendar_item, parent,false);
        layout.setLayoutParams(new GridView.LayoutParams(mColumnWidth, mColumnWidth));
        final ImageView imgItem = (ImageView)layout.findViewById(R.id.imgCalItem);
        TextView txtItem = (TextView)layout.findViewById(R.id.tvDateCalItem);
        day = getDateItem(position);
        if (day != null) {
            txtItem.setText(String.valueOf(day.Day));
            imgItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(context).load(day.imgURL).into(imgItem);

            Transformation transformation = new Transformation() {

                @Override
                public Bitmap transform(Bitmap source) {
                    int targetWidth = imgItem.getWidth();
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                    if (result != source) {
                        source.recycle();
                    }
                    return result;
                }

                @Override
                public String key() {
                    return "transformation" + " desiredWidth";
                }
            };

            String mMessage_pic_url = day.imgURL;

            Picasso.with(context)
                    .load(mMessage_pic_url)
                    .error(android.R.drawable.stat_notify_error)
                    .transform(transformation)
                    .into(imgItem);
//            isTransperent(imgItem, i);
        }
        return  layout;
    }

    private final Day getDateItem(final int pos) {
        final int offset = data.get(0).getWeekDay();
        if (pos < offset) return null;
        return data.get(pos - offset);

    }

    public final void addToEnd(ArrayList<Day> listToAdd){
        for (int i = 0; i < listToAdd.size(); i++ ){
            data.add(listToAdd.get(i));
        }



    }
    public final void removeFromStart( ArrayList<Day> listToRemove){
        for (int i = 0 ; i < listToRemove.size(); i++){
            data.remove(i);
        }
    }
}
