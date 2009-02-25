package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.junit.ext.Preconditions;
import com.googlecode.junit.ext.Context;
import com.googlecode.junit.ext.JunitExtRunner;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtRunner.class)
public class ShouldFailedTestAndContinueRanAllTeardownTest {
    @Context
    public Map context = staticContext;
    public static Map staticContext = new HashMap();

    @Test
    @Preconditions({FailedToTearDown.class, SuccessfullyRan.class})
    public void shouldFailedTestAndContinueRanAllTeardown() {
        context.put("ShouldFailedTestAndContinueRanAllTeardownTest#shouldFailedTestAndContinueRanAllTeardown", true);
    }
}