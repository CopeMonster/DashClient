package com.copemonster.impl.modules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleObject {
    String name();
    String description() default "";
    Category category();
    boolean isEnabled() default false;
    int key() default -1;
}
