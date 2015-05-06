package com.example.mike.moodtracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.*;
import java.util.List;

import android.util.Log;

public class MainActivity extends ActionBarActivity {
    public static DBaccessor d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    this.deleteDatabase("mooddb5.db");
        setUpDatabase();
    }
    public void setUpDatabase(){
        DBaccessor d = new DBaccessor(this.getApplicationContext());
        /*Add moods to database for stuff*/
        d.addMoodToDatabase("Happy", 1);
        d.addMoodToDatabase("Sad", 2);
        d.addMoodToDatabase("Angry", 3);
        d.addMoodToDatabase("Tender", 4); //?
        d.addMoodToDatabase("Scared", 5);
        d.addMoodToDatabase("Excited", 6);
        d.addMoodToDatabase("Elated", 1);
        d.addMoodToDatabase("Upset", 2);
        d.addMoodToDatabase("Mad", 3);
        d.addMoodToDatabase("Emotional", 4); //?
        d.addMoodToDatabase("Afraid", 5);
        d.addMoodToDatabase("Thrilled", 6);
        //add triggers

        d.addTriggerToDatabase("Dog Died", 2);
        d.addTriggerToDatabase("Video Games", 6);
        d.addTriggerToDatabase("Darkness", 5);
        d.addTriggerToDatabase("New born", 4);
        d.addTriggerToDatabase("Stupidity", 3);
        d.addTriggerToDatabase("Day Off", 1);
        d.addTriggerToDatabase("Aunt died", 2);
        d.addTriggerToDatabase("New Toy", 6);
        d.addTriggerToDatabase("Clowns", 5);
        d.addTriggerToDatabase("Birthday Hug", 4);
        d.addTriggerToDatabase("Flat Tire", 3);
        d.addTriggerToDatabase("BBQ", 1);

        //add behavors
        d.addBehaviorToDatabase("Smile", 1);
        d.addBehaviorToDatabase("Frown", 2);
        d.addBehaviorToDatabase("Pout", 3);
        d.addBehaviorToDatabase("Hug", 4);
        d.addBehaviorToDatabase("Hide", 5);
        d.addBehaviorToDatabase("Heart Races", 6);
        d.addBehaviorToDatabase("Jump for joy", 1);
        d.addBehaviorToDatabase("Cry", 2);
        d.addBehaviorToDatabase("Throw Tantrum", 3);
        d.addBehaviorToDatabase("Weep", 4);
        d.addBehaviorToDatabase("Run Away", 5);
        d.addBehaviorToDatabase("Pace around", 6);

        //add beliefs
        d.addBeliefToDatabase("Always happy", 1);
        d.addBeliefToDatabase("Always sad", 2);
        d.addBeliefToDatabase("Always angry", 3);
        d.addBeliefToDatabase("Always tender", 4);
        d.addBeliefToDatabase("Always scared", 5);
        d.addBeliefToDatabase("Always excited", 6);
        d.addBeliefToDatabase("Forever happy", 1);
        d.addBeliefToDatabase("Forever sad", 2);
        d.addBeliefToDatabase("Forever angry", 3);
        d.addBeliefToDatabase("Forever tender", 4);
        d.addBeliefToDatabase("Forever scared", 5);
        d.addBeliefToDatabase("Forever excited", 6);


        //add test moodData


        for (int x = 0; x < 1000; x++) {
            Random r = new Random();
            List<String> moods = d.getAllMoods();

            String mood = moods.get(r.nextInt(moods.size()));
            String trigger;
            String belief;
            String behavior;

            int coping = d.getCopingIDFromMood(mood);

            int triggerCheck = r.nextInt(100);

            if (triggerCheck >= 25) { //75% probability to get trigger
                List<String> pTriggers = d.getTriggerFromCopingID(coping);
                trigger = pTriggers.get(r.nextInt(pTriggers.size()));
            } else {
                trigger = null;
            }

            int beliefCheck = r.nextInt(100);

            if (beliefCheck <= 30) {
                List<String> pBeliefs = d.getBeliefsFromCopingID(coping);
                belief = pBeliefs.get(r.nextInt(pBeliefs.size()));
            } else {
                belief = null;
            }

            int behaviorCheck = r.nextInt(100);

            if (behaviorCheck <= 20) {
                List<String> pBehaviors = d.getBehaviorFromCopingID(coping);
                behavior = pBehaviors.get(r.nextInt(pBehaviors.size()));
            } else {
                behavior = null;
            }

            double moodIntensity = Math.random() * 0.99;

            d.insertMoodData(mood, trigger, belief, behavior, moodIntensity, null, null, null, null);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createLogMoodsPage(View view){
        Intent intent = new Intent(this, LogMoods.class);//i need to add the kind of activity that i want
        startActivity(intent);
    }

    public void createFindPatternsPage(View view){
        Intent intent = new Intent(this, FindPatterns.class);//i need to add the kind of activity that i want
        startActivity(intent);
    }

}
