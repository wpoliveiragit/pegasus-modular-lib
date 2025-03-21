package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.invoke.ExceptionBlockInvoke;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.LogBehavior;

public class ExceptionLogPlugin extends BaseLogPlugin implements ExceptionBlockInvoke {

	public ExceptionLogPlugin(Object bean, LogBehavior methodLog, Map<Method, String> methodMap) {
		super(methodMap, methodLog, Logger.getLogger(bean.getClass().getSimpleName()));
	}

	@Override
	public void intercept(Object beanCustom, Method method, Object[] args, MethodProxy proxy, Throwable ex) {
		methodLog.exception(beanCustom, method, args, proxy, ex, log, methodMap.get(method));
	}

}
