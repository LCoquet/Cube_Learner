package com.example.cubelearner.chronometer;

public class Chronometer {

    private Counter hours;
    private CyclingCounter minutes;
    private CyclingCounter seconds;
    private CyclingCounter milliseconds;

    public Chronometer(){
        hours = new Counter();
        minutes = new CyclingCounter(59);
        seconds = new CyclingCounter(59);
        milliseconds = new CyclingCounter(999);
    }

    public void increment(){
        if(milliseconds.increment())
            if(seconds.increment())
                if(minutes.increment())
                    hours.increment();
    }

    public Counter getHours() {
        return hours;
    }
    public void setHours(Counter hours) {
        this.hours = hours;
    }
    public CyclingCounter getMinutes() {
        return minutes;
    }
    public void setMinutes(CyclingCounter minutes) {
        this.minutes = minutes;
    }
    public CyclingCounter getSeconds() {
        return seconds;
    }
    public void setSeconds(CyclingCounter seconds) {
        this.seconds = seconds;
    }
    public CyclingCounter getMilliseconds() {
        return milliseconds;
    }
    public void setMilliseconds(CyclingCounter milliseconds) {
        this.milliseconds = milliseconds;
    }
    public String toString(){
        return hours.toString()+":"+minutes.toString()+":"+seconds.toString()+":"+milliseconds.toString();
    }
}
