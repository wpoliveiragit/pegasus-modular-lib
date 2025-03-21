package br.com.pegasus.lib.module.monitor.infra.interceptor.v1.factory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.MonitorInterceptor;
import br.com.pegasus.lib.module.monitor.infra.interceptor.v1.resource.exception.plugin.MonitorAdvicePlugin;

public final class MonitorAdvicePluginFactory {

	public static void createMonitorAdvicePlugin(Object bean, DefaultListableBeanFactory defaultListableBeanFactory,
			MonitorInterceptor monitorInterceptor) {
		monitorInterceptor.add(new MonitorAdvicePlugin(bean, defaultListableBeanFactory));
	}

}
