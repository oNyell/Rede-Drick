//java/com/vulcanth/commons/annotations/CommandInfo.java
package com.vulcanth.commons.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();
    String description() default "";
    String[] aliases() default {};
}
