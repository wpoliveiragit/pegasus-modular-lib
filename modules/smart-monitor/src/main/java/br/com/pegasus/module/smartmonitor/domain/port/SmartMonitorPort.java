package br.com.pegasus.module.smartmonitor.domain.port;

public interface SmartMonitorPort {
	
	Object filter(Object bean, String beanName);

}
