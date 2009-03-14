package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.RunIf;
import com.googlecode.junit.ext.HTTPChecker;

@RunWith(JunitExtRunner.class)
public class TestCasesOnHttpServer {
    @Test
    @RunIf(checker = HTTPChecker.class, arguments = "http://www.google.com")
    public void shouldRun() throws Exception {
    }

    @Test
    @RunIf(checker = HTTPChecker.class, arguments = "http://www.somewhereinthere.bj")
    public void shouldNotRun() throws Exception {
    }
}
