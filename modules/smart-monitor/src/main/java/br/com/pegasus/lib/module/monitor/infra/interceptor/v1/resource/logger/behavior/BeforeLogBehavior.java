package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.log.BeforeLog;

public final class BeforeLogBehavior implements LogBehavior {

	private Class<? extends Annotation> annMethod = BeforeLog.class;

	@Override
	public void before(Object customBean, Method method, Object[] args, MethodProxy proxy, Logger log, String logMsg) {
		if (method.isAnnotationPresent(annMethod)) {
			log.info(logMsg);
		}
	}
}
