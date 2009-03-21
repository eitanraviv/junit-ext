package com.googlecode.junit.suite;

import com.googlecode.junit.ext.preconditions.ShouldFailedTestAndContinueRanAllTeardownTest;
import com.googlecode.junit.ext.preconditions.ShouldFailedTestAndStopRunningAtFailedPreconditionsTest;
import com.googlecode.junit.ext.preconditions.ShouldInvokeAllSetupsAndTeardownsTest;
import com.googlecode.junit.ext.JunitExtRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.InitializationError;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

import java.util.Map;

import static junit.framework.Assert.fail;

public class PreconditionsTest {
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
    public void shouldInvokeAllSetupsAndTeardowns() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(ShouldInvokeAllSetupsAndTeardownsTest.class);

        awareClassRunner.run(runNotifier);

        Map map = ShouldInvokeAllSetupsAndTeardownsTest.staticContext;

        assertThat(testResultListener.isPassed(), is(true));
        assertThat(map.containsKey("SuccessfullyRan#setup"), is(true));
        assertThat(map.containsKey("SuccessfullyRan#teardown"), is(true));
    }

    @Test
    public void shouldFailedTestAndStopRunningAtFailedPreconditions() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(
                ShouldFailedTestAndStopRunningAtFailedPreconditionsTest.class);

        awareClassRunner.run(runNotifier);

        Map map = ShouldFailedTestAndStopRunningAtFailedPreconditionsTest.staticContext;

        assertThat(testResultListener.isPassed(), is(false));
        assertThat(map.containsKey("FailedToSetUp#setup"), is(true));
        assertThat(map.containsKey("FailedToSetUp#teardown"), is(true));
        assertThat(map.containsKey("ShouldFailedTestAndStopRunningAtFailedPreconditionsTest" +
                "#shouldFailedTestAndStopRunningAtFailedPreconditions"), is(false));
        assertThat(map.containsKey("SuccessfullyRan#setup"), is(false));
        assertThat(map.containsKey("SuccessfullyRan#teardown"), is(false));
    }

    @Test
    public void shouldFailedTestAndContinueRanAllTeardownTest() throws InitializationError {
        JunitExtRunner awareClassRunner = new JunitExtRunner(
                ShouldFailedTestAndContinueRanAllTeardownTest.class);
        awareClassRunner.run(runNotifier);

        Map map = ShouldFailedTestAndContinueRanAllTeardownTest.staticContext;

        assertThat(testResultListener.isPassed(), is(false));
        assertThat(map.containsKey("FailedToTearDown#setup"), is(true));
        assertThat(map.containsKey("FailedToTearDown#teardown"), is(true));
        assertThat(map.containsKey("ShouldFailedTestAndContinueRanAllTeardownTest" +
                "#shouldFailedTestAndContinueRanAllTeardown"), is(true));
        assertThat(map.containsKey("SuccessfullyRan#setup"), is(true));
        assertThat(map.containsKey("SuccessfullyRan#teardown"), is(true));
    }


    private class TestCountListener extends RunListener {
        private int count = 0;

        public int count() {
            return count;
        }

        public void testFinished(Description description) throws Exception {
            count++;
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
