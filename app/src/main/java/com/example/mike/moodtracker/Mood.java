package com.example.mike.moodtracker;

/**
 * Created by Michael on 5/5/2015.
 */
public class Mood {
    String mood;
    float intensity;
    String annotation;

    public Mood(String mood, float intensity, String annotation) {
        this.mood = mood;
        this.intensity = intensity;
        this.annotation = annotation;
    }
}
