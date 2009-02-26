package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.googlecode.junit.ext.Preconditions;
import com.googlecode.junit.ext.Context;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.JunitExtSpringRunner;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtSpringRunner.class)
@ContextConfiguration(locations = {
        "classpath:**/applicationContext.xml"
})
public class ShouldWorkWithSpringTest {
    @Autowired
    private SampleService service;
    @Context
    public Map context = staticContext;
    public static Map staticContext = new HashMap();

    @Test
    @Preconditions({FailedToTearDown.class, SuccessfullyRan.class})
    public void shouldFailedTestAndContinueRanAllTeardown() {
    }
}