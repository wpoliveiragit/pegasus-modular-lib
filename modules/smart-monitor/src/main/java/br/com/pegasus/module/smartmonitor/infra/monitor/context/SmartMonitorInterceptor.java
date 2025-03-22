package br.com.pegasus.module.smartmonitor.infra.monitor.context;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.AfterBlockInvoke;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.BeforeBlockInvoke;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.ExceptionBlockInvoke;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.FinallyExceptionBlockInvoke;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.TrapsExceptionBlockInvoke;
import lombok.RequiredArgsConstructor;

/**
 * Classe de controle de anotações em métods da biblioteca.
 */
@RequiredArgsConstructor
public class SmartMonitorInterceptor implements MethodInterceptor {

	private final Object bean;

	private List<BeforeBlockInvoke> beforeList = new ArrayList<>();
	private List<AfterBlockInvoke> afterList = new ArrayList<>();
	private List<ExceptionBlockInvoke> onExceptionList = new ArrayList<>();
	private List<TrapsExceptionBlockInvoke> blockExceptionList = new ArrayList<>();
	private List<FinallyExceptionBlockInvoke> finallyOnExceptionList = new ArrayList<>();

	@Override
	public Object intercept(Object bean, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		try {
			beforeList.forEach(ml -> ml.intercept(bean, method, args, methodProxy));
			Object returnMethod = methodProxy.invokeSuper(bean, args);
			afterList.forEach(ml -> ml.intercept(bean, method, args, methodProxy, returnMethod));
			return returnMethod;
		} catch (Throwable ex) {
			onExceptionList.forEach(ml -> ml.intercept(bean, method, args, methodProxy, ex));
			if (blockExceptionList.isEmpty()) {
				throw ex;
			}
			blockExceptionList.forEach(ml -> ml.intercept(bean, method, args, methodProxy, ex));
			return null;
		} finally {
			finallyOnExceptionList.forEach(ml -> ml.intercept(bean, method, args, methodProxy));
		}
	}

	public void add(BeforeBlockInvoke value) {
		beforeList.add(value);
	}

	public void add(AfterBlockInvoke value) {
		afterList.add(value);
	}

	public void add(ExceptionBlockInvoke value) {
		onExceptionList.add(value);
	}

	public void add(FinallyExceptionBlockInvoke value) {
		finallyOnExceptionList.add(value);
	}

	public void add(TrapsExceptionBlockInvoke value) {
		blockExceptionList.add(value);
	}

}
