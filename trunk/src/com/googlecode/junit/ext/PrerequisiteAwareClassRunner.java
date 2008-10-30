package com.googlecode.junit.ext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import com.googlecode.junit.ext.Prerequisite;

public class PrerequisiteAwareClassRunner extends JUnit4ClassRunner {
    public PrerequisiteAwareClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    protected List<Method> getTestMethods() {
        List<Method> methods = super.getTestMethods();
        return filterMethods(methods);
    }

    public static List<Method> filterMethods(List<Method> methods) {
        List<Method> filteredMethod = new ArrayList();
        for (Method method : methods) {
            Prerequisite resource = method.getAnnotation(Prerequisite.class);
            if (resource == null) {
                filteredMethod.add(method);
            } else {
                PrerequisiteChecker prerequisiteChecker = resource.value();
                if (prerequisiteChecker.isSatisfied()) {
                    filteredMethod.add(method);
                }
            }
        }
        return filteredMethod;
    }
}