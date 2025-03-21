package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.util;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.advice.MonitorClassAdvice;

public final class AdviceUtil {

	public static final String MSG_EXCEPTION_ERROR = "Erro ao recuperar o Advice do contexto do Spring Boot: ";
	public static final String MSG_LOG_WARN = "Nenhum método encontrado para a exceção: {%s}";
	public static final String MSG_LOG_ERROR = "Erro ao invocar handler da exceção: {%s}";

	public static Object getAdviceBean(Object bean, DefaultListableBeanFactory defaultListableBeanFactory) {
		Class<?> adviceClazz = bean.getClass().getAnnotation(MonitorClassAdvice.class).value();

		try { // RECUPERA O BEAN DO ADVICE
			return defaultListableBeanFactory.getBean(adviceClazz);
		} catch (Exception e) {
			throw new RuntimeException(AdviceUtil.MSG_EXCEPTION_ERROR + adviceClazz.getCanonicalName(), e);
		}
	}

}
