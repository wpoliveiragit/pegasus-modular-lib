package module.exception.monitor.v1.project;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.pegasus.module.smartmonitor.app.entrypoint.SmartMonitorEntryPoint;
import br.com.pegasus.module.smartmonitor.infra.config.SmartMonitorConfig;

@Component
public class SmartMonitorEntryPointConfig {

	@Bean
	public SmartMonitorEntryPoint createSmartMonitorEntryPoint(DefaultListableBeanFactory defaultListableBeanFactory) {
		return SmartMonitorConfig.createSmartMonitorEntryPoint(defaultListableBeanFactory);
	}

}
