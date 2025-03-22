package br.com.pegasus.module.smartmonitor.resource.logger.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GlobalLog {

	String before() default "** Default Message Begine **";

	String after() default "** Default Message After **";

	String exception() default "** Default Message Exception **";
}
