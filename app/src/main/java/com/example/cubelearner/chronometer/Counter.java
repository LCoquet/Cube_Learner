package com.example.cubelearner.chronometer;

public class Counter {

    private int value;

    public Counter(){
        this.value = 0;
    }
    public int getValue(){
        return value;
    }
    public void setValue(int value) {
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
    public String toString(){
        return String.valueOf(value);
    }
}
