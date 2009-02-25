package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.*;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtRunner.class)
public class TestCasesOnDifferentOS {
    @Test
    @Prerequisite(checker = OSChecker.class, arguments = OSChecker.MAC)
    public void shouldRunOnMac() throws Exception {
    }

    @Test
    @Prerequisite(checker = OSChecker.class, arguments = OSChecker.WINDOWS)
    public void shouldRunOnWindows() throws Exception {

    }

    @Test
    @Prerequisite(checker = OSChecker.class, arguments = OSChecker.LINUX)
    public void shouldRunOnLinux() throws Exception {
    }
}
