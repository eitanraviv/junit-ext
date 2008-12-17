package com.googlecode.junit.ext.helpers;

import com.googlecode.junit.ext.Checker;

public class TwoArgumentsPrerequisite implements Checker {
    public TwoArgumentsPrerequisite(String[] args) {
    }

    public boolean satisfy() {
        return false;
    }
}