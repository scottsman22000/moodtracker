package com.example.mike.moodtracker;

import java.util.*;

import android.app.Activity;

public class HelpBuilder extends Activity{

    CopingStrategy[] copingStrategies;

    public HelpBuilder() {
        //TODO grab copingStrategies from database
    }
    public void rateStrategy(CopingStrategy copingStrategy, int rating){
        copingStrategy.rating = rating;
        //may have to slightly change method parameters
        //TODO set copingStrategy and send back to database
    }

    public CopingStrategy[] getCopingStrategies() {
        return copingStrategies;
    }
}
