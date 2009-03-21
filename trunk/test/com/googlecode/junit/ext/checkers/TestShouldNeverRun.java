package com.googlecode.junit.ext.checkers;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.junit.ext.RunIf;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.NeverSatisfiedPrerequisite;

@RunWith(JunitExtRunner.class)
public class TestShouldNeverRun {
    @Test
    @RunIf(checker = NeverSatisfiedPrerequisite.class)
    public void shouldNotRun() throws Exception {
    }
}
