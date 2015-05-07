package com.example.mike.moodtracker;

import java.util.*;

import android.app.Activity;
import android.content.Context;

public class GraphBuilder extends Activity{

    ArrayList<MoodData> moodData;
    Date startTime;
    Date endTime;
    private Context context;
    private DBaccessor dba;

    public GraphBuilder(Context context) {
        this.context = context;
        this.dba = new DBaccessor(context);
    }

    public MoodData getData(Date startTime, Date endTime, int graphType, Mood mood){
        //pull mood data between two times and of matching mood and stores them in mood data
        ArrayList<MoodData> moodData = null;//import data
        //filter data by mood
        for (MoodData m : moodData) {
            if (!m.mood.mood.equals(mood.mood))
                moodData.remove(m);
        }
        this.moodData = moodData;
        this.startTime = startTime;
        this.endTime = endTime;
        return null;
    }

    public void graphPoints(){
        //graph points
        long range = endTime.getTime() - startTime.getTime();
        ArrayList<Float> horizontalPosition = new ArrayList<Float>();
        for (MoodData m : moodData) {
            horizontalPosition.add((float) ((m.timeStamp.getTimeInMillis() - startTime.getTime()) / range));
        }
        //horizontal positions are stored in horizontalPosition from 0.0 to 1.0
        //vertical positions are stored in moodData.mood.intensity from 0.0 to 1.0
        //find a graphing tool that lets you do this
    }
}
