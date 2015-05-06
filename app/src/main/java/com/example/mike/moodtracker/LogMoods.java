package com.example.mike.moodtracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.text.TextUtils;
import android.util.Log;
import java.util.*;
import java.util.List;

public class LogMoods extends ActionBarActivity implements BlankFragment.OnFragmentInteractionListener {

    BlankFragment moodFragment = new BlankFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_moods);
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMoodClick(View v){

            DBaccessor dbTest = new DBaccessor(getApplicationContext());


           dbTest.addMoodToDatabase("Happy", 0);
        dbTest.addMoodToDatabase("Sad", 0);

            fragmentTransaction.replace(R.id.blankFragment, moodFragment, "LOG_SEARCH_fragment");
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();




    }

    public void onFragmentInteraction(Uri uri){

    }






}
