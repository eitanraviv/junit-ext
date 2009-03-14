package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.*;

@RunWith(JunitExtRunner.class)
public class TestCasesOnDifferentOS {
    @Test
    @RunIf(checker = OSChecker.class, arguments = OSChecker.MAC)
    public void shouldRunOnMac() throws Exception {
    }

    @Test
    @RunIf(checker = OSChecker.class, arguments = OSChecker.WINDOWS)
    public void shouldRunOnWindows() throws Exception {

    }

    @Test
    @RunIf(checker = OSChecker.class, arguments = OSChecker.LINUX)
    public void shouldRunOnLinux() throws Exception {
    }
}
