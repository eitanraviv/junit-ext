package com.googlecode.junit.ext.checkers;

public class AlwaysSatisfiedChecker implements Checker {
    public boolean satisfy() {
        return true;
    }
}