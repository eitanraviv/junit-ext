package com.googlecode.junit.ext.checkers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.RunIf;

@RunWith(JunitExtRunner.class)
public class TestCasesOnSocket {
    @Test
    @RunIf(value = SocketChecker.class, arguments = {"www.google.com", "80"})
    public void shouldRun() throws Exception {
    }


    @Test
    @RunIf(value = SocketChecker.class, arguments = {"www.somewhereinthere.bj", "80"})
    public void shouldNotRun() throws Exception {
    }

}
