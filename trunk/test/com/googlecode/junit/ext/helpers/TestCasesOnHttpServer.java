package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.Prerequisite;
import com.googlecode.junit.ext.HTTPChecker;

@RunWith(JunitExtRunner.class)
public class TestCasesOnHttpServer {
    @Test
    @Prerequisite(checker = HTTPChecker.class, arguments = "http://www.google.com")
    public void shouldRun() throws Exception {
    }

    @Test
    @Prerequisite(checker = HTTPChecker.class, arguments = "http://www.googleblablabla.com")
    public void shouldNotRun() throws Exception {
    }
}
