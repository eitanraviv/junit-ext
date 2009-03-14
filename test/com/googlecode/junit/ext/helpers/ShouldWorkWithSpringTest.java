package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.googlecode.junit.ext.*;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtSpringRunner.class)
@ContextConfiguration(locations = {
        "classpath:**/applicationContext.xml"
})
public class ShouldWorkWithSpringTest {
    @Context
    public Map context = new HashMap();

    @Test
    @Preconditions(value = {FailedToTearDown.class})
    public void shouldFailedTestAndContinueRanAllTeardown() {
    }

}