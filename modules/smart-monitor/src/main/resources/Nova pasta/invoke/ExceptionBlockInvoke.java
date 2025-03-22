package br.com.pegasus.module.smartmonitor.invoke;

import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public interface ExceptionBlockInvoke {

	void intercept(Object beanCustom, Method method, Object[] args, MethodProxy proxy, Throwable ex);

}
