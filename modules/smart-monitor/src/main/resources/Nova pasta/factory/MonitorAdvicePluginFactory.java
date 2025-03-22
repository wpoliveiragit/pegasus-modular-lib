package br.com.pegasus.module.smartmonitor.factory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import br.com.pegasus.module.smartmonitor.MonitorInterceptor;
import br.com.pegasus.module.smartmonitor.resource.exception.plugin.MonitorAdvicePlugin;

public final class MonitorAdvicePluginFactory {

	public static void createMonitorAdvicePlugin(Object bean, DefaultListableBeanFactory defaultListableBeanFactory,
			MonitorInterceptor monitorInterceptor) {
		monitorInterceptor.add(new MonitorAdvicePlugin(bean, defaultListableBeanFactory));
	}

}
