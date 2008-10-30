package com.googlecode.junit.ext;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertThat;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import static org.hamcrest.core.Is.is;
import static com.googlecode.junit.ext.PrerequisiteChecker.NOT_SATISFIED;

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
        PrerequisiteAwareClassRunner awareClassRunner = new PrerequisiteAwareClassRunner(TestCasesOnDifferentOS.class);
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


    @RunWith(PrerequisiteAwareClassRunner.class)
    public static class TestShouldNeverRun {
        @Test
        @Prerequisite(NOT_SATISFIED)
        public void shouldNotRun() throws Exception {

        }
    }

}
