package com.example.cubelearner.stopwatch;

public class Stopwatch {

    private Counter hours;
    private CyclingCounter minutes;
    private CyclingCounter seconds;
    private CyclingCounter centiseconds;
    private Counter totalCentiSeconds;

    public Stopwatch(){
        hours = new Counter();
        minutes = new CyclingCounter(59);
        seconds = new CyclingCounter(59);
        centiseconds = new CyclingCounter(99);
        totalCentiSeconds = new Counter();
    }

    public void increment(){
        if(centiseconds.increment())
            if(seconds.increment())
                if(minutes.increment())
                    hours.increment();
        totalCentiSeconds.increment();
    }

    public void reset(){
        centiseconds.setValue(0);
        seconds.setValue(0);
        minutes.setValue(0);
        hours.setValue(0);
        totalCentiSeconds.setValue(0);
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
    public void setCentiseconds(CyclingCounter centiseconds) {
        this.centiseconds = centiseconds;
    }
    public Counter getTotalCentiSeconds() { return totalCentiSeconds; }
    public void setTotalCentiSeconds(Counter totalCentiSeconds) { this.totalCentiSeconds = totalCentiSeconds; }

    @Override
    public String toString(){
        return hours.toString()+":"+minutes.toString()+":"+seconds.toString()+":"+centiseconds.toString();
    }
}
