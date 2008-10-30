package com.googlecode.junit.ext.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.junit.ext.Prerequisite;
import com.googlecode.junit.ext.PrerequisiteAwareClassRunner;

@RunWith(PrerequisiteAwareClassRunner.class)
public class TestShouldNeverRun {
    @Test
    @Prerequisite(checker = NeverSatisfiedPrerequisite.class)
    public void shouldNotRun() throws Exception {
    }
}
