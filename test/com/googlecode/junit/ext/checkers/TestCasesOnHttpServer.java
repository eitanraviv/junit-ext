package com.googlecode.junit.ext.checkers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.RunIf;
import com.googlecode.junit.ext.checkers.HTTPChecker;

@RunWith(JunitExtRunner.class)
public class TestCasesOnHttpServer {
    @Test
    @RunIf(checker = HTTPChecker.class, arguments = {"http://www.google.com", "80"})
    public void shouldRun() throws Exception {
    }

    @Test
    @RunIf(checker = HTTPChecker.class, arguments = {"http://www.google.com"})
    public void shouldRun2() throws Exception {
    }

    @Test
    @RunIf(checker = HTTPChecker.class, arguments = "http://www.google.com")
    public void shouldRun3() throws Exception {
    }

    @Test
    @RunIf(checker = HTTPChecker.class, arguments = {"http://www.somewhereinthere.bj", "80"})
    public void shouldNotRun() throws Exception {
    }

    @Test
    @RunIf(checker = HTTPChecker.class, arguments = {"http://www.somewhereinthere.bj"})
    public void shouldNotRun2() throws Exception {
    }

    @Test
    @RunIf(checker = HTTPChecker.class, arguments = "http://www.somewhereinthere.bj")
    public void shouldNotRun3() throws Exception {
    }
}
