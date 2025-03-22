package br.com.pegasus.module.smartmonitor.infra.monitor.context.factory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import br.com.pegasus.module.smartmonitor.infra.monitor.context.SmartMonitorInterceptor;
import br.com.pegasus.module.smartmonitor.infra.monitor.support.advice.plugin.MonitorAdvicePlugin;

public final class MonitorAdvicePluginFactory {

	public static void createMonitorAdvicePlugin(Object bean, DefaultListableBeanFactory defaultListableBeanFactory,
			SmartMonitorInterceptor monitorInterceptor) {
		monitorInterceptor.add(new MonitorAdvicePlugin(bean, defaultListableBeanFactory));
	}

}
