package com.example.cubelearner.stopwatch;

public class CyclingCounter extends BoundedCounter {

    public CyclingCounter(int limit){
        super(limit);
    }

    @Override
    public boolean increment() {
        if(getValue() == getLimit()) {
            setValue(0);
            return true;
        }
        else {
            super.increment();
            return false;
        }
    }

    @Override
    public boolean decrement() {
        if(getValue() == 0) {
            setValue(getLimit());
            return true;
        }
        else {
            super.decrement();
            return false;
        }
    }
}
