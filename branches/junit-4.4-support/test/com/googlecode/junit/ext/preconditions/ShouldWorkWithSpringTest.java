package com.googlecode.junit.ext.preconditions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import com.googlecode.junit.ext.*;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtSpringRunner.class)
@ContextConfiguration(locations = {
        "classpath:**/applicationContext-repository.xml"
})
public class ShouldWorkWithSpringTest {
    @Context
    public Map context = new HashMap();

    @Test
    @Preconditions(value = {FailedToTearDown.class})
    public void shouldFailedTestAndContinueRanAllTeardown() {
    }

}