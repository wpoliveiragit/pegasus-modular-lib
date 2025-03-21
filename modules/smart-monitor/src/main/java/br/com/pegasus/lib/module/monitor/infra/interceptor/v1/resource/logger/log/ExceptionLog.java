package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionLog {

	String exception() default "** Default Message Exception **";

}
