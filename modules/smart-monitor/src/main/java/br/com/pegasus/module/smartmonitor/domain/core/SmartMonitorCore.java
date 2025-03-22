package br.com.pegasus.module.smartmonitor.domain.core;

import br.com.pegasus.module.smartmonitor.domain.adapter.SmartMonitorAdapter;
import br.com.pegasus.module.smartmonitor.domain.port.SmartMonitorPort;

public class SmartMonitorCore implements SmartMonitorPort {

	private final SmartMonitorAdapter smartMonitorAdapter;

	public SmartMonitorCore(SmartMonitorAdapter smartMonitorAdapter) {
		this.smartMonitorAdapter = smartMonitorAdapter;
	}

	@Override
	public Object filter(Object bean, String beanName) {
		if (smartMonitorAdapter.hasAdviceQualities(bean)) {
			bean = smartMonitorAdapter.filterAdvice(bean, beanName);
		}
		if (smartMonitorAdapter.hasLogQualities(bean)) {
			bean = smartMonitorAdapter.filterLog(bean, beanName);
		}
		return bean;
	}

}
