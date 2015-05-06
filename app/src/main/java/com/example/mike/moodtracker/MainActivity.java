package com.example.mike.moodtracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;


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
        d.addMoodToDatabase("Angry",3);
        d.addMoodToDatabase("Tender", 4); //?
        d.addBeliefToDatabase("Scared", 5);
        d.addMoodToDatabase("Excited", 6);

        //add triggers
        d.addTriggerToDatabase("Dog Died", 2);
        d.addTriggerToDatabase("Video Games", 6);
        d.addTriggerToDatabase("Darkness", 5);
        d.addTriggerToDatabase("New born", 4);
        d.addTriggerToDatabase("Stupidity", 3);
        d.addTriggerToDatabase("Day Off", 1);

        //add behavors
        d.addBehaviorToDatabase("Smile", 1);
        d.addBehaviorToDatabase("Frown", 2);
        d.addBehaviorToDatabase("Pout", 3);
        d.addBehaviorToDatabase("Hug", 4);
        d.addBehaviorToDatabase("Hide", 5);
        d.addBehaviorToDatabase("Heart Races", 6);

        //add beliefs
        d.addBeliefToDatabase("Always happy", 1);
        d.addBeliefToDatabase("Always sad", 2);
        d.addBeliefToDatabase("Always angry",3);
        d.addBeliefToDatabase("Always tender", 4);
        d.addBeliefToDatabase("Always scared", 5);
        d.addBeliefToDatabase("Always excited", 6);





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
}
