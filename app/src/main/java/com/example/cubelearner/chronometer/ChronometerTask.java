package com.example.cubelearner.chronometer;

import com.example.cubelearner.MainActivity;

import java.util.TimerTask;

public class ChronometerTask extends TimerTask {

    private Chronometer chronometer;
    public ChronometerTask(Chronometer chronometer){
        super();
        this.chronometer = chronometer;
    }
    public Chronometer getChronometer(){
        return chronometer;
    }

    @Override
    public void run() {
        chronometer.increment();
        MainActivity.refreshChronometerTV();
    }
}
