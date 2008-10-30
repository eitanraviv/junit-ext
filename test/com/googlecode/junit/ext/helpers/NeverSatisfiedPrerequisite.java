package com.googlecode.junit.ext.helpers;

import com.googlecode.junit.ext.Checker;

public class NeverSatisfiedPrerequisite implements Checker {
    public boolean isExist() {
        return false;
    }
}
