package com.example.cubelearner.stopwatch;

import android.os.SystemClock;

import com.example.cubelearner.MainActivity;

public class StopwatchRun extends Thread {
    private boolean running = false;
    private MainActivity activity;
    private Stopwatch chronometer;
    private static long ELAPSE_TIME = 10000000L;

    public StopwatchRun(MainActivity activity){
        this.activity = activity;
        chronometer = activity.getStopwatch();
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
                activity.refreshStopwatchTV();
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
