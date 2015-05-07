package com.example.mike.moodtracker;

/**
 * Created by Michael on 5/5/2015.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.*;
import java.text.*;


public class TableBuilder extends ActionBarActivity {
    private static final int NUMBER_OF_MAXIMUMS = 3;
    private static final DecimalFormat df = new DecimalFormat("%##0.00");
    private static ArrayList<MoodData> fakeData = new ArrayList<MoodData>();
    private Context context;
    private DBaccessor dba;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_builder);
        Log.i("", "in onCreate of FindPatterns??????????????????????????????????????");
        // Intent intent = getIntent();//I was supposed to add this//dont seem to need this
        if (FindPatterns.mood != null)
            new TableBuilder(getApplicationContext()).buildTable(new GregorianCalendar(1970, 1, 1), new GregorianCalendar(), FindPatterns.mood);
        else if (FindPatterns.trigger != null)
            new TableBuilder(getApplicationContext()).buildTable(new GregorianCalendar(1970, 1, 1), new GregorianCalendar(), FindPatterns.trigger);

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

    private static class Duple implements Comparable<Duple> {
        Integer index;
        Integer value;

        public Duple(Integer i, Integer v) {
            index = i;
            value = v;
        }

        public int compareTo(Duple d) {
            if (value > d.value)
                return 1;
            if (value < d.value)
                return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
//        //test
//        for (int i = 0; i < 10000; i++) {
//            MoodData m = new MoodData(new Mood("" + Math.floor(Math.random() * 5), 1.0f, ""));
//            m.trigger = new Trigger("" + Math.floor(Math.random() * 5), "");
//            m.behavior = new Behavior("" + Math.floor(Math.random() * 5), "");
//            m.belief = new Belief("" + Math.floor(Math.random() * 5), "");
//            fakeData.add(m);
//        }
        //new TableBuilder().buildTable(null, null, new Mood("Tender", 0, ""));
        GregorianCalendar g = new GregorianCalendar();
        System.err.print(buildString(g));
    }

    public TableBuilder(){

    }

    public TableBuilder(Context context) {
        this.context = context;
        this.dba = new DBaccessor(context);
    }

    public String buildTable(GregorianCalendar startTime, GregorianCalendar endTime, Mood mood) {
        ArrayList<MoodData> moodData = new ArrayList<MoodData>(dba.getMoodDataBetweenDates(buildString(startTime), buildString(endTime)));
        String output = "";
        //filter
        for (MoodData m : moodData) {
            if (!m.mood.mood.equals(mood.mood))
                moodData.remove(m);
        }
        //endfilter
        /*//mood
        Hashtable<String, Integer> moodHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer mood;
            if(m.mood.mood != null)
                if ((mood = moodHash.get(m.mood.mood)) == null)
                   moodHash.put(m.mood.mood, 1);
                 else
                   moodHash.put(m.mood.mood, mood + 1);
        }
        ArrayList<String> moods = new ArrayList<>(moodHash.keySet());
        ArrayList<Integer> moodValues = new ArrayList<>(moodHash.values());

        ArrayList<Duple> moodMax = new ArrayList<>();
        for (int i = 0; i < moodValues.size(); i++) {
            if (moodMax.size() < NUMBER_OF_MAXIMUMS) {
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            } else if (moodMax.get(0).value < moodValues.get(i)) {
                moodMax.remove(0);
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            }
        }
        output += "Moods:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += moods.get(moodMax.get(i).index) + ": " + df.format(moodValues.get(moodMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end mood*/
        //trigger
        Hashtable<String, Integer> triggerHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer trigger;
            if (m.trigger.trigger != null)
                if ((trigger = triggerHash.get(m.trigger.trigger)) == null)
                    triggerHash.put(m.trigger.trigger, 1);
                else
                    triggerHash.put(m.trigger.trigger, trigger + 1);
        }
        ArrayList<String> triggers = new ArrayList<>(triggerHash.keySet());
        ArrayList<Integer> triggerValues = new ArrayList<>(triggerHash.values());

        ArrayList<Duple> triggerMax = new ArrayList<>();
        for (int i = 0; i < triggerValues.size(); i++) {
            if (triggerMax.size() < NUMBER_OF_MAXIMUMS) {
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            } else if (triggerMax.get(0).value < triggerValues.get(i)) {
                triggerMax.remove(0);
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            }
        }
        output += "Triggers:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += triggers.get(triggerMax.get(i).index) + ": " + df.format(triggerValues.get(triggerMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end trigger
        //belief
        Hashtable<String, Integer> beliefHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer belief;
            if (m.belief.belief != null)
                if ((belief = beliefHash.get(m.belief.belief)) == null)
                    beliefHash.put(m.belief.belief, 1);
                else
                    beliefHash.put(m.belief.belief, belief + 1);
        }
        ArrayList<String> beliefs = new ArrayList<>(beliefHash.keySet());
        ArrayList<Integer> beliefValues = new ArrayList<>(beliefHash.values());

        ArrayList<Duple> beliefMax = new ArrayList<>();
        for (int i = 0; i < beliefValues.size(); i++) {
            if (beliefMax.size() < NUMBER_OF_MAXIMUMS) {
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            } else if (beliefMax.get(0).value < beliefValues.get(i)) {
                beliefMax.remove(0);
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            }
        }
        output += "Beliefs:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += beliefs.get(beliefMax.get(i).index) + ": " + df.format(beliefValues.get(beliefMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end belief
        //behavior
        Hashtable<String, Integer> behaviorHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer behavior;
            if (m.behavior.behavior != null)
                if ((behavior = behaviorHash.get(m.behavior.behavior)) == null)
                    behaviorHash.put(m.behavior.behavior, 1);
                else
                    behaviorHash.put(m.behavior.behavior, behavior + 1);
        }
        ArrayList<String> behaviors = new ArrayList<>(behaviorHash.keySet());
        ArrayList<Integer> behaviorValues = new ArrayList<>(behaviorHash.values());

        ArrayList<Duple> behaviorMax = new ArrayList<>();
        for (int i = 0; i < behaviorValues.size(); i++) {
            if (behaviorMax.size() < NUMBER_OF_MAXIMUMS) {
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            } else if (behaviorMax.get(0).value < behaviorValues.get(i)) {
                behaviorMax.remove(0);
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            }
        }
        output += "Behaviors:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += behaviors.get(behaviorMax.get(i).index) + ": " + df.format(behaviorValues.get(behaviorMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end behavior


        return (output);

    }

    public String buildTable(GregorianCalendar startTime, GregorianCalendar endTime, Trigger trigger) {
        ArrayList<MoodData> moodData = new ArrayList<MoodData>(dba.getMoodDataBetweenDates(buildString(startTime), buildString(endTime)));
        String output = "";
        //filter
        for (MoodData m : moodData) {
            if (!m.trigger.trigger.equals(trigger.trigger))
                moodData.remove(m);
        }
        //endfilter
        //mood
        Hashtable<String, Integer> moodHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer mood;
            if (m.mood.mood != null)
                if ((mood = moodHash.get(m.mood.mood)) == null)
                    moodHash.put(m.mood.mood, 1);
                else
                    moodHash.put(m.mood.mood, mood + 1);
        }
        ArrayList<String> moods = new ArrayList<>(moodHash.keySet());
        ArrayList<Integer> moodValues = new ArrayList<>(moodHash.values());

        ArrayList<Duple> moodMax = new ArrayList<>();
        for (int i = 0; i < moodValues.size(); i++) {
            if (moodMax.size() < NUMBER_OF_MAXIMUMS) {
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            } else if (moodMax.get(0).value < moodValues.get(i)) {
                moodMax.remove(0);
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            }
        }
        output += "Moods:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += moods.get(moodMax.get(i).index) + ": " + df.format(moodValues.get(moodMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end mood
        /*//trigger
        Hashtable<String, Integer> triggerHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer trigger;
            if (m.trigger.trigger != null)
                if ((trigger = triggerHash.get(m.trigger.trigger)) == null)
                    triggerHash.put(m.trigger.trigger, 1);
                else
                    triggerHash.put(m.trigger.trigger, trigger + 1);
        }
        ArrayList<String> triggers = new ArrayList<>(triggerHash.keySet());
        ArrayList<Integer> triggerValues = new ArrayList<>(triggerHash.values());

        ArrayList<Duple> triggerMax = new ArrayList<>();
        for (int i = 0; i < triggerValues.size(); i++) {
            if (triggerMax.size() < NUMBER_OF_MAXIMUMS) {
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            } else if (triggerMax.get(0).value < triggerValues.get(i)) {
                triggerMax.remove(0);
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            }
        }
        output += "Triggers:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += triggers.get(triggerMax.get(i).index) + ": " + df.format(triggerValues.get(triggerMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end trigger*/
        //belief
        Hashtable<String, Integer> beliefHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer belief;
            if (m.belief.belief != null)
                if ((belief = beliefHash.get(m.belief.belief)) == null)
                    beliefHash.put(m.belief.belief, 1);
                else
                    beliefHash.put(m.belief.belief, belief + 1);
        }
        ArrayList<String> beliefs = new ArrayList<>(beliefHash.keySet());
        ArrayList<Integer> beliefValues = new ArrayList<>(beliefHash.values());

        ArrayList<Duple> beliefMax = new ArrayList<>();
        for (int i = 0; i < beliefValues.size(); i++) {
            if (beliefMax.size() < NUMBER_OF_MAXIMUMS) {
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            } else if (beliefMax.get(0).value < beliefValues.get(i)) {
                beliefMax.remove(0);
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            }
        }
        output += "Beliefs:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += beliefs.get(beliefMax.get(i).index) + ": " + df.format(beliefValues.get(beliefMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end belief
        //behavior
        Hashtable<String, Integer> behaviorHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer behavior;
            if (m.behavior.behavior != null)
                if ((behavior = behaviorHash.get(m.behavior.behavior)) == null)
                    behaviorHash.put(m.behavior.behavior, 1);
                else
                    behaviorHash.put(m.behavior.behavior, behavior + 1);
        }
        ArrayList<String> behaviors = new ArrayList<>(behaviorHash.keySet());
        ArrayList<Integer> behaviorValues = new ArrayList<>(behaviorHash.values());

        ArrayList<Duple> behaviorMax = new ArrayList<>();
        for (int i = 0; i < behaviorValues.size(); i++) {
            if (behaviorMax.size() < NUMBER_OF_MAXIMUMS) {
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            } else if (behaviorMax.get(0).value < behaviorValues.get(i)) {
                behaviorMax.remove(0);
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            }
        }
        output += "Behaviors:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += behaviors.get(behaviorMax.get(i).index) + ": " + df.format(behaviorValues.get(behaviorMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end behavior


        return (output);


    }

    public String buildTable(GregorianCalendar startTime, GregorianCalendar endTime, Belief belief) {
        ArrayList<MoodData> moodData = new ArrayList<MoodData>(dba.getMoodDataBetweenDates(buildString(startTime), buildString(endTime)));
        String output = "";
        //filter
        for (MoodData m : moodData) {
            if (!m.belief.belief.equals(belief.belief))
                moodData.remove(m);
        }
        //endfilter
        //mood
        Hashtable<String, Integer> moodHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer mood;
            if (m.mood.mood != null)
                if ((mood = moodHash.get(m.mood.mood)) == null)
                    moodHash.put(m.mood.mood, 1);
                else
                    moodHash.put(m.mood.mood, mood + 1);
        }
        ArrayList<String> moods = new ArrayList<>(moodHash.keySet());
        ArrayList<Integer> moodValues = new ArrayList<>(moodHash.values());

        ArrayList<Duple> moodMax = new ArrayList<>();
        for (int i = 0; i < moodValues.size(); i++) {
            if (moodMax.size() < NUMBER_OF_MAXIMUMS) {
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            } else if (moodMax.get(0).value < moodValues.get(i)) {
                moodMax.remove(0);
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            }
        }
        output += "Moods:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += moods.get(moodMax.get(i).index) + ": " + df.format(moodValues.get(moodMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end mood
        //trigger
        Hashtable<String, Integer> triggerHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer trigger;
            if (m.trigger.trigger != null)
                if ((trigger = triggerHash.get(m.trigger.trigger)) == null)
                    triggerHash.put(m.trigger.trigger, 1);
                else
                    triggerHash.put(m.trigger.trigger, trigger + 1);
        }
        ArrayList<String> triggers = new ArrayList<>(triggerHash.keySet());
        ArrayList<Integer> triggerValues = new ArrayList<>(triggerHash.values());

        ArrayList<Duple> triggerMax = new ArrayList<>();
        for (int i = 0; i < triggerValues.size(); i++) {
            if (triggerMax.size() < NUMBER_OF_MAXIMUMS) {
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            } else if (triggerMax.get(0).value < triggerValues.get(i)) {
                triggerMax.remove(0);
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            }
        }
        output += "Triggers:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += triggers.get(triggerMax.get(i).index) + ": " + df.format(triggerValues.get(triggerMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end trigger
        /*//belief
        Hashtable<String, Integer> beliefHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer belief;
            if (m.belief.belief != null)
                if ((belief = beliefHash.get(m.belief.belief)) == null)
                    beliefHash.put(m.belief.belief, 1);
                else
                    beliefHash.put(m.belief.belief, belief + 1);
        }
        ArrayList<String> beliefs = new ArrayList<>(beliefHash.keySet());
        ArrayList<Integer> beliefValues = new ArrayList<>(beliefHash.values());

        ArrayList<Duple> beliefMax = new ArrayList<>();
        for (int i = 0; i < beliefValues.size(); i++) {
            if (beliefMax.size() < NUMBER_OF_MAXIMUMS) {
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            } else if (beliefMax.get(0).value < beliefValues.get(i)) {
                beliefMax.remove(0);
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            }
        }
        output += "Beliefs:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += beliefs.get(beliefMax.get(i).index) + ": " + df.format(beliefValues.get(beliefMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end belief*/
        //behavior
        Hashtable<String, Integer> behaviorHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer behavior;
            if (m.behavior.behavior != null)
                if ((behavior = behaviorHash.get(m.behavior.behavior)) == null)
                    behaviorHash.put(m.behavior.behavior, 1);
                else
                    behaviorHash.put(m.behavior.behavior, behavior + 1);
        }
        ArrayList<String> behaviors = new ArrayList<>(behaviorHash.keySet());
        ArrayList<Integer> behaviorValues = new ArrayList<>(behaviorHash.values());

        ArrayList<Duple> behaviorMax = new ArrayList<>();
        for (int i = 0; i < behaviorValues.size(); i++) {
            if (behaviorMax.size() < NUMBER_OF_MAXIMUMS) {
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            } else if (behaviorMax.get(0).value < behaviorValues.get(i)) {
                behaviorMax.remove(0);
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            }
        }
        output += "Behaviors:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += behaviors.get(behaviorMax.get(i).index) + ": " + df.format(behaviorValues.get(behaviorMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end behavior


        return (output);

    }

    public String buildTable(GregorianCalendar startTime, GregorianCalendar endTime, Behavior behavior) {
        ArrayList<MoodData> moodData = new ArrayList<MoodData>(dba.getMoodDataBetweenDates(buildString(startTime), buildString(endTime)));
        String output = "";
        //filter
        for (MoodData m : moodData) {
            if (!m.behavior.behavior.equals(behavior.behavior))
                moodData.remove(m);
        }
        //endfilter
        //mood
        Hashtable<String, Integer> moodHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer mood;
            if (m.mood.mood != null)
                if ((mood = moodHash.get(m.mood.mood)) == null)
                    moodHash.put(m.mood.mood, 1);
                else
                    moodHash.put(m.mood.mood, mood + 1);
        }
        ArrayList<String> moods = new ArrayList<>(moodHash.keySet());
        ArrayList<Integer> moodValues = new ArrayList<>(moodHash.values());

        ArrayList<Duple> moodMax = new ArrayList<>();
        for (int i = 0; i < moodValues.size(); i++) {
            if (moodMax.size() < NUMBER_OF_MAXIMUMS) {
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            } else if (moodMax.get(0).value < moodValues.get(i)) {
                moodMax.remove(0);
                moodMax.add(new Duple(i, moodValues.get(i)));
                Collections.sort(moodMax);
            }
        }
        output += "Moods:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += moods.get(moodMax.get(i).index) + ": " + df.format(moodValues.get(moodMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end mood
        //trigger
        Hashtable<String, Integer> triggerHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer trigger;
            if (m.trigger.trigger != null)
                if ((trigger = triggerHash.get(m.trigger.trigger)) == null)
                    triggerHash.put(m.trigger.trigger, 1);
                else
                    triggerHash.put(m.trigger.trigger, trigger + 1);
        }
        ArrayList<String> triggers = new ArrayList<>(triggerHash.keySet());
        ArrayList<Integer> triggerValues = new ArrayList<>(triggerHash.values());

        ArrayList<Duple> triggerMax = new ArrayList<>();
        for (int i = 0; i < triggerValues.size(); i++) {
            if (triggerMax.size() < NUMBER_OF_MAXIMUMS) {
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            } else if (triggerMax.get(0).value < triggerValues.get(i)) {
                triggerMax.remove(0);
                triggerMax.add(new Duple(i, triggerValues.get(i)));
                Collections.sort(triggerMax);
            }
        }
        output += "Triggers:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += triggers.get(triggerMax.get(i).index) + ": " + df.format(triggerValues.get(triggerMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end trigger
        //belief
        Hashtable<String, Integer> beliefHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer belief;
            if (m.belief.belief != null)
                if ((belief = beliefHash.get(m.belief.belief)) == null)
                    beliefHash.put(m.belief.belief, 1);
                else
                    beliefHash.put(m.belief.belief, belief + 1);
        }
        ArrayList<String> beliefs = new ArrayList<>(beliefHash.keySet());
        ArrayList<Integer> beliefValues = new ArrayList<>(beliefHash.values());

        ArrayList<Duple> beliefMax = new ArrayList<>();
        for (int i = 0; i < beliefValues.size(); i++) {
            if (beliefMax.size() < NUMBER_OF_MAXIMUMS) {
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            } else if (beliefMax.get(0).value < beliefValues.get(i)) {
                beliefMax.remove(0);
                beliefMax.add(new Duple(i, beliefValues.get(i)));
                Collections.sort(beliefMax);
            }
        }
        output += "Beliefs:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += beliefs.get(beliefMax.get(i).index) + ": " + df.format(beliefValues.get(beliefMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end belief
        /*//behavior
        Hashtable<String, Integer> behaviorHash = new Hashtable<>();
        for (MoodData m : moodData) {
            Integer behavior;
            if (m.behavior.behavior != null)
                if ((behavior = behaviorHash.get(m.behavior.behavior)) == null)
                    behaviorHash.put(m.behavior.behavior, 1);
                else
                    behaviorHash.put(m.behavior.behavior, behavior + 1);
        }
        ArrayList<String> behaviors = new ArrayList<>(behaviorHash.keySet());
        ArrayList<Integer> behaviorValues = new ArrayList<>(behaviorHash.values());

        ArrayList<Duple> behaviorMax = new ArrayList<>();
        for (int i = 0; i < behaviorValues.size(); i++) {
            if (behaviorMax.size() < NUMBER_OF_MAXIMUMS) {
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            } else if (behaviorMax.get(0).value < behaviorValues.get(i)) {
                behaviorMax.remove(0);
                behaviorMax.add(new Duple(i, behaviorValues.get(i)));
                Collections.sort(behaviorMax);
            }
        }
        output += "Behaviors:\n";
        for (int i = NUMBER_OF_MAXIMUMS - 1; i >= 0; i--) {
            output += behaviors.get(behaviorMax.get(i).index) + ": " + df.format(behaviorValues.get(behaviorMax.get(i).index) / (float) moodData.size()) + "\n";
        }
        output += "\n";
        //end behavior*/


        return (output);

    }

    private static String buildString(GregorianCalendar g) {
        String year = Integer.toString(g.get(GregorianCalendar.YEAR));
        String month = Integer.toString(g.get(GregorianCalendar.MONTH) + 1);
        String day = Integer.toString(g.get(GregorianCalendar.DAY_OF_MONTH));
        if (month.length() == 1)
            month = "0".concat(month);
        if (day.length() == 1)
            day = "0".concat(day);
        return (year + "-" + month + "-" + day);
    }
}
