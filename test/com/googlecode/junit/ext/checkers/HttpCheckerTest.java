package com.googlecode.junit.ext.checkers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class HttpCheckerTest {
    @Test
    public void shouldReturnTrueWhenResourceIsReachable() {
        HttpChecker httpChecker = new HttpChecker("Http://www.google.com");
        assertThat(httpChecker.satisfy(), is(true));
    }

    @Test
    public void shouldReturnFalseWhenResourceIsNotReachable() {
        HttpChecker httpChecker = new HttpChecker("Http://www.somewhere.not/notexit");
        assertThat(httpChecker.satisfy(), is(false));
    }

    @Test
    public void shouldReturnFalseWhenTimeout() {
        long start = System.currentTimeMillis();
        HttpChecker httpChecker = new HttpChecker("Http://www.yotube.com");
        long end = System.currentTimeMillis();
        assertThat(httpChecker.satisfy(), is(false));
        assertThat(end - start <= 10000, is(true));
    }

    @Test
    public void shouldReturnFalseWhenTimeoutSpecified() {
        long start = System.currentTimeMillis();
        HttpChecker httpChecker = new HttpChecker(new String[]{"Http://www.yotube.com", "3000"});
        long end = System.currentTimeMillis();
        assertThat(httpChecker.satisfy(), is(false));
        assertThat(end - start <= 3000, is(true));
    }


}
