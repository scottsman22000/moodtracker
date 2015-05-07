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
import android.widget.Button;

/**
 * Created by Mahoney on 5/5/2015.
 */
public class FindPatterns extends ActionBarActivity implements BlankFragment.OnFragmentInteractionListener, MoodsList.OnFragmentInteractionListener, TriggerFragment.OnFragmentInteractionListener, TriggersList.OnFragmentInteractionListener {

    BlankFragment moodFragment;
    TriggerFragment triggerFragment;
    BelifeFragment beliefFragment;
    BehaviorsFragment behaviorFragment;
    MoodsList MoodsList;
    TriggersList triggersList;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        setContentView(R.layout.activity_find_patterns);
        Log.i("", "in onCreate of FindPatterns??????????????????????????????????????");
        // Intent intent = getIntent();//I was supposed to add this//dont seem to need this
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_find_patterns, menu);
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

    public void onMoodClick(View v){
        moodFragment = new BlankFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(moodFragment);

        //fragmentTransaction.add(R.id.frameLayout, moodFragment);
        fragmentTransaction.replace(R.id.frameLayout, moodFragment);

        fragmentTransaction.commit();
    }

    public void onTriggerClick(View v){
        triggerFragment = new TriggerFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, triggerFragment);
        fragmentTransaction.commit();
    }


    public void onMyMoodsClick(View v) {
        Log.i("", "In clickedMoods() of FindPatterns");
        MoodsList = new MoodsList();
        fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.remove(moodFragment);

        //fragmentTransaction.add(R.id.frameLayout, myMoodsList);
        fragmentTransaction.replace(R.id.frameLayout,MoodsList);
        fragmentTransaction.commit();
    }

    public void onTableClick(View v){
        Log.i("", "clicked table in findpatterns");
        Intent intent = new Intent(this, TableBuilder.class);//i need to add the kind of activity that i want
        startActivity(intent);
    }

    public void onMyTriggerListClick(View v){
        triggersList = new TriggersList();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, triggersList);
        fragmentTransaction.commit();
    }

    public void onFragmentInteraction(Uri uri){
        Log.i("","We are here in the activity...yay");
    }


}
