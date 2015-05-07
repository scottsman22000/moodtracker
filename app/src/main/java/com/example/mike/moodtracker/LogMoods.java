package com.example.mike.moodtracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.*;

public class LogMoods extends ActionBarActivity implements BlankFragment.OnFragmentInteractionListener, MyMoodsList.OnFragmentInteractionListener, SearchMoodsList.OnFragmentInteractionListener, TriggerFragment.OnFragmentInteractionListener , MyTriggerList.OnFragmentInteractionListener, searchTriggerList.OnFragmentInteractionListener, BelifeFragment.OnFragmentInteractionListener, myBelifesList.OnFragmentInteractionListener, searchBelifesList.OnFragmentInteractionListener, BehaviorsFragment.OnFragmentInteractionListener, searchBehaviorsList.OnFragmentInteractionListener, MyBehaviorsList.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    // BlankFragment moodFragment = new BlankFragment();
    BlankFragment moodFragment;
    MyMoodsList myMoodsList;
    SearchMoodsList searchMoodsList;
    TriggerFragment triggerFragment;
    MyTriggerList myTriggerList;
    searchTriggerList searchTriggerList;
    myBelifesList myBelifesList;
    BelifeFragment belifeFragment;
    searchBelifesList searchBelifesList;
    BehaviorsFragment behaviorsFragment;
    searchBehaviorsList searchBehaviorsList;
    MyBehaviorsList myBehaviorsList;
    MoodData mood;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        setContentView(R.layout.activity_log_moods);
        Log.i("", "in onCreate of LogMoods???????????????????????????????????????");
        // Intent intent = getIntent();//I was supposed to add this//dont seem to need this
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_moods, menu);
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
        } else if (id == R.id.acceptButton) {
            submitMoodDataToDatabase();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMoodClick(View v) {
        moodFragment = new BlankFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(moodFragment);

        //fragmentTransaction.add(R.id.frameLayout, moodFragment);
        fragmentTransaction.replace(R.id.frameLayout, moodFragment);


        fragmentTransaction.commit();
    }

    public MoodData getMoodData() {
        return mood;
    }

    public void setMoodData(MoodData d) {
        mood = d;
    }
    public void onTriggerClick(View v){
        triggerFragment = new TriggerFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, triggerFragment);
        fragmentTransaction.commit();
    }

    public void onMyMoodsClick(View v) {
        Log.i("", "In clickedMyMoods() of LogMoods");
        myMoodsList = new MyMoodsList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,myMoodsList);
        fragmentTransaction.commit();
    }

    public void onMyTriggerListClick(View v){
        myTriggerList = new MyTriggerList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, myTriggerList);
        fragmentTransaction.commit();
    }

    public void onSearchMoodsClick(View v){
        searchMoodsList = new SearchMoodsList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,searchMoodsList);
        fragmentTransaction.commit();
    }

    public void onSearchTriggersClick(View v){
        searchTriggerList = new searchTriggerList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, searchTriggerList);
        fragmentTransaction.commit();
    }

    public void onBelifeClick(View v){
        belifeFragment = new BelifeFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, belifeFragment);
        fragmentTransaction.commit();
    }

    public void onMyBelifeClick(View v){
        myBelifesList = new myBelifesList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, myBelifesList);
        fragmentTransaction.commit();
    }

    public void onSearchBelifesListClicked(View v){
        searchBelifesList = new searchBelifesList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, searchBelifesList);
        fragmentTransaction.commit();
    }

    public void onBehviorClick(View v){
        behaviorsFragment = new BehaviorsFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, behaviorsFragment);
        fragmentTransaction.commit();
    }

    public void onMyBehaviorClick(View v){
        myBehaviorsList = new MyBehaviorsList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,myBehaviorsList);
        fragmentTransaction.commit();
    }

    public void onSearchBehviorClick(View v){
        searchBehaviorsList = new searchBehaviorsList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, searchBehaviorsList);
        fragmentTransaction.commit();

    }




    public void onFragmentInteraction(Uri uri){

    }


    public void submitMoodDataToDatabase() {
        String moodData = "";
        String triggerData = "";
        String behaviorData = "";
        String beliefData = "";
        if (mood != null) {
            if (mood.mood != null) {
                if (mood.mood.mood != null) {
                    moodData = mood.mood.mood;
                } else {
                    moodData = "";
                }
            } else {
                moodData = "";
            }

            if (mood.trigger != null) {
                if (mood.trigger.trigger != null) {
                    triggerData = mood.trigger.trigger;
                } else {
                    triggerData = "";
                }
            } else {
                triggerData = "";
            }

            if (mood.behavior != null) {
                if (mood.behavior.behavior != null) {
                    behaviorData = mood.behavior.behavior;
                } else {
                    behaviorData = "";
                }
            } else {
                behaviorData = "";
            }

            if (mood.belief != null) {
                if (mood.belief.belief != null) {
                    beliefData = mood.belief.belief;
                } else {
                    beliefData = "";
                }
            } else {
                beliefData = "";
            }
        }


        if (mood != null) {
            if (mood.mood == null) {
                new AlertDialog.Builder(this).setTitle("Invalid Mood Entry")
                        .setMessage("There must be a mood associated with an entry!") //mood.trigger.trigger, mood.behavior.behavior, mood.belief.belief))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                new AlertDialog.Builder(this).setTitle("Mood Data Added")
                        .setMessage(String.format("The following data has been entered: Mood: %s\nTrigger: %s\nBehavior: %s\nBelief: %s", moodData, triggerData, behaviorData, beliefData)) //mood.trigger.trigger, mood.behavior.behavior, mood.belief.belief))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                // mood = new MoodData();
                                Intent myIntent = new Intent(LogMoods.this, MainActivity.class);
                                LogMoods.this.startActivity(myIntent);

                                Intent intent = new Intent(LogMoods.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }


        }
    }



}
