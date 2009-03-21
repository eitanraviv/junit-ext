package com.googlecode.junit.ext;

import com.googlecode.junit.ext.checkers.Checker;

public class NeverSatisfiedPrerequisite implements Checker {
    public boolean satisfy() {
        return false;
    }
}
