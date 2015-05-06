package com.example.mike.moodtracker;

import java.util.*;

public class HelpBuilder extends Activity{

CopingStrategy[] copingStrategies;

    public void rateStrategy(CopingStrategy copingStrategy, int rating){
        copingStrategy.rating = rating;
    }

    public CopingStrategy[] getCopingStrategies() {
        return copingStrategies;
    }
}
