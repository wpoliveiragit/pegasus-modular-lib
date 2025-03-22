package br.com.pegasus.module.smartmonitor.resource.logger.behavior;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.module.smartmonitor.resource.logger.log.GlobalLog;

public final class GlobalLogBehavior implements LogBehavior {

	private Class<? extends Annotation> annMethod = GlobalLog.class;

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
