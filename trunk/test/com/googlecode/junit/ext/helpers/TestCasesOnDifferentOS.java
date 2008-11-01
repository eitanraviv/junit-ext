package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.PrerequisiteAwareClassRunner;
import com.googlecode.junit.ext.Prerequisite;
import com.googlecode.junit.ext.OSChecker;

@RunWith(PrerequisiteAwareClassRunner.class)
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
