package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cubelearner.chronometer.Chronometer;
import com.example.cubelearner.chronometer.ChronometerRun;
import com.example.cubelearner.databases.TimeTable;
import com.example.cubelearner.scrambler.ThreeByThree;

public class MainActivity extends AppCompatActivity {

     private static Chronometer chronometer;
     private ChronometerRun chronometerThread;
     private boolean running = false;
     private static TextView chronometerTV;
     private TextView scrambleTV;
     private TextView lastTimeTV;
     private TextView bestTimeTV;
     TimeTable db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * Only here for tests. Will delete this line later
         */
        //this.deleteDatabase("CubeLearner_data");
        db = new TimeTable(this);
        chronometer = new Chronometer();
        chronometerThread = new ChronometerRun();
        chronometerTV = findViewById(R.id.chronometer);
        scrambleTV = findViewById(R.id.scramble);
        lastTimeTV = findViewById(R.id.lastTime);
        bestTimeTV = findViewById(R.id.bestTime);
        updateBackgroundColor();
        refreshScramble();
        refreshChronometerTV();
        refreshLastTime();
        refreshBestTime();
    }

    public static void refreshChronometerTV(){
        chronometerTV.setText(chronometer.toString());
    }

    public void runChronometer(View v) {
        if(!running) {
            chronometer.reset();
            chronometerThread.start();
            running = true;
        }
        else {
            chronometerThread.close();
            chronometerThread = new ChronometerRun();
            db.addTime("3*3", chronometer.toString(), chronometer.getTotalCentiSeconds().getValue(), (String) scrambleTV.getText());
            refreshScramble();
            refreshLastTime();
            refreshBestTime();
            running = false;
        }
        updateBackgroundColor();
    }

    public void refreshScramble(){
        scrambleTV.setText(ThreeByThree.scrambler());
    }
    public void refreshLastTime(){
        String res = "Last time : ";
        res += db.getLastTime("3*3");
        lastTimeTV.setText(res);
    }
    public void refreshBestTime(){
        String res = "Best time : ";
        res += db.getBestTime("3*3");
        bestTimeTV.setText(res);
    }
    public void updateBackgroundColor(){
        ConstraintLayout back = findViewById(R.id.background);
        if(running)
            back.setBackgroundColor(ContextCompat.getColor(this, R.color.chronometerRunning));
        else
            back.setBackgroundColor(ContextCompat.getColor(this, R.color.chronometerStopped));
    }

    public static Chronometer getChronometer(){
        return chronometer;
    }

}