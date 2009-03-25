package com.googlecode.junit.ext.checkers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.*;
import com.googlecode.junit.ext.checkers.RunningOnTargetOS;

@RunWith(JunitExtRunner.class)
@RunIf(AlwaysSatisfiedChecker.class)
public class TestCasesOnIgnoringClassAndMethod {
    @Test
    @RunIf(value = RunningOnTargetOS.class, arguments = RunningOnTargetOS.MAC)
    public void shouldRunOnMac() throws Exception {
    }

    @Test
    @RunIf(value = RunningOnTargetOS.class, arguments = RunningOnTargetOS.WINDOWS)
    public void shouldRunOnWindows() throws Exception {

    }

    @Test
    @RunIf(value = RunningOnTargetOS.class, arguments = RunningOnTargetOS.LINUX)
    public void shouldRunOnLinux() throws Exception {
    }
}