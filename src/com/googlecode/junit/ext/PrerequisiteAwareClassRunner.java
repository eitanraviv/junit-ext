package com.googlecode.junit.ext;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.internal.runners.TestMethod;
import org.junit.internal.runners.MethodRoadie;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.Description;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PrerequisiteAwareClassRunner extends JUnit4ClassRunner {
    public PrerequisiteAwareClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected void invokeTestMethod(Method method, RunNotifier notifier) {
        if (isPrereuisitSatisfied(method)) {
            super.invokeTestMethod(method, notifier);
        } else {
            Description testDescription = Description.createTestDescription(this.getTestClass().getJavaClass(),
                    method.getName());
            notifier.fireTestIgnored(testDescription);
        }
    }

    public boolean isPrereuisitSatisfied(Method method) {
        Prerequisite resource = method.getAnnotation(Prerequisite.class);
        if (resource == null) {
            return true;
        }
        Class<? extends Checker> prerequisiteChecker = resource.checker();
        try {
            String[] arguments = resource.arguments();
            Checker checker;
            if (isArgumentNotProvided(arguments)) {
                checker = prerequisiteChecker.newInstance();
            } else {
                if (arguments.length == 1) {
                    Constructor<? extends Checker> constructor = prerequisiteChecker.getConstructor(String.class);
                    checker = constructor.newInstance(arguments[0]);
                } else {
                    Constructor<? extends Checker> constructor = prerequisiteChecker.getConstructor(String[].class);
                    checker = constructor.newInstance(new Object[]{arguments});
                }
            }
            return checker.satisfy();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isArgumentNotProvided(String[] argument) {
        return argument == null || argument.length == 0;
    }

}