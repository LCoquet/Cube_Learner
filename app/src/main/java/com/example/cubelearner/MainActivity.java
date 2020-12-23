package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cubelearner.chronometer.Chronometer;
import com.example.cubelearner.chronometer.ChronometerRun;
import com.example.cubelearner.chronometer.ChronometerTask;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

     private static TextView chronometerTV;
     private static Chronometer chronometer;
     private boolean runningChronometer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = new Chronometer();
        chronometerTV = (TextView) findViewById(R.id.chronometer);
        refreshChronometerTV();
    }

    public static void refreshChronometerTV(){
        chronometerTV.setText(chronometer.toString());
    }

    public void runChronometer(View v){
        ChronometerRun cr = new ChronometerRun();
        cr.run();
    }

    public static Chronometer getChronometer(){
        return chronometer;
    }

}