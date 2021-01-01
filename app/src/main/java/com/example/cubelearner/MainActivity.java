package com.example.cubelearner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cubelearner.processing.NotificationReceiver;
import com.example.cubelearner.stopwatch.Stopwatch;
import com.example.cubelearner.stopwatch.StopwatchRun;
import com.example.cubelearner.databases.TimeTable;
import com.example.cubelearner.scrambler.ThreeByThree;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

     private static Stopwatch stopwatch;
     private StopwatchRun stopwatchThread;
     private boolean running = false;
     private TextView stopwatchTV;
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
        stopwatch = new Stopwatch();
        stopwatchThread = new StopwatchRun(this);
        stopwatchTV = findViewById(R.id.stopwatch); //Recovery of every views we may change
        scrambleTV = findViewById(R.id.scramble);
        lastTimeTV = findViewById(R.id.lastTime);
        bestTimeTV = findViewById(R.id.bestTime);
        myAlarrm();
        updateBackgroundColor();    //Color is red while stopwatch is not running and green during the run
        refreshScramble();  //refresh all the TextViews to have the right informations
        refreshStopwatchTV();
        refreshLastTime();
        refreshBestTime();
    }

    public void refreshStopwatchTV(){
        //we have to run on ui thread because it is impossible to update a wrapped view on a lower layout
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stopwatchTV.setText(stopwatch.toString());
            }
        });
    }

    public void runStopwatch(View v) {
        if(!running) {
            stopwatch.reset();    //reset the stopwatch to have a fresh stopwatch at every run
            stopwatchThread.start();
            running = true;
        }
        else {
            stopwatchThread.close();  //stops the thread and then the stopwatch
            stopwatchThread = new StopwatchRun(this);
            db.addTime("3*3", stopwatch.toString(), stopwatch.getTotalCentiSeconds().getValue(), (String) scrambleTV.getText());
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
            back.setBackgroundColor(ContextCompat.getColor(this, R.color.stopwatchRunning));
        else
            back.setBackgroundColor(ContextCompat.getColor(this, R.color.stopwatchStopped));
    }
    public void algorithmButton(View v){
        String type;
        switch(v.getId()){
            case R.id.ollButton : type = "oll";   break;
            case R.id.pllButton : type = "pll"; break;
            default : type = ""; break;
        }
        Intent i = new Intent(this, AlgorithmActivity.class);
        i.putExtra("type", type);
        startActivity(i);
    }

    public void launchStats(View v){
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public void myAlarrm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 32);
        calendar.set(calendar.SECOND, 0);

        if(calendar.getTime().compareTo(new Date()) < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if(alarmManager != null)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public Stopwatch getStopwatch(){
        return stopwatch;
    }

}