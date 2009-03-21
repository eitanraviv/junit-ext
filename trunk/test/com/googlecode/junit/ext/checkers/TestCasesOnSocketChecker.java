package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.RunIf;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JunitExtRunner.class)
public class TestCasesOnSocketChecker {
    @Test
    @RunIf(value = SocketChecker.class, arguments = {"http://www.google.com", "80"})
    public void shouldRun() throws Exception {
    }


    @Test
    @RunIf(value = SocketChecker.class, arguments = {"http://www.somewhereinthere.bj", "80"})
    public void shouldNotRun() throws Exception {
    }
}
