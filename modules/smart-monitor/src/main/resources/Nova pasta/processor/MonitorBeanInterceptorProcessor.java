package br.com.pegasus.module.smartmonitor.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.cglib.proxy.Enhancer;

import br.com.pegasus.module.smartmonitor.MonitorInterceptor;
import br.com.pegasus.module.smartmonitor.factory.LogPluginFactory;
import br.com.pegasus.module.smartmonitor.factory.MonitorAdvicePluginFactory;
import br.com.pegasus.module.smartmonitor.resource.exception.advice.MonitorClassAdvice;
import br.com.pegasus.module.smartmonitor.resource.logger.log.AfterLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.BeforeAfterLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.BeforeLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.ExceptionLog;
import br.com.pegasus.module.smartmonitor.resource.logger.log.GlobalLog;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MonitorBeanInterceptorProcessor implements BeanPostProcessor {

	private final DefaultListableBeanFactory defaultListableBeanFactory;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		MonitorInterceptor monitorInterceptor = new MonitorInterceptor();

		// verifica se o bean possui alguma anotação do monitor
		boolean isMoniorAdvice = checkMonitorAdvice(bean, monitorInterceptor);
		boolean isMonitorMethodLog = checkMonitorMethodLog(bean, monitorInterceptor);

		// Verifica se foi add algum invoke no 'monitorInterceptor'
		if (isMoniorAdvice || isMonitorMethodLog) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(bean.getClass());
			enhancer.setCallback(monitorInterceptor);
			return enhancer.create();
		}
		return bean;
	}

	private boolean checkMonitorAdvice(Object bean, MonitorInterceptor monitorInterceptor) {
		if (bean.getClass().isAnnotationPresent(MonitorClassAdvice.class)) {
			MonitorAdvicePluginFactory.createMonitorAdvicePlugin(bean, defaultListableBeanFactory, monitorInterceptor);
			return true;
		}
		return false;
	}

	private boolean checkMonitorMethodLog(Object bean, MonitorInterceptor monitorInterceptor) {

		// CRIA O MAP de coleta de anotações
		Map<Class<? extends Annotation>, List<Method>> methodMap = Map.of(//
				BeforeLog.class, new ArrayList<>(), //
				AfterLog.class, new ArrayList<>(), //
				ExceptionLog.class, new ArrayList<>(), //
				BeforeAfterLog.class, new ArrayList<>(), //
				GlobalLog.class, new ArrayList<>()//
		);

		// adiciona cada metodo para cada lista das anotações correspondente
		for (Method method : bean.getClass().getDeclaredMethods()) {
			methodMap.keySet().forEach(annotation -> {
				if (method.isAnnotationPresent(annotation)) {
					methodMap.get(annotation).add(method);
				}
			});
		}

		// add cada listas no invoke do monitor correspondente
		methodMap.forEach((annotation, list) -> {
			if (!list.isEmpty()) {
				switch (AnnotationLog.getType(annotation)) {
				case BEFORE -> LogPluginFactory.createBeforeMethodLogPlugin(monitorInterceptor, bean, list);
				case AFTER -> LogPluginFactory.createAfterMethodLogPlugin(monitorInterceptor, bean, list);
				case EXCEPTION -> LogPluginFactory.createExceptionMethodLogPlugin(monitorInterceptor, bean, list);
				case BEFORE_AFTER -> LogPluginFactory.createBeforeAfterMethodLogPlugin(monitorInterceptor, bean, list);
				case GLOBAL -> LogPluginFactory.createAllMethodLogPlugin(monitorInterceptor, bean, list);
				case UNKNOWN -> System.out.println("Tipo de log desconhecido.");// trocar por log
				}
			}
		});
		// veridica se existe ao menos um elemento em uma das listas
		return methodMap.values().stream().anyMatch(list -> !list.isEmpty());
	}

	enum AnnotationLog {
		BEFORE(BeforeLog.class), //
		AFTER(AfterLog.class), //
		EXCEPTION(ExceptionLog.class), //
		BEFORE_AFTER(BeforeAfterLog.class), //
		GLOBAL(GlobalLog.class), //
		UNKNOWN(null); // Valor padrão para casos não mapeados

		@Getter
		private final Class<? extends Annotation> value;

		private AnnotationLog(Class<? extends Annotation> value) {
			this.value = value;
		}

		public static AnnotationLog getType(Class<? extends Annotation> value) {
			for (AnnotationLog type : values()) {
				if (type.value == value) {
					return type;
				}
			}
			return UNKNOWN;
		}
	}

}
