package com.example.cubelearner.chronometer;

public class Chronometer {

    private Counter hours;
    private CyclingCounter minutes;
    private CyclingCounter seconds;
    private CyclingCounter centiseconds;

    public Chronometer(){
        hours = new Counter();
        minutes = new CyclingCounter(59);
        seconds = new CyclingCounter(59);
        centiseconds = new CyclingCounter(99);
    }

    public void increment(){
        if(centiseconds.increment())
            if(seconds.increment())
                if(minutes.increment())
                    hours.increment();
    }

    public void reset(){
        centiseconds.setValue(0);
        seconds.setValue(0);
        minutes.setValue(0);
        hours.setValue(0);
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
        return centiseconds;
    }
    public void setMilliseconds(CyclingCounter milliseconds) {
        this.centiseconds = milliseconds;
    }

    @Override
    public String toString(){
        return hours.toString()+":"+minutes.toString()+":"+seconds.toString()+"."+centiseconds.toString();
    }
}
