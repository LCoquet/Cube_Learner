package com.example.cubelearner.chronometer;

import com.example.cubelearner.MainActivity;

public class ChronometerRun implements Runnable{

    public void run(){
        Chronometer chronometer = MainActivity.getChronometer();
        while(true){
            chronometer.increment();
            MainActivity.refreshChronometerTV();
        }
    }

}
