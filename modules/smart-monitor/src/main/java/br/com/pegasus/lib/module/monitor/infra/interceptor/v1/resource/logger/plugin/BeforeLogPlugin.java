package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.invoke.BeforeBlockInvoke;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.logger.behavior.LogBehavior;

public class BeforeLogPlugin extends BaseLogPlugin implements BeforeBlockInvoke {

	public BeforeLogPlugin(Object bean, LogBehavior methodLog, Map<Method, String> methodMap) {
		super(methodMap, methodLog, Logger.getLogger(bean.getClass().getSimpleName()));
	}

	@Override
	public void intercept(Object customBean, Method method, Object[] args, MethodProxy proxy) {
		methodLog.before(customBean, method, args, proxy, log, methodMap.get(method));
	}

}
