package br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodProxy;

public interface TrapsExceptionBlockInvoke {

	void intercept(Object beanCustom, Method method, Object[] args, MethodProxy proxy, Throwable ex);

}
