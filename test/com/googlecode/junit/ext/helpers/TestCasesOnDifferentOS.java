package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.*;

import java.util.Map;
import java.util.HashMap;

@RunWith(JunitExtRunner.class)
public class TestCasesOnDifferentOS {
    @Context private Map context = new HashMap();

    @Test
    @Prerequisite(checker = OSChecker.class, arguments = OSChecker.MAC)
    @Preconditions({SetUpHgRepo.class, CheckinFilesToHg.class})
    public void shouldRunOnMac() throws Exception {
        System.out.println("run hg log on" + context.get("URL"));
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
