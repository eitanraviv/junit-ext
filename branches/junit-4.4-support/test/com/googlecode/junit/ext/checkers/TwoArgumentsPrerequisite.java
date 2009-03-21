package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.checkers.Checker;

public class TwoArgumentsPrerequisite implements Checker {
    public TwoArgumentsPrerequisite(String[] args) {
    }

    public boolean satisfy() {
        return false;
    }
}