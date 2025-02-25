package com.example.cubelearner.stopwatch;

public class Counter {

    private long value;

    public Counter(){
        this.value = 0;
    }
    public long getValue(){
        return value;
    }
    public void setValue(long value) {
        this.value = value;
    }
    public boolean increment(){
        value ++;
        return false;
    }
    public boolean decrement(){
        if(value > 0)
            value --;
        return false;
    }
    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
