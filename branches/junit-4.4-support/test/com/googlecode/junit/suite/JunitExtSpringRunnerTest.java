package com.googlecode.junit.suite;

import com.googlecode.junit.ext.spring.ShouldWorkWithSpringTest;
import com.googlecode.junit.ext.preconditionspring.ShouldWorkWithSpringAndPreconditionsTest;
import com.googlecode.junit.ext.JunitExtSpringRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

public class JunitExtSpringRunnerTest {
    private TestCountListener countListener;
    private RunNotifier runNotifier;
    private TestResultListener testResultListener;

    @Before
    public void setUp() {
        runNotifier = new RunNotifier();
        countListener = new TestCountListener();
        testResultListener = new TestResultListener();
        runNotifier.addListener(countListener);
        runNotifier.addListener(testResultListener);
    }

    @Test
    public void shouldRunWithSpringSupport() throws Exception {
        JunitExtSpringRunner awareClassRunner = new JunitExtSpringRunner(ShouldWorkWithSpringTest.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(1));
        assertThat(countListener.ignored, is(1));
        assertThat(testResultListener.isPassed(), is(true));
    }


    @Test
    public void shouldRunWithSpringAndPrecondions() throws Exception {
        JunitExtSpringRunner awareClassRunner = new JunitExtSpringRunner(ShouldWorkWithSpringAndPreconditionsTest.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(1));
        assertThat(countListener.ignored, is(1));
        assertThat(testResultListener.isPassed(), is(true));
        assertThat(ShouldWorkWithSpringAndPreconditionsTest.staticContext.containsKey("SuccessfullyRan#setup"),
                is(true));
        assertThat(ShouldWorkWithSpringAndPreconditionsTest.staticContext.containsKey("SuccessfullyRan#teardown"),
                is(true));
    }

    private class TestCountListener extends RunListener {
        private int count = 0;
        public int ignored = 0;

        public int count() {
            return count;
        }

        public void testFinished(Description description) throws Exception {
            count++;

        }

        public void testIgnored(Description description) throws Exception {
            ignored++;
        }
    }

    private class TestResultListener extends RunListener {
        private boolean failed = false;

        public boolean isPassed() {
            return !failed;
        }

        public void testFailure(Failure failure) throws Exception {
            failed = true;
        }
    }
}
