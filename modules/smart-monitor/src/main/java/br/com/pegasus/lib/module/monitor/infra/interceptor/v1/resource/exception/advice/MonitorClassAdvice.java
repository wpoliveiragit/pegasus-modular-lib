package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.processor.MonitorBeanInterceptorProcessor;

/** Adicione na classe que deseja monitorar. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ MonitorBeanInterceptorProcessor.class })
public @interface MonitorClassAdvice {
	/**
	 * Tipo da classe que foi implementado a anotação {@link ThrowableClassAdvice}.
	 */
	Class<?> value();
}
