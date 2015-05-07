package com.example.mike.moodtracker;

import java.util.*;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

public class HomeScreen extends Activity{

    private Context context;

    public GraphBuilder createGraphBuilder(){
        GraphBuilder graph = new GraphBuilder(context);
        return graph;
    }

    public MoodBuilder createMoodBuilder(){
        MoodBuilder mood = new MoodBuilder();
        return mood;
    }

    public TableBuilder createTableBuilder(){
        TableBuilder table = new TableBuilder();
        return table;
    }

    public HelpBuilder createHelpBuilder(){
        HelpBuilder help = new HelpBuilder();
        return help;
    }

    public PrefEditor createPrefEditor(){
        PrefEditor prefs = new PrefEditor();
        return prefs;
    }

    public String appHelp(){
        String help = null;
        return help;
    }
}
