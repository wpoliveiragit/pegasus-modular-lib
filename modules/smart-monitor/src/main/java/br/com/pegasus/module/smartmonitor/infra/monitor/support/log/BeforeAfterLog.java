package br.com.pegasus.module.smartmonitor.infra.monitor.support.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeAfterLog {

	String before() default "** Default Message Begine **";

	String after() default "** Default Message After **";
}
