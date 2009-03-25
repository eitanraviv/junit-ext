package com.googlecode.junit.ext.checkers;

import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.RunIf;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JunitExtRunner.class)
public class TestCasesOnSocketChecker {
    @Test
    @RunIf(value = SocketIsOpened.class, arguments = {"www.google.com", "80"})
    public void shouldRun() throws Exception {
    }


    @Test
    @RunIf(value = SocketIsOpened.class, arguments = {"www.somewhereinthere.bj", "80"})
    public void shouldNotRun() throws Exception {
    }
}
