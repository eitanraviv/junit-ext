package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import com.googlecode.junit.ext.Preconditions;
import com.googlecode.junit.ext.Context;

import java.util.Map;
import java.util.HashMap;

public class ShouldInvokeAllSetupsAndTeardownsTest {
    public static Map staticContext = new HashMap();
    @Context
    public Map context = staticContext;

    @Test
    @Preconditions({SuccessfullyRan.class, SuccessfullyRan.class})
    public void shouldInvokeAllSetupsAndTeardowns() {
    }
}
