package br.com.pegasus.module.smartmonitor.app.entrypoint;

import org.springframework.beans.factory.config.BeanPostProcessor;

import br.com.pegasus.module.smartmonitor.domain.port.SmartMonitorPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmartMonitorEntryPoint implements BeanPostProcessor {

	private final SmartMonitorPort smartMonitorPort;

	@Override
	public final Object postProcessAfterInitialization(Object bean, String beanName) {
		return smartMonitorPort.filter(bean, beanName);
	}

}