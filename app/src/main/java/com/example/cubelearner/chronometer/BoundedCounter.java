package com.example.cubelearner.chronometer;

public class BoundedCounter extends Counter {

    private int limit;

    public BoundedCounter(int limit) {
        super();
        this.limit = limit;
    }
    public int getLimit(){
        return limit;
    }
    public void setLimit(int limit){
        this.limit = limit;
    }
    @Override
    public boolean increment() {
        if(getValue() < limit) {
            super.increment();
            return true;
        }
        else
            return false;
    }

    public String toString(){
        String res = "";
        int difference = String.valueOf(getLimit()).length() - String.valueOf(getValue()).length();
        for(int i = 0; i < difference; i ++)
            res += "0";
        res += getValue();
        return res;
    }

}
