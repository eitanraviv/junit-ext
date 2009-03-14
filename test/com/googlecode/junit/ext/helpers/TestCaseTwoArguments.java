package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.RunIf;

@RunWith(JunitExtRunner.class)
public class TestCaseTwoArguments {
    @Test
    @RunIf(checker = TwoArgumentsPrerequisite.class, arguments = {"ant", "version"})
    public void shouldRunIfAntInstalled() throws Exception {
    }
    
}
