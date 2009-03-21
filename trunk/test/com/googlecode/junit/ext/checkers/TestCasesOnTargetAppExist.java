package com.googlecode.junit.ext.checkers;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.junit.ext.RunIf;
import com.googlecode.junit.ext.checkers.AppsInstalledChecker;
import com.googlecode.junit.ext.JunitExtRunner;

@RunWith(JunitExtRunner.class)
public class TestCasesOnTargetAppExist {
    @Test
    @RunIf(checker = AppsInstalledChecker.class, arguments = "ant -version")
    public void shouldRunIfAntInstalled() throws Exception {
    }

    @Test
    @RunIf(checker = AppsInstalledChecker.class, arguments = "svn help")
    public void shouldRunIfSvnInstalled() throws Exception {
        throw new RuntimeException();

    }
}
