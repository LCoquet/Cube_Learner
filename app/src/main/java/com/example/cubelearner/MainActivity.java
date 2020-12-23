package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cubelearner.chronometer.Chronometer;
import com.example.cubelearner.chronometer.ChronometerRun;

public class MainActivity extends AppCompatActivity {

     private static TextView chronometerTV;
     private static Chronometer chronometer;
     private boolean running = false;
     private ChronometerRun chronometerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = new Chronometer();
        chronometerThread = new ChronometerRun();
        chronometerTV = (TextView) findViewById(R.id.chronometer);
        refreshChronometerTV();
    }

    public static void refreshChronometerTV(){
        chronometerTV.setText(chronometer.toString());
    }

    public void runChronometer(View v) {
        if(!chronometerThread.isRunning()) {
            chronometer.reset();
            chronometerThread.start();
        }
        else {
            chronometerThread.close();
            chronometerThread = new ChronometerRun();
        }
    }

    public static Chronometer getChronometer(){
        return chronometer;
    }

}