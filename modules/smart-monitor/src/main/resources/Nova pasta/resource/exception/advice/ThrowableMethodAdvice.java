package br.com.pegasus.module.smartmonitor.resource.exception.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Adicionado nos método advice que trata as exceções. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ThrowableMethodAdvice {

	/** Tipo da classe da exceção que o método irá tratar. */
	Class<?> value();
}
