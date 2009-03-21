package com.googlecode.junit.ext;

import com.googlecode.junit.ext.checkers.Checker;

@java.lang.annotation.Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(value = {java.lang.annotation.ElementType.METHOD})
public @interface RunIf {
    Class<? extends Checker> checker();

    String[] arguments() default {};
}

