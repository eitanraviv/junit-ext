package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.junit.ext.Prerequisite;
import com.googlecode.junit.ext.AppsInstalledChecker;
import com.googlecode.junit.ext.JunitExtRunner;

@RunWith(JunitExtRunner.class)
public class TestCasesOnTargetAppExist {
    @Test
    @Prerequisite(checker = AppsInstalledChecker.class, arguments = "ant -version")
    public void shouldRunIfAntInstalled() throws Exception {
    }

    @Test
    @Prerequisite(checker = AppsInstalledChecker.class, arguments = "svn help")
    public void shouldRunIfSvnInstalled() throws Exception {
        throw new RuntimeException();

    }
}
