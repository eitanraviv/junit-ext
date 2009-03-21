package com.googlecode.junit.ext.preconditions;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.junit.ext.Preconditions;
import com.googlecode.junit.ext.Context;
import com.googlecode.junit.ext.JunitExtRunner;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtRunner.class)
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