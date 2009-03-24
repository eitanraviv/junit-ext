package com.googlecode.junit.suite;

import com.googlecode.junit.ext.checkers.TestShouldNeverRun;
import com.googlecode.junit.ext.checkers.TestCasesOnDifferentOS;
import com.googlecode.junit.ext.checkers.TestCasesOnSocketChecker;
import com.googlecode.junit.ext.checkers.*;
import com.googlecode.junit.ext.JunitExtRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.Failure;

import static junit.framework.Assert.fail;

public class RunIfTest {
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
    public void shouldNotRunAnyMethod() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(TestShouldNeverRun.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(0));
        assertThat(testResultListener.isPassed(), is(true));
    }

    @Test
    public void shouldOnlyRunTestCaseAssociatedWithOS() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(TestCasesOnDifferentOS.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(1));
        assertThat(testResultListener.isPassed(), is(true));
    }

    @Test
    public void shouldOnlyIgnoreAllTestCases() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(TestCasesOnIgnoringClass.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(0));
        assertThat(testResultListener.isPassed(), is(true));
    }

    @Test
    public void shouldOnlyRunTestCasesWhenClassIsNotIgnored() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(TestCasesOnIgnoringClassAndMethod.class);
        awareClassRunner.run(runNotifier);
        assertThat(countListener.count(), is(1));
        assertThat(testResultListener.isPassed(), is(true));
    }

    @Test
    public void shouldOnlyRunTestCasesWhenAssocatiedAppsInstalled() throws Exception {
        int installedAppsCount = getAppsInstalledCount();
        JunitExtRunner awareClassRunner = new JunitExtRunner(
                TestCasesOnTargetAppExist.class);
        awareClassRunner.run(runNotifier);

        assertThat(countListener.count(), is(installedAppsCount));
        assertThat(testResultListener.isPassed(), is(false));
    }

    @Test
    public void shouldOnlyRunTestCasesWhenURLIsReachleable() throws Exception {
        JunitExtRunner awareClassRunner = new JunitExtRunner(
                TestCasesOnSocketChecker.class);
        awareClassRunner.run(runNotifier);

        assertThat(countListener.count(), is(1));
        assertThat(testResultListener.isPassed(), is(true));
    }

    private int getAppsInstalledCount() {
        int count = 0;
        boolean antInstalled = getAppsInstalledCount("ant -version");
        if (antInstalled) {
            count++;
        }
        boolean svnInstalled = getAppsInstalledCount("svn help");
        if (svnInstalled) {
            count++;
        }
        return count;
    }


    private boolean getAppsInstalledCount(String commandToCheck) {
        try {
            Runtime.getRuntime().exec(commandToCheck);
            return true;
        } catch (Exception e) {
            return false;
        }
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
