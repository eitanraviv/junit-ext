package com.googlecode.junit.ext.checkers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.*;
import com.googlecode.junit.ext.checkers.OSChecker;

@RunWith(JunitExtRunner.class)
public class TestCasesOnDifferentOS {
    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.MAC)
    public void shouldRunOnMac() throws Exception {
    }

    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.WINDOWS)
    public void shouldRunOnWindows() throws Exception {

    }

    @Test
    @RunIf(value = OSChecker.class, arguments = OSChecker.LINUX)
    public void shouldRunOnLinux() throws Exception {
    }
}
