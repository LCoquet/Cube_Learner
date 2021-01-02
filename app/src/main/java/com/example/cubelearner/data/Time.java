package com.example.cubelearner.data;

/*
 * Data class for a time, permits to be stored for the statistics activity.
 */

public class Time {

    private String chrono;
    private long precise;
    private String scramble;

    public Time(String chrono, long precise, String scramble){
        this.chrono = chrono;
        this.precise = precise;
        this.scramble = scramble;
    }

    public String getChrono() {
        return chrono;
    }

    public long getPrecise() {
        return precise;
    }

    public String getScramble() {
        return scramble;
    }
}
