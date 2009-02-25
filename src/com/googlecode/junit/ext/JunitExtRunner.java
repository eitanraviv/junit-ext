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
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

public class JunitExtRunner extends JUnit4ClassRunner {
    public JunitExtRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected void invokeTestMethod(Method method, RunNotifier notifier) {
        if (isPrereuisitSatisfied(method)) {
            List<Precondition> list = null;

            Description description = null;
            Object test = null;
            try {
                description = methodDescription(method);
                try {
                    test = createTest();
                } catch (InvocationTargetException e) {
                    notifier.testAborted(description, e.getCause());
                    return;
                } catch (Exception e) {
                    notifier.testAborted(description, e);
                    return;
                }
                list = invokeAllPrecondtions(method, test);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            Exception possibleException = null;
            int failedAt = -1;
            int currentIndex = 0;
            for (Precondition precondition : list) {
                currentIndex++;
                try {
                    precondition.setup();
                } catch (Exception e) {
                    possibleException = e;
                    failedAt = currentIndex;
                    break;
                }
            }
            try {
                if (failedAt == -1) {
                    TestMethod testMethod = wrapMethod(method);
                    new MethodRoadie(test, testMethod, notifier, description).run();
                }
            } finally {
                failedAt = failedAt == -1 ? list.size() : failedAt;
                for (int i = 0; i < failedAt; i++) {
                    try {
                        Precondition precondition = list.get(i);
                        precondition.teardown();
                    } catch (Exception e) {
                        possibleException = e;
                    }
                }
            }
            if (possibleException != null) {
                throw new RuntimeException(possibleException);
            }
        } else {
            Description testDescription = Description.createTestDescription(this.getTestClass().getJavaClass(),
                    method.getName());
            notifier.fireTestIgnored(testDescription);
        }
    }

    private List<Precondition> invokeAllPrecondtions(Method method, Object test) {
        Class<?> declaringClass = method.getDeclaringClass();
        Field[] declaredFields = declaringClass.getDeclaredFields();
        Object context = null;
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Context.class)) {
                try {
                    declaredField.setAccessible(true);
                    context = declaredField.get(test);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        Preconditions preconditions = method.getAnnotation(Preconditions.class);
        ArrayList<Precondition> preconditionsAsList = new ArrayList<Precondition>();
        if (preconditions == null) {
            return preconditionsAsList;
        }
        Class<? extends Precondition>[] classes = preconditions.value();
        for (Class<? extends Precondition> aClass : classes) {
            Precondition precondition;
            try {
                if (context != null) {
                    Constructor<? extends Precondition> constructor = aClass.getConstructor(Object.class);
                    precondition = constructor.newInstance(context);
                } else {
                    precondition = aClass.newInstance();
                }
                preconditionsAsList.add(precondition);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return preconditionsAsList;
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