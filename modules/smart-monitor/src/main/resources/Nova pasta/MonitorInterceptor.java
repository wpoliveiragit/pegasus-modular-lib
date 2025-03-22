package br.com.pegasus.module.smartmonitor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.module.smartmonitor.invoke.AfterBlockInvoke;
import br.com.pegasus.module.smartmonitor.invoke.BeforeBlockInvoke;
import br.com.pegasus.module.smartmonitor.invoke.ExceptionBlockInvoke;
import br.com.pegasus.module.smartmonitor.invoke.FinallyExceptionBlockInvoke;
import br.com.pegasus.module.smartmonitor.invoke.TrapsExceptionBlockInvoke;

/**
 * Classe de controle de anotações em métods da biblioteca.
 */
public class MonitorInterceptor implements MethodInterceptor {

	private List<BeforeBlockInvoke> beforeList = new ArrayList<>();
	private List<AfterBlockInvoke> afterList = new ArrayList<>();
	private List<ExceptionBlockInvoke> onExceptionList = new ArrayList<>();
	private List<TrapsExceptionBlockInvoke> blockExceptionList = new ArrayList<>();
	private List<FinallyExceptionBlockInvoke> finallyOnExceptionList = new ArrayList<>();

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		try {
			beforeList.forEach(ml -> ml.intercept(obj, method, args, methodProxy));
			Object returnMethod = methodProxy.invokeSuper(obj, args);
			afterList.forEach(ml -> ml.intercept(obj, method, args, methodProxy, returnMethod));
			return returnMethod;
		} catch (Throwable ex) {
			onExceptionList.forEach(ml -> ml.intercept(obj, method, args, methodProxy, ex));
			if (blockExceptionList.isEmpty()) {
				throw ex;
			}
			blockExceptionList.forEach(ml -> ml.intercept(obj, method, args, methodProxy, ex));
			return null;
		} finally {
			finallyOnExceptionList.forEach(ml -> ml.intercept(obj, method, args, methodProxy));
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
