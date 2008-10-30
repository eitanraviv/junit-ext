package com.googlecode.junit.ext;

import org.junit.runner.RunWith;
import org.junit.Test;
import static com.googlecode.junit.ext.PrerequisiteChecker.MAC;

@RunWith(PrerequisiteAwareClassRunner.class)
public class TestCasesOnDifferentOS {
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
