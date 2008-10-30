package com.googlecode.junit.ext;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.Description;

import java.lang.reflect.Method;

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
        PrerequisiteChecker prerequisiteChecker = resource.value();
        return prerequisiteChecker.isSatisfied();
    }

}