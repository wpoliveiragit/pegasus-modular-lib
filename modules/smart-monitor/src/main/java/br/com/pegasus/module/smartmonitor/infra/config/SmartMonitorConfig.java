package br.com.pegasus.module.smartmonitor.infra.config;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import br.com.pegasus.module.smartmonitor.app.entrypoint.SmartMonitorEntryPoint;
import br.com.pegasus.module.smartmonitor.domain.adapter.SmartMonitorAdapter;
import br.com.pegasus.module.smartmonitor.domain.core.SmartMonitorCore;
import br.com.pegasus.module.smartmonitor.domain.port.SmartMonitorPort;
import br.com.pegasus.module.smartmonitor.infra.monitor.context.processor.SmartMonitorProcessor;

public class SmartMonitorConfig {

	public static SmartMonitorEntryPoint createSmartMonitorEntryPoint(
			DefaultListableBeanFactory defaultListableBeanFactory) {
		SmartMonitorAdapter SmartMonitorAdapter = new SmartMonitorProcessor(defaultListableBeanFactory);
		SmartMonitorPort smartMonitorCore = new SmartMonitorCore(SmartMonitorAdapter);
		return new SmartMonitorEntryPoint(smartMonitorCore);
	}

}
