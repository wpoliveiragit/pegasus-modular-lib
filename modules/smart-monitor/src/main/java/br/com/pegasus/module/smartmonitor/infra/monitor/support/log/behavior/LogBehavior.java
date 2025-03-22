package br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

public interface LogBehavior {

	default void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log, String logMsg) {
	}

	default void after(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj, Logger log,
			String logMsg) {
	}

	default void exception(Object customBean, Method method, Object[] args, MethodProxy proxy, Throwable ex,
			Logger log, String logMsg) {
	}

}
