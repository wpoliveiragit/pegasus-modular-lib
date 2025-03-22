package br.com.pegasus.module.smartmonitor.infra.monitor.support.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import br.com.pegasus.module.smartmonitor.infra.monitor.context.processor.SmartMonitorProcessor;

/** Adicione na classe que deseja monitorar. */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({ SmartMonitorProcessor.class })
public @interface MonitorClassAdvice {
	/**
	 * Tipo da classe que foi implementado a anotação {@link ThrowableClassAdvice}.
	 */
	Class<?> value();
}
