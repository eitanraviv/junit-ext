package com.googlecode.junit.ext.checkers;

import static org.hamcrest.core.Is.is;
import org.hamcrest.Matcher;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class SocketCheckerTest {
    @Test
    public void shouldReturnTrueWhenResourceIsReachable() {
        long start = System.currentTimeMillis();
        SocketChecker checker = new SocketChecker(new String[]{"167.18.7.51", "8153"});
        long end = System.currentTimeMillis();
        assertThat(checker.satisfy(), is(false));
        assertThat(end - start <= 10000, is(true));
    }
}