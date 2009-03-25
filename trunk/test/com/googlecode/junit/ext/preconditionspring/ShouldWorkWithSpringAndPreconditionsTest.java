package com.googlecode.junit.ext.preconditionspring;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import com.googlecode.junit.ext.*;
import com.googlecode.junit.ext.checkers.NeverSatisfiedChecker;
import com.googlecode.junit.ext.checkers.AlwaysSatisfiedChecker;
import com.googlecode.junit.ext.preconditions.SuccessfullyRan;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtSpringRunner.class)
@ContextConfiguration(locations = {
        "classpath:**/applicationContext-preconditionspring.xml"
})
public class ShouldWorkWithSpringAndPreconditionsTest {
    public static Map staticContext = new HashMap();
    @Context
    Map context = staticContext;
    @Autowired
    private SampleComponent component;

    @Test
    @Preconditions(value = {SuccessfullyRan.class})
    @RunIf(NeverSatisfiedChecker.class)
    public void shouldFailedTestAndContinueRanAllTeardown() {
        throw new RuntimeException();
    }

    @Test
    @Preconditions(value = {SuccessfullyRan.class})
    @RunIf(AlwaysSatisfiedChecker.class)
    public void shouldFailedTestAndContinueRanAllTeardown2() {
        assertThat(component, is(not(nullValue())));
    }

}