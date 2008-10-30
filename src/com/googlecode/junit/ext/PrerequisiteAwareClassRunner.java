package com.googlecode.junit.ext;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.Description;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class PrerequisiteAwareClassRunner extends JUnit4ClassRunner {
    public PrerequisiteAwareClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }


    protected void runMethods(RunNotifier notifier) {
        for (Method method : this.getTestMethods()) {
            if (isPrereuisitSatisfied(method)) {
                invokeTestMethod(method, notifier);
            } else {
                Description testDescription = Description.createTestDescription(this.getTestClass().getJavaClass(),
                        method.getName());
                notifier.fireTestIgnored(testDescription);
            }
        }
    }

    public boolean isPrereuisitSatisfied(Method method) {
        Prerequisite resource = method.getAnnotation(Prerequisite.class);
        if (resource == null) {
            return true;
        }
        Class<? extends Checker> prerequisiteChecker = resource.checker();
        try {
            String argument = resource.arguments();
            Checker checker;
            if (isArgumentNotProvided(argument)) {
                checker = prerequisiteChecker.newInstance();
            } else {
                Constructor<? extends Checker> constructor = prerequisiteChecker.getConstructor(String.class);
                checker = constructor.newInstance(argument);
                return checker.isExist();
            }

            return checker.isExist();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isArgumentNotProvided(String argument) {
        return argument == null || argument.trim().length() == 0;
    }

}