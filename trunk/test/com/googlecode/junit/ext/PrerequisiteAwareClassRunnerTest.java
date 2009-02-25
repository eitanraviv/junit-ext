package com.googlecode.junit.ext;

import com.googlecode.junit.ext.helpers.TestCasesOnDifferentOS;
import com.googlecode.junit.ext.helpers.TestShouldNeverRun;
import com.googlecode.junit.ext.helpers.TestCasesOnTargetAppExist;
import com.googlecode.junit.ext.helpers.TestCasesOnHttpServer;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assume.assumeThat;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.Failure;

import java.io.File;

import static junit.framework.Assert.fail;

public class PrerequisiteAwareClassRunnerTest {
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
                TestCasesOnHttpServer.class);
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

    @Test
    public void should() {
        assumeThat(File.separatorChar, is('\\'));
        System.out.println("1111");
        fail();
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
