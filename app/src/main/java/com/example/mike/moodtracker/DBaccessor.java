package com.example.mike.moodtracker;
import java.util.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.*;
import android.util.Log;
/**
 * Created by HP on 5/1/2015.
 **/

public class DBaccessor extends SQLiteOpenHelper {

    public DBaccessor(Context applicationcontext) {
        super(applicationcontext, "mooddb5.db", null, 1);

    }

    /*

    ex code on inserting
            dbTest.addMoodToDatabase("Happy", 1);
            dbTest.addBehaviorToDatabase("Sketchy", 1);
            dbTest.addBeliefToDatabase("Always depressed",1);
            dbTest.addTriggerToDatabase("WORK!!!", 1);
            dbTest.addCopingStrategyToDatabase("Deep breathing");
     */
    public void onCreate(SQLiteDatabase db) {
        /*
            CREATE MOOD TABLE:
               COLUMNS:
                    int ID (Primary key)
                    moodString : Text to store mood (ie sad)
                    coping id : Int to store coping ID, ie mad -> 2 where 2 could be deep breathing
         */

        db.execSQL("DROP TABLE IF EXISTS MOOD;");
        db.execSQL("DROP TABLE IF EXISTS TRIGGER;");
        db.execSQL("DROP TABLE IF EXISTS BELIEF;");
        db.execSQL("DROP TABLE IF EXISTS BEHAVIOR;");
        db.execSQL("DROP TABLE IF EXISTS MOOD_DATA;");
        db.execSQL("DROP TABLE IF EXISTS COPING_STRATEGY;");
        db.execSQL("DROP TABLE IF EXISTS PREF_TABLE;");


        String CREATE_MOOD_TABLE = "CREATE TABLE " +
                "MOOD" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "moodString"
                + " TEXT," + "copingID" + " INTEGER" + ")";

        db.execSQL(CREATE_MOOD_TABLE);

            /*
            CREATE TRIGGER TABLE:
               COLUMNS:
                    int ID (Primary key)
                    triggerString : Text to store mood (ie sad)
                    coping id : Int to store coping ID, ie mad -> 2 where 2 could be deep breathing
          */


        String CREATE_TRIGGER_TABLE = "CREATE TABLE " +
                "TRIGGER" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "triggerString"
                + " TEXT," + "copingID" + " INTEGER" + ")";

        db.execSQL(CREATE_TRIGGER_TABLE);

            /*
            CREATE BELIEF TABLE:
               COLUMNS:
                    int ID (Primary key)
                    beliefString : Text to store mood (ie sad)
                    coping id : Int to store coping ID, ie mad -> 2 where 2 could be deep breathing
            */

        String CREATE_BELIEF_TABLE = "CREATE TABLE " +
                "BELIEF" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "beliefString"
                + " TEXT," + "copingID" + " INTEGER" + ")";

        db.execSQL( CREATE_BELIEF_TABLE);



              /*
            CREATE BEHAVIOR TABLE:
               COLUMNS:
                    int ID (Primary key)
                    behaviorString : Text to store mood (ie sad)
                    coping id : Int to store coping ID, ie mad -> 2 where 2 could be deep breathing
            */

        String CREATE_BEHAVIOR_TABLE = "CREATE TABLE " +
                "BEHAVIOR" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "behaviorString"
                + " TEXT," + "copingID" + " INTEGER" + ")";

        db.execSQL(CREATE_BEHAVIOR_TABLE);

           /*
            CREATE BEHAVIOR TABLE:
               COLUMNS:
                   Mood Data
                    id: primary
                    timestate: date
                    mood: text of mood data
                    trigger: textual representation of trigger
                    belief: text of belief
                    moodIntensity: double from 0 to 1 of mood intensity
                    moodAnnotation: text user entered
                    ...trigger
                    ...belief
                    ...behaviorAnnotation
            */

        String CREATE_MOOD_DATA_TABLE = "CREATE TABLE " +
                "MOOD_DATA" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "timestape" + " datetime default current_timestamp,"
                + "mood" + " TEXT NOT NULL,"
                + "trigger" + " TEXT,"
                + "belief" + " TEXT,"
                + "behavior" + " TEXT,"
                + "moodIntensity" + " DOUBLE,"
                + "moodAnnotation" + " TEXT,"
                + "triggerAnnotation" + " TEXT,"
                + "beliefAnnotation" + " TEXT,"
                + "behaviorAnnotation" + " TEXT"
                + ")";

        db.execSQL(CREATE_MOOD_DATA_TABLE);

          /*
            CREATE COPINGSTRAT TABLE:
               COLUMNS:
                    int ID (Primary key)
                    compingStrategy : Full text of the coping stratgey
                    rating : Int for rating 1 - 5
            */

        String CREATE_COPINGSTRAT_TABLE = "CREATE TABLE " +
                "COPING_STRATEGY" + "("
                + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "copingStrategy"
                + " TEXT," + "rating" + " INTEGER DEFAULT 0" + ")";

        db.execSQL(CREATE_COPINGSTRAT_TABLE);

           /*
            CREATE PREFTABLE TABLE:
               COLUMNS:
                  customBGLocation : text
            */

        String CREATE_PREF_TABLE = "CREATE TABLE " +
                "PREF_TABLE" + "("
                + "userBGLocation" + "TEXT" + ")";

        db.execSQL(CREATE_PREF_TABLE);



    }

    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    /* Insert the mood data into the database */
    public void insertMoodData(String mood, String trigger, String belief, String behavior, double moodIntensity, String moodAnnotation, String triggerAnnotation, String beliefAnnotation, String behaviorAnnotation){
        SQLiteDatabase db = this.getWritableDatabase();
        String data = String.format("INSERT INTO MOOD_DATA (mood,trigger,belief,behavior, moodIntensity, moodAnnotation, triggerAnnotation, beliefAnnotation, behaviorAnnotation ) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", %.2f, \"%s\",\"%s\", \"%s\", \"%s\")",
                mood, trigger, belief, behavior, moodIntensity, moodAnnotation, triggerAnnotation, beliefAnnotation, behaviorAnnotation);

        db.execSQL(data);

    }
    /* Simple method for adding a mood into the database */
    public void addMoodToDatabase( String moodToAdd, int copingStrategy ){
        SQLiteDatabase db = this.getWritableDatabase();

        String data = String.format("INSERT INTO MOOD (moodString, copingID) VALUES (\"%s\", %d);", moodToAdd, copingStrategy);
        db.execSQL(data);
    }

