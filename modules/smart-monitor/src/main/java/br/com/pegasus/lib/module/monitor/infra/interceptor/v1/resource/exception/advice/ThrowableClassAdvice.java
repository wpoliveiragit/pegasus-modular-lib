package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/** Adicionado na classe advice. */
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThrowableClassAdvice {
}
