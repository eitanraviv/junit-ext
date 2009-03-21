package com.googlecode.junit.ext.preconditions;

import com.googlecode.junit.ext.Precondition;

import java.util.Map;

public class FailedToSetUp implements Precondition {
    private Map context;

    public FailedToSetUp(Object context) {
        this.context = (Map) context;
    }

    public void setup() {
        this.context.put("FailedToSetUp#setup", true);
        throw new RuntimeException();
    }

    public void teardown() {
        this.context.put("FailedToSetUp#teardown", true);
    }
}
