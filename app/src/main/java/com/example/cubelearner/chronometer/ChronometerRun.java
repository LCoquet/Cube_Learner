package com.example.cubelearner.chronometer;

import android.os.SystemClock;

import com.example.cubelearner.MainActivity;

public class ChronometerRun extends Thread {
    private boolean running = false;
    Chronometer chronometer;
    private static long ELAPSE_TIME = 10000000L;

    public ChronometerRun(){
        chronometer = MainActivity.getChronometer();
    }

    @Override
    public void run(){
        long buffer = SystemClock.elapsedRealtimeNanos();
        long buffer2;
        running = true;
        while(running){
            buffer2 = SystemClock.elapsedRealtimeNanos();
            if(buffer2 >= buffer + ELAPSE_TIME){
                chronometer.increment();
                MainActivity.refreshChronometerTV();
                buffer = buffer2;
            }
        }
    }
    public void close(){
        running = false;
    }
    public boolean isRunning(){
        return running;
    }

}
