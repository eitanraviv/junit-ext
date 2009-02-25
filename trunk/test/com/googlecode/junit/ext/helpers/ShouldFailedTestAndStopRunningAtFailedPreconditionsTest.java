package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import com.googlecode.junit.ext.Preconditions;
import com.googlecode.junit.ext.Context;

import java.util.Map;
import java.util.HashMap;

public class ShouldFailedTestAndStopRunningAtFailedPreconditionsTest {
    public static Map staticContext = new HashMap();
    @Context
    public Map context = staticContext;


    @Test
    @Preconditions({FailedToSetUp.class, SuccessfullyRan.class})
    public void shouldFailedTestAndStopRunningAtFailedPreconditions() {
        staticContext.put("ShouldFailedTestAndStopRunningAtFailedPreconditionsTest" +
                "#shouldFailedTestAndStopRunningAtFailedPreconditions", true);
    }
}