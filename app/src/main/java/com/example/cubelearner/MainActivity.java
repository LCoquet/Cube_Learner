package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cubelearner.chronometer.Chronometer;
import com.example.cubelearner.chronometer.ChronometerRun;
import com.example.cubelearner.scrambler.ThreeByThree;

public class MainActivity extends AppCompatActivity {

     private static TextView chronometerTV;
     private static Chronometer chronometer;
     private ChronometerRun chronometerThread;
     private boolean running = false;
     private TextView scrambleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = new Chronometer();
        chronometerThread = new ChronometerRun();
        chronometerTV = (TextView) findViewById(R.id.chronometer);
        scrambleTV = (TextView) findViewById(R.id.scramble);
        updateBackgroundColor();
        refreshScramble();
        refreshChronometerTV();
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
            refreshScramble();
            chronometerThread = new ChronometerRun();
            running = false;
        }
        updateBackgroundColor();
    }

    public void refreshScramble(){
        scrambleTV.setText(ThreeByThree.scrambler());
    }

    public void updateBackgroundColor(){
        ConstraintLayout back = (ConstraintLayout) findViewById(R.id.background);
        if(running)
            back.setBackgroundColor(ContextCompat.getColor(this, R.color.chronometerRunning));
        else
            back.setBackgroundColor(ContextCompat.getColor(this, R.color.chronometerStopped));
    }

    public static Chronometer getChronometer(){
        return chronometer;
    }

}