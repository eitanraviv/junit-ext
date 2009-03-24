package com.googlecode.junit.ext;

import com.googlecode.junit.ext.checkers.Checker;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@java.lang.annotation.Retention(value = RUNTIME)
@java.lang.annotation.Target(value = {METHOD, TYPE})
public @interface RunIf {
    Class<? extends Checker> value();

    String[] arguments() default {};
}

