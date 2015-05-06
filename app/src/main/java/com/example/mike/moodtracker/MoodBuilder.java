package com.example.mike.moodtracker;

import java.util.*;

public class MoodBuilder extends Activity{

    public MoodData moodData;

    public void createMoodData(Mood mood){
        moodData.mood = mood;
    }

    public void addTrigger(Trigger trigger){
        moodData.trigger = trigger;
    }

    public void addBelief(Belief belief){
        moodData.belief = belief;
    }

    public void addBehavior(Behavior behavior){
        moodData.behavior = behavior;
    }

    public void submitMoodData(){
        //submit mood data to database
    }

}
