package com.zhu8fei.easytest.core.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@MarkTestTarget({MarkTestTarget.class})
public @interface MarkTestTarget {
    Class[] value() default {};
}
