package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.checkers.Checker;

public class AlwaysSatisfiedChecker implements Checker {
    public boolean satisfy() {
        return true;
    }
}