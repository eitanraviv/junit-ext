package com.googlecode.junit.ext.spring;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import com.googlecode.junit.ext.*;
import com.googlecode.junit.ext.checkers.AlwaysSatisfiedChecker;
import com.googlecode.junit.ext.checkers.NeverSatisfiedChecker;

@RunWith(JunitExtSpringRunner.class)
@ContextConfiguration(locations = {
        "classpath:**/applicationContext-repository.xml"
        })
public class ShouldWorkWithSpringTest {
    @Autowired
    private SampleRepository repository;

    @Test
    @RunIf(NeverSatisfiedChecker.class)
    public void shouldFailedTestAndContinueRanAllTeardown() {
        throw new RuntimeException();
    }

    @Test
    @RunIf(AlwaysSatisfiedChecker.class)
    public void should() throws Exception {
        assertThat(repository, is(not(nullValue())));
    }

}