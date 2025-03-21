package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.ExceptionLog;

public final class ExceptionLogBehavior implements LogBehavior {

	private Class<? extends Annotation> annMethod = ExceptionLog.class;

	@Override
	public void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log, String logMsg) {
		if (method.isAnnotationPresent(annMethod)) {
			log.info(logMsg);
		}
	}

	@Override
	public void after(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj, Logger log,
			String logMsg) {
		if (method.isAnnotationPresent(annMethod)) {
			log.info(logMsg);
		}
	}

	@Override
	public void exception(Object customBean, Method method, Object[] args, MethodProxy proxy, Throwable ex, Logger log,
			String logMsg) {
		if (method.isAnnotationPresent(annMethod)) {
			log.info(logMsg);
		}
	}
}
