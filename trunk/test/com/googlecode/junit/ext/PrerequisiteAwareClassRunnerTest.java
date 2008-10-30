package com.googlecode.junit.ext;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertThat;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Result;
import org.junit.runner.Description;
import static org.hamcrest.core.Is.is;
import static com.googlecode.junit.ext.PrerequisiteChecker.NOT_SATISFIED;
import static com.googlecode.junit.ext.PrerequisiteChecker.MAC;

public class PrerequisiteAwareClassRunnerTest {
    private TestCountListener countListener;
    private RunNotifier runNotifier;
    @Before
    public void setUp() {
        runNotifier = new RunNotifier();
        countListener = new TestCountListener();
        runNotifier.addListener(countListener);
    }

    @Test
    public void shouldNotRunAnyMethod() throws Exception {
        PrerequisiteAwareClassRunner awareClassRunner = new PrerequisiteAwareClassRunner(TestShouldNeverRun.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(0));
    }

    @Test
    public void shouldOnlyRunOneTestCaseInTheSuite() throws Exception {
        PrerequisiteAwareClassRunner awareClassRunner = new PrerequisiteAwareClassRunner(TestSuiteOnDifferentOS.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(1));
    }


    class TestCountListener extends RunListener {
        private int count = 0;

        public int count() {
            return count;
        }

        public void testFinished(Description description) throws Exception {
            count++;
        }

    }


    public static class TestShouldNeverRun {
        @Test
        @Prerequisite(NOT_SATISFIED)
        public void shouldNotRun() throws Exception {

        }
    }

    public static class TestSuiteOnDifferentOS {
        @Test
        @Prerequisite(MAC)
        public void shouldRunOnMac() throws Exception {
        }

        @Test
        @Prerequisite(PrerequisiteChecker.WINDOWS)
        public void shouldRunOnWindows() throws Exception {
        }

        @Test
        @Prerequisite(PrerequisiteChecker.LINUX)
        public void shouldRunOnLinux() throws Exception {
        }
    }
}
