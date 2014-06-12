package com.example.Caldroid.dataobject;

import java.util.Date;

/**
 * Created by BruSD on 6/12/2014.
 */
public class Day {
    private Date date;
    private String imgURL;
    public Day(long dateInMilliseconds , String imgURL){
        this.date = new Date(dateInMilliseconds);
        this.imgURL = imgURL;
    }


}
