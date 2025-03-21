package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Adicionado no método que deseja monitorar. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MonitorMethodAdvice {
}
