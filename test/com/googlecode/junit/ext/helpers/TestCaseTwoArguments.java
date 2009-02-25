package com.googlecode.junit.ext.helpers;

import org.junit.runner.RunWith;
import org.junit.Test;
import com.googlecode.junit.ext.JunitExtRunner;
import com.googlecode.junit.ext.Prerequisite;

@RunWith(JunitExtRunner.class)
public class TestCaseTwoArguments {
    @Test
    @Prerequisite(checker = TwoArgumentsPrerequisite.class, arguments = {"ant", "version"})
    public void shouldRunIfAntInstalled() throws Exception {
    }
    
}