    public void addTriggerToDatabase( String triggerToAdd, int copingStrategy ){
        SQLiteDatabase db = this.getWritableDatabase();

        String data = String.format("INSERT INTO TRIGGER (triggerString, copingID) VALUES (\"%s\", %d)", triggerToAdd, copingStrategy);
        db.execSQL(data);
    }

    public void addBeliefToDatabase( String beliefToAdd, int copingStrategy ){
        SQLiteDatabase db = this.getWritableDatabase();

        String data = String.format("INSERT INTO BELIEF (beliefString, copingID) VALUES (\"%s\", %d)", beliefToAdd, copingStrategy);
        db.execSQL(data);
    }

    public void addBehaviorToDatabase( String behaviorToAdd, int copingStrategy ){
        SQLiteDatabase db = this.getWritableDatabase();

        String data = String.format("INSERT INTO BEHAVIOR (behaviorString, copingID)  VALUES (\"%s\", %d)", behaviorToAdd, copingStrategy);
        db.execSQL(data);
    }

    public void addCopingStrategyToDatabase( String copingStrategy ){
        SQLiteDatabase db = this.getWritableDatabase();

        String data = String.format("INSERT INTO COPING_STRATEGY (copingStrategy) VALUES (\"%s\")", copingStrategy);
        db.execSQL(data);
    }


    public List<String> getAllMoods(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> returnList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM MOOD", null);

        cursor.moveToFirst();
        do {
            //Log.d("DB", cursor.getString((1)));
            returnList.add(cursor.getString(1));

        } while (cursor.moveToNext());

        cursor.close();

        return returnList;
    }

    public List<String> getAllTriggers(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> returnList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM TRIGGER", null);

        cursor.moveToFirst();
        do {
            //Log.d("DB", cursor.getString((1)));
            returnList.add(cursor.getString(1));

        } while (cursor.moveToNext());

        cursor.close();

        return returnList;
    }

    public List<String> getAllBehaviors(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> returnList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM BEHAVIOR", null);

        cursor.moveToFirst();
        do {
            //Log.d("DB", cursor.getString((1)));
            returnList.add(cursor.getString(1));

        } while (cursor.moveToNext());

        cursor.close();

        return returnList;
    }

    public List<String> getAllBeliefs(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> returnList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM BELIEF", null);

        cursor.moveToFirst();
        do {
            //Log.d("DB", cursor.getString((1)));
            returnList.add(cursor.getString(1));

        } while (cursor.moveToNext());

        cursor.close();

        return returnList;
    }


}
