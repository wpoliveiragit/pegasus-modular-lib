package br.com.pegasus.module.smartmonitor.domain.adapter;

public interface SmartMonitorAdapter {

	Object filterLog(Object bean, String beanName);

	Object filterAdvice(Object bean, String beanName);

	boolean hasLogQualities(Object bean);

	boolean hasAdviceQualities(Object bean);
}
