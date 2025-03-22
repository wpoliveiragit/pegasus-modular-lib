package br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.AfterLog;

public final class AfterLogBehavior implements LogBehavior {

	private Class<? extends Annotation> annMethod = AfterLog.class;

	@Override
	public void after(Object customBean, Method method, Object[] args, MethodProxy proxy, Object returnObj, Logger log,
			String logMsg) {
		if (method.isAnnotationPresent(annMethod)) {
			log.info(logMsg);
		}
	}

}
