package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.checkers.Checker;

public class NeverSatisfiedChecker implements Checker {
    public boolean satisfy() {
        return false;
    }
}
