package br.com.pegasus.module.smartmonitor.infra.monitor.support.log.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.BeforeBlockInvoke;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.log.behavior.LogBehavior;

public class BeforeLogPlugin extends BaseLogPlugin implements BeforeBlockInvoke {

	public BeforeLogPlugin(Object bean, LogBehavior methodLog, Map<Method, String> methodMap) {
		super(methodMap, methodLog, Logger.getLogger(bean.getClass().getSimpleName()));
	}

	@Override
	public void intercept(Object customBean, Method method, Object[] args, MethodProxy proxy) {
		methodLog.before(customBean, method, args, proxy, log, methodMap.get(method));
	}

}
