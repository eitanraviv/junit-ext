package com.googlecode.junit.ext.helpers;

import com.googlecode.junit.ext.Precondition;

import java.util.Map;

public class FailedToTearDown implements Precondition {
    private Map context;

    public FailedToTearDown(Object context) {
        this.context = (Map) context;
    }

    public void setup() {
        this.context.put("FailedToTearDown#setup", true);
    }

    public void teardown() {
        this.context.put("FailedToTearDown#teardown", true);
        throw new RuntimeException("FailedToTearDown");
    }
}
