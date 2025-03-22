package br.com.pegasus.module.smartmonitor.infra.monitor.support.advice.plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.MethodProxy;

import br.com.pegasus.module.smartmonitor.infra.monitor.context.invoke.TrapsExceptionBlockInvoke;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.advice.MonitorMethodAdvice;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.advice.ThrowableMethodAdvice;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.advice.util.AdviceUtil;

public class MonitorAdvicePlugin implements TrapsExceptionBlockInvoke {

	private static final Logger LOG = Logger.getLogger(new Object() {
	}.getClass().getEnclosingClass().getSimpleName());

	private final Object adviceBean;
	private final Map<Class<?>, Method> methodAdviceMap;

	/** CRIA O INTERCEPTADOR DE EXCEÇÕES DA CLASSE DE MONITORAMENTO */
	public MonitorAdvicePlugin(Object bean, DefaultListableBeanFactory defaultListableBeanFactory) {
		this.adviceBean = AdviceUtil.getAdviceBean(bean, defaultListableBeanFactory);

		// Cria o map dos métodos advice usando o atributo da anotação como chave
		this.methodAdviceMap = Stream.of(adviceBean.getClass().getDeclaredMethods())
				.filter(method -> method.isAnnotationPresent(ThrowableMethodAdvice.class))
				.filter(method -> method.getParameterCount() == 1)//
				.collect(Collectors.toMap(//
						method -> method.getAnnotation(ThrowableMethodAdvice.class).value(), //
						method -> method));
	}

	@Override
	public void intercept(Object beanCustom, Method method, Object[] args, MethodProxy proxy, Throwable ex) {
		if (method.isAnnotationPresent(MonitorMethodAdvice.class)) {

			/** Invoca o metodo do advice correspondente a exceção */
			Method adviceMethod = methodAdviceMap.get(ex.getClass());
			if (adviceMethod == null) {
				LOG.warning(String.format(AdviceUtil.MSG_LOG_WARN, ex.getClass().getName()));
			} else {
				try {
					adviceMethod.invoke(adviceBean, ex); // INVOCA O MÉTODO ADVICE CORRESPONDENTE
				} catch (Throwable thr) {
					LOG.severe(String.format(AdviceUtil.MSG_LOG_ERROR, ex.getMessage()));
				}
			}

		}
	}

}
